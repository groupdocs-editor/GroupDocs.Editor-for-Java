/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.basicusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.TextSaveOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


/**
 *
 * @author AlexT
 */
public class SaveDocument {

    public static void run() throws Exception {
//Load and edit some document, like it was shown in LoadDocument.cs and EditDocument.cs
        String inputFilePath = Constants.SAMPLE_DOCX;
        Editor editor = new Editor(inputFilePath, new WordProcessingLoadOptions());
        EditableDocument defaultWordProcessingDoc = editor.edit();

        //Modify its content somehow. Because there is no attached WYSIWYG-editor, this code simulates document editing
        String allEmbeddedInsideString = defaultWordProcessingDoc.getEmbeddedHtml();
        String allEmbeddedInsideStringEdited = allEmbeddedInsideString.replace("Subtitle", "Edited subtitle");//Modified version

        //Create new EditableDocument instance from modified HTML content
        EditableDocument editedDoc = EditableDocument.fromMarkup(allEmbeddedInsideStringEdited, null);

        //Save edited document as RTF, specified through file path
        String outputRtfPath = Constants.getOutputDirectoryPath("editedDoc.rtf");
        WordProcessingSaveOptions rtfSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Rtf);
        editor.save(editedDoc, outputRtfPath, rtfSaveOptions);

        //Save edited document as DOCM, specified through stream
        String outputDocmPath = Constants.getOutputDirectoryPath("editedDoc.docm");
        WordProcessingSaveOptions docmSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docm);
        
        try (OutputStream outputStream = new ByteArrayOutputStream()) {
            editor.save(editedDoc, outputStream, docmSaveOptions);
        }

        //Save edited document as plain text, specified through file path.
        //Note that all complex content and resources (like images and fonts) will be missed in output TXT
        String outputTxtPath = Constants.getOutputDirectoryPath("editedDoc.txt");
        TextSaveOptions textSaveOptions = new TextSaveOptions();
        textSaveOptions.setEncoding(StandardCharsets.UTF_8);
        textSaveOptions.setPreserveTableLayout(true);
        editor.save(editedDoc, outputTxtPath, textSaveOptions);

        //Dispose EditableDocument and Editor instances
        editedDoc.dispose();
        defaultWordProcessingDoc.dispose();
        editor.dispose();

        System.out.println("SaveDocument routine has successfully finished");
    }
}
