/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage.editabledocumentexamples;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingSaveOptions;

/**
 *
 * @author AlexT
 */
public class CreateEditableDocumentFromHtmlFile {

    public static void run() throws Exception {
        String htmlFilePath = Constants.SAMPLE_HTML;
        EditableDocument document = EditableDocument.fromFile(htmlFilePath, null);

        Editor editor = new Editor(htmlFilePath);
        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
        String savePath = Constants.getOutputFilePath(htmlFilePath, ".docx");
        editor.save(document, savePath, saveOptions);
    }
}
