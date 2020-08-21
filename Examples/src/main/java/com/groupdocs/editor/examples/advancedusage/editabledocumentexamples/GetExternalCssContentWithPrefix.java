/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage.editabledocumentexamples;

import java.util.List;
import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;

/**
 *
 * @author AlexT
 */
public class GetExternalCssContentWithPrefix {

    public static void run() throws Exception {
        Editor editor = new Editor(Constants.SAMPLE_DOCX, new WordProcessingLoadOptions());
        EditableDocument document = editor.edit(new WordProcessingEditOptions());
        String externalImagesPrefix = "http://www.mywebsite.com/images/id=";
        String externalFontsPrefix = "http://www.mywebsite.com/fonts/id=";
        List<String> stylesheets = document.getCssContent(externalImagesPrefix, externalFontsPrefix);
        System.out.println("There are " + stylesheets.size() + " stylesheets in the input document");
        for (String css : stylesheets) {
            System.out.println(css);
        }
    }
}
