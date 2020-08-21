/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.quickstart;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;

/**
 *
 * @author AlexT
 */
public class HelloWorld {
    
    public static void run() throws Exception {
        String documentPath = Constants.SAMPLE_DOCX;
        
        System.out.println(documentPath);
        
        try {
            Editor editor = new Editor(documentPath);

            // Obtain editable document from original DOCX document
            WordProcessingEditOptions editOptions = new WordProcessingEditOptions();
            EditableDocument editableDocument = editor.edit(editOptions);//Open document for editing
            

            String htmlContent = editableDocument.getEmbeddedHtml();
            // Pass htmlContent to WYSIWYG editor and edit there...
            // ...
 
            // Save edited EditableDocument object to some WordProcessing format - DOC for example
            String savePath = Constants.getOutputDirectoryPath("HelloWorldOutput.doc");
            WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
            editor.save(editableDocument, savePath, saveOptions);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
