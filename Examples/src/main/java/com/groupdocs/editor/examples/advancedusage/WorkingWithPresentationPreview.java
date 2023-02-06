package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.htmlcss.resources.images.vector.SvgImage;
import com.groupdocs.editor.metadata.IDocumentInfo;
import com.groupdocs.editor.metadata.PresentationDocumentInfo;

import java.io.File;

public class WorkingWithPresentationPreview {
    public static void run() {
        // Obtain a valid full path to the presentation file
        String inputPath = Constants.getSampleFilePath("FormatingExample.pptx");

        String outputFolder = Constants.getOutputDirectoryPath("");

        // Load file to the Editor constructor
        Editor editor = new Editor(inputPath);

        // Get document info for this file
        IDocumentInfo infoUncasted = editor.getDocumentInfo(null);

        // Cast this document info to the PresentationDocumentInfo type
        PresentationDocumentInfo infoSlides = (PresentationDocumentInfo) infoUncasted;

        // Get the number of all slides
        int slidesCount = infoSlides.getPageCount();

        //Iterate through all slides and generate the preview on every iteration
        for (int i = 0; i < slidesCount; i++) {
            // Generate one preview as SVG image by slide index
            SvgImage oneSvgPreview = infoSlides.generatePreview(i);

            // Save it to the file
            oneSvgPreview.save(new File(outputFolder, oneSvgPreview.getFilenameWithExtension()).getPath());
        }
        editor.dispose();
        System.out.println("WorkingWithPresentationPreview routine has successfully finished");
    }
}
