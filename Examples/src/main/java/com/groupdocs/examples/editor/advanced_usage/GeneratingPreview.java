package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.htmlcss.resources.images.vector.SvgImage;
import com.groupdocs.editor.metadata.IDocumentInfo;
import com.groupdocs.editor.metadata.PresentationDocumentInfo;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.file.Files;
import java.nio.file.Path;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class GeneratingPreview {
    public static Path run(Path inputFile) {
        final Path outputFolderPath = makeOutputPath("GeneratingPreview");

        try {
            Files.createDirectories(outputFolderPath);

            // Load the input file into the Editor
            Editor editor = new Editor(inputFile.toString());
            try {

                // Retrieve document information from the editor
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);

                // Cast to PresentationDocumentInfo to access slide-related methods
                PresentationDocumentInfo infoSlides = (PresentationDocumentInfo) documentInfo;

                // Get the total number of slides in the presentation
                int slidesCount = infoSlides.getPageCount();

                // Iterate through all slides to generate SVG previews
                for (int i = 0; i < slidesCount; i++) {
                    // Generate an SVG preview for each slide index
                    SvgImage oneSvgPreview = infoSlides.generatePreview(i);

                    // Save the generated SVG image to the output folder
                    oneSvgPreview.save(outputFolderPath.resolve(oneSvgPreview.getFilenameWithExtension()).toString());
                }
            } finally {
                editor.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return outputFolderPath;
    }
}
