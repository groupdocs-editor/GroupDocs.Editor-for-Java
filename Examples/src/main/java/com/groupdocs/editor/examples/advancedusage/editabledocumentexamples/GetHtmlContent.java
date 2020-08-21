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
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author AlexT
 */
public class GetHtmlContent {

    public static void run() throws Exception {
        InputStream fs = new FileInputStream(Constants.SAMPLE_DOCX);
        Editor editor = new Editor(fs, new WordProcessingLoadOptions());
        EditableDocument document = editor.edit(new WordProcessingEditOptions());
        String htmlContent = document.getContent();
        System.out.println("HTML content of the input document (first 200 chars): " + htmlContent.substring(0, 200));
    }
}
