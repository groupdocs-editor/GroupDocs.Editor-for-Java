package com.groupdocs.editor.examples.basicusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.FontExtractionOptions;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * This example demonstrates how to create a new WordProcessing document, apply edit options,
 * modify the content, and save the edited document to a stream.
 */
public class EditNewDocument {

    public static void run() throws Exception {
        try (OutputStream memoryStream = new ByteArrayOutputStream()) {
            Editor editor = new Editor(WordProcessingFormats.Docx);

            WordProcessingEditOptions wordProcessingEditOptions = new WordProcessingEditOptions();
            wordProcessingEditOptions.setEnablePagination(false);
            wordProcessingEditOptions.setEnableLanguageInformation(true);
            wordProcessingEditOptions.setFontExtraction(FontExtractionOptions.ExtractAllEmbedded);

            EditableDocument wordProcessingDocument = editor.edit(wordProcessingEditOptions);

            String srcHtml = wordProcessingDocument.getEmbeddedHtml();
            String editedHtml = srcHtml.replace(
                    "<span class = \"text001\">&#xa0;</span>",
                    "<span class = \"text001\">New text</span>");

            EditableDocument editedDoc = EditableDocument.fromMarkup(editedHtml, null);

            WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions();
            editor.save(editedDoc, memoryStream, saveOptions);

            wordProcessingDocument.dispose();
            editedDoc.dispose();
            editor.dispose();
        }

        System.out.println("EditNewDocument routine has successfully finished");
    }
}
