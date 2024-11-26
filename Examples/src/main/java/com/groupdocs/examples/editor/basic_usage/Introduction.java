package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.resources.fonts.FontResourceBase;
import com.groupdocs.editor.htmlcss.resources.images.IImageResource;
import com.groupdocs.editor.htmlcss.resources.textual.CssText;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/**
 * This class provides functionality to process documents by editing their content
 * and saving them in a specified format. It utilizes various resources such as
 * images, fonts, and stylesheets to generate the final output document.
 */
public class Introduction {
    /**
     * This method processes the specified input file and performs some operations and saves it in a specified format.
     *
     * @param inputFile The path to the input file which will be processed.
     */
    public static Path run(Path inputFile) {
        final java.nio.file.Path outputPath = makeOutputPath("Introduction.rtf");

        try {
            Editor editor = new Editor(inputFile.toString());
            try {
                // Edit WordProcessing document with default options
                EditableDocument beforeEditDocument = editor.edit();
                try {
                    // Get HTML content from the original document
                    String htmlContent = beforeEditDocument.getContent();
                    List<IImageResource> images = beforeEditDocument.getImages();
                    List<FontResourceBase> fonts = beforeEditDocument.getFonts();
                    List<CssText> stylesheets = beforeEditDocument.getCss();

                    System.out.println("htmlContent length: " + htmlContent.length());

                    System.out.println("images: ");
                    for (IImageResource imageResource : images) {
                        System.out.println("\t" + imageResource.getType().getFormalName() + ": " + imageResource.getName());
                    }

                    System.out.println("fonts: ");
                    for (FontResourceBase fontResource : fonts) {
                        System.out.println("\t" + fontResource.getType().getFormalName() + ": " + fontResource.getName());
                    }

                    System.out.println("stylesheets: ");
                    for (CssText cssText : stylesheets) {
                        System.out.println("\t" + cssText.getType().getFormalName() + ": " + cssText.getName());
                    }

                    // Get document as a single base64-encoded String, where all resources (images, fonts, etc) are embedded inside this String along with main textual content
                    String htmlWithEmbeddedResources = beforeEditDocument.getEmbeddedHtml();
                    // Edit its content
                    String htmlWithEmbeddedResourcesChanged = htmlWithEmbeddedResources.replace("Subtitle", "Edited subtitle");

                    // Create new EditableDocument instance from edited content and resources
                    EditableDocument afterEditDocument = EditableDocument.fromMarkup(htmlWithEmbeddedResourcesChanged, null);
                    try {
                        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Rtf);
                        // Save to path
                        editor.save(afterEditDocument, outputPath.toString(), saveOptions);
                        // Alternatively, output document can be saved into any stream that supports writing
                        try (OutputStream outputStream = new ByteArrayOutputStream()) {
                            editor.save(afterEditDocument, outputStream, saveOptions);
                        }

                    } finally {
                        afterEditDocument.dispose();
                    }
                } finally {
                    beforeEditDocument.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return outputPath;
    }
}
