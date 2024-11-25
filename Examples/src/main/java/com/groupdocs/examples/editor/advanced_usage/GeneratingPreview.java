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

            // Load file to the Editor constructor
            Editor editor = new Editor(inputFile.toString());
            try {

                // Get document info for this file
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);

                // Cast this document info to the PresentationDocumentInfo type
                PresentationDocumentInfo infoSlides = (PresentationDocumentInfo) documentInfo;

                // Get the number of all slides
                int slidesCount = infoSlides.getPageCount();

                //Iterate through all slides and generate the preview on every iteration
                for (int i = 0; i < slidesCount; i++) {
                    // Generate one preview as SVG image by slide index
                    SvgImage oneSvgPreview = infoSlides.generatePreview(i);

                    // Save it to the file
                    oneSvgPreview.save(outputFolderPath.resolve(oneSvgPreview.getFilenameWithExtension()).toString());
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocuments saved successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return outputFolderPath;
    }
}
