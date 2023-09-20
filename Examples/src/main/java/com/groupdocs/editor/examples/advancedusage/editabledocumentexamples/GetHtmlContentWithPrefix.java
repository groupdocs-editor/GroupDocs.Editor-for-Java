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
public class GetHtmlContentWithPrefix {

    public static void run() throws Exception {
        Editor editor = new Editor(Constants.SAMPLE_DOCX, new WordProcessingLoadOptions());
        EditableDocument document = editor.edit(new WordProcessingEditOptions());
        String externalImagesPrefix = "http://www.mywebsite.com/images/id=";
        String externalCssPrefix = "http://www.mywebsite.com/css/id=";
        String prefixedHtmlContent = document.getContentString(externalImagesPrefix, externalCssPrefix);
        System.out.println("HTML content of the input document with custom image and stylesheet prefixes: " + prefixedHtmlContent);
    }
}
