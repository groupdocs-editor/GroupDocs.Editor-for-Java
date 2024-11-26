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
            // Create an instance of Editor class with the input file path
            Editor editor = new Editor(inputFile.toString());
            try {
                // Initialize email edit options to include all content
                EmailEditOptions editOptions = new EmailEditOptions(MailMessageOutput.All);

                // Generate an editable document from the input file using the defined edit options
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {
                    // Extract and save HTML content from the generated editable document
                    String savedHtmlContent = beforeEdit.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    // Generate a new editable document with edited content obtained from the client-side
                    EditableDocument afterEdit = EditableDocument.fromMarkup(savedHtmlContent, null);
                    try {
                        // Define save options for two types of output MSG format (common and body/attachments)
                        EmailSaveOptions saveOptions1 = new EmailSaveOptions(MailMessageOutput.Common);
                        EmailSaveOptions saveOptions2 = new EmailSaveOptions(MailMessageOutput.Body | MailMessageOutput.Attachments);

                        // Save the first output MSG to a file using defined save options and generated editable document
                        editor.save(afterEdit, outputPath.toString(), saveOptions1);

                        // Save the second output MSG to an output stream using defined save options and generated editable document
                        try (ByteArrayOutputStream outputMsgStream = new ByteArrayOutputStream()) {
                            editor.save(afterEdit, outputMsgStream, saveOptions2);
                        }
                    } finally {
                        afterEdit.dispose();
                    }
                } finally {
                    beforeEdit.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return outputPath;
    }
}
