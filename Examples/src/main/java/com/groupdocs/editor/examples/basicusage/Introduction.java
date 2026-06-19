package com.groupdocs.editor.examples.basicusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingSaveOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

/**
 * This example demonstrates the most basic usage of GroupDocs.Editor for editing documents:
 * loading a document from file path, editing, and saving with complete minimum of options and settings.
 */
public class Introduction {

    public static void run() throws Exception {
        String inputFilePath = Constants.SAMPLE_DOCX2;

        Editor editor = new Editor(inputFilePath);
        EditableDocument beforeEdit = editor.edit();

        String content = beforeEdit.getContent();
        beforeEdit.getImages();
        beforeEdit.getFonts();
        beforeEdit.getCss();

        String allEmbeddedInsideString = beforeEdit.getEmbeddedHtml();
        String allEmbeddedInsideStringEdited = allEmbeddedInsideString.replace("Subtitle", "Edited subtitle");

        EditableDocument afterEdit = EditableDocument.fromMarkup(allEmbeddedInsideStringEdited, null);

        String outputDir = Constants.getOutputDirectoryPath("Introduction");
        new File(outputDir).mkdirs();
        String outputPath = outputDir + File.separator + Constants.removeExtension(new File(inputFilePath).getName()) + ".rtf";
        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Rtf);
        editor.save(afterEdit, outputPath, saveOptions);

        try (OutputStream ms = new ByteArrayOutputStream()) {
            editor.save(afterEdit, ms, saveOptions);
        }

        beforeEdit.dispose();
        afterEdit.dispose();
        editor.dispose();

        System.out.println("Introduction routine has successfully finished");
    }
}
