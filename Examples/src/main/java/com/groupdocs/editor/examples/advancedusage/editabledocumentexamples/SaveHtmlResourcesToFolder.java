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
import com.groupdocs.editor.htmlcss.resources.fonts.FontResourceBase;
import com.groupdocs.editor.htmlcss.resources.images.IImageResource;
import com.groupdocs.editor.htmlcss.resources.textual.CssText;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;

/**
 *
 * @author AlexT
 */
public class SaveHtmlResourcesToFolder {

    public static void run() throws Exception {
        Editor editor = new Editor(Constants.SAMPLE_DOCX, new WordProcessingLoadOptions());
        EditableDocument document = editor.edit(new WordProcessingEditOptions());
        List<IImageResource> images = document.getImages();
        List<FontResourceBase> fonts = document.getFonts();
        List<CssText> stylesheets = document.getCss();

        String outputFolder = Constants.getOutputDirectoryPath("");

        for (IImageResource oneImage : images) {
            System.out.println(
                "Saving " + oneImage.getFilenameWithExtension() 
                + " of " + oneImage.getType().getFormalName() 
                + "type and " + oneImage.getLinearDimensions() + " dimensions");
            oneImage.save(outputFolder + oneImage.getFilenameWithExtension());
        }

        for (FontResourceBase oneFont : fonts) {
            System.out.println("Saving " + oneFont.getFilenameWithExtension() + " of " + oneFont.getType().getFormalName() + " type");
            oneFont.save(outputFolder + oneFont.getFilenameWithExtension());
        }

        for (CssText oneStylesheet : stylesheets) {
            System.out.println(
                "Saving " + oneStylesheet.getFilenameWithExtension() 
                + " of " + oneStylesheet.getType().getFormalName() 
                + "type and " + oneStylesheet.getEncoding() + " encoding");
            oneStylesheet.save(outputFolder + oneStylesheet.getFilenameWithExtension());
        }
    }
}
