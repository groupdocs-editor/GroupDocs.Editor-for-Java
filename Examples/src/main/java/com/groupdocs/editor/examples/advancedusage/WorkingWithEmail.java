package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.options.EmailEditOptions;
import com.groupdocs.editor.options.EmailSaveOptions;
import com.groupdocs.editor.options.MailMessageOutput;

import java.io.ByteArrayOutputStream;

public class WorkingWithEmail {
    public static void run() throws Exception {
        //0. Prepare a sample file
        String msgInputPath = Constants.SAMPLE_MSG;

        //1. Load to the Editor class
        Editor msgEditor = new Editor(msgInputPath);

        //2. Create edit options with all content
        EmailEditOptions editOptions = new EmailEditOptions(MailMessageOutput.All);

        //3. Generate an editable document
        EditableDocument originalDoc = msgEditor.edit(editOptions);

        //4. Emit HTML from EditableDocument, send it to the client-side, edit it there in WYSIWYG-editor (omitted here)
        String savedHtmlContent = originalDoc.getEmbeddedHtml();

        //5. Obtain edited content from the client-side and generate a new EditableDocument from it (omitted here)
        EditableDocument editedDoc = EditableDocument.fromMarkup(savedHtmlContent, null);

        //6. Create 1st save options
        EmailSaveOptions saveOptions1 = new EmailSaveOptions(MailMessageOutput.Common);

        //7. Create 2nd save options
        EmailSaveOptions saveOptions2 = new EmailSaveOptions(MailMessageOutput.Body | MailMessageOutput.Attachments);

        //8. Generate and save 1st output MSG to the file
        String outputMsgPath = Constants.getOutputFilePath( "OutputFile","msg");
        msgEditor.save(editedDoc, outputMsgPath, saveOptions1);

        //9. Generate and save 2nd output MSG to the stream
        ByteArrayOutputStream outputMsgStream = new ByteArrayOutputStream();
        msgEditor.save(editedDoc, outputMsgStream, saveOptions2);

        //10. Dispose all resources
        editedDoc.dispose();
        originalDoc.dispose();
        msgEditor.dispose();
        System.out.println("WorkingWithEmail routine has successfully finished");
    }
}
