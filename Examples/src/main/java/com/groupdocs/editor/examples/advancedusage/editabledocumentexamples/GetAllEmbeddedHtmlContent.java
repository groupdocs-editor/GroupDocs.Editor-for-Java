/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage.editabledocumentexamples;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;

/**
 *
 * @author AlexT
 */
public class GetAllEmbeddedHtmlContent {

    public static void run() throws Exception {
        Editor editor = new Editor(Constants.SAMPLE_DOCX, new WordProcessingLoadOptions());
        EditableDocument document = editor.edit(new WordProcessingEditOptions());
        String embeddedHtmlContent = document.getEmbeddedHtml();
        System.out.println("HTML content of the input document, where all resources are embedded in base64 encoding: " + embeddedHtmlContent);
    }
}
