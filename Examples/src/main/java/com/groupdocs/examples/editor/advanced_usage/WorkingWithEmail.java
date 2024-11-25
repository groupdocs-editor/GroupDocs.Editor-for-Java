package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.options.EmailEditOptions;
import com.groupdocs.editor.options.EmailSaveOptions;
import com.groupdocs.editor.options.MailMessageOutput;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class WorkingWithEmail {
    public static Path run(Path inputFile) {
        final Path outputPath = makeOutputPath("WorkingWithEmail.eml");
        try {
            //1. Load to the Editor class
            Editor editor = new Editor(inputFile.toString());
            try {

                //2. Create edit options with all content
                EmailEditOptions editOptions = new EmailEditOptions(MailMessageOutput.All);

                //3. Generate an editable document
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {
                    //4. Emit HTML from EditableDocument, send it to the client-side, edit it there in WYSIWYG-editor (omitted here)
                    String savedHtmlContent = beforeEdit.getEmbeddedHtml();

                    //5. Obtain edited content from the client-side and generate a new EditableDocument from it (omitted here)
                    EditableDocument afterEdit = EditableDocument.fromMarkup(savedHtmlContent, null);
                    try {
                        //6. Create 1st save options
                        EmailSaveOptions saveOptions1 = new EmailSaveOptions(MailMessageOutput.Common);

                        //7. Create 2nd save options
                        EmailSaveOptions saveOptions2 = new EmailSaveOptions(MailMessageOutput.Body | MailMessageOutput.Attachments);

                        //8. Generate and save 1st output MSG to the file
                        editor.save(afterEdit, outputPath.toString(), saveOptions1);

                        //9. Generate and save 2nd output MSG to a stream
                        try (ByteArrayOutputStream outputMsgStream = new ByteArrayOutputStream()) {
                            editor.save(afterEdit, outputMsgStream, saveOptions2);
                        }
                        //10. Dispose all resources
                    } finally {
                        afterEdit.dispose();
                    }
                } finally {
                    beforeEdit.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocuments saved successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return outputPath;
    }
}
