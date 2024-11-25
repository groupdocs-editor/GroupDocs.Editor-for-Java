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
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class Introduction {

    public static void run(java.nio.file.Path inputFile) {
        final java.nio.file.Path outputPath = makeOutputPath("Introduction.rtf");

        try {
            //2. Instantiate Editor object by loading the input file
            Editor editor = new Editor(inputFile.toString());
            try {
                //3. Open input document for edit � obtain an intermediate document, that can be edited
                EditableDocument beforeEditDocument = editor.edit();
                try {
                    //4. Grab document content and associated resources from editable document
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

                    //4.1. Get document as a single base64-encoded String, where all resources (images, fonts, etc) are embedded inside this String along with main textual content
                    String htmlWithEmbeddedResources = beforeEditDocument.getEmbeddedHtml();
                    //4.2. For example, edit its content somehow
                    String htmlWithEmbeddedResourcesChanged = htmlWithEmbeddedResources.replace("Subtitle", "Edited subtitle");

                    //5. Create new EditableDocument instance from edited content and resources
                    EditableDocument afterEditDocument = EditableDocument.fromMarkup(htmlWithEmbeddedResourcesChanged, null);
                    try {
                        //6. Save edited document to the output format
                        //6.2. Prepare saving options
                        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Rtf);
                        //6.3. Finally, save to path
                        editor.save(afterEditDocument, outputPath.toString(), saveOptions);
                        //6.4. Alternatively, output document can be saved into any stream, that support writing
                        try (OutputStream outputStream = new ByteArrayOutputStream()) {
                            editor.save(afterEditDocument, outputStream, saveOptions);
                        }

                    } finally {
                        //7. Dispose both EditableDocument instances and Editor itself
                        afterEditDocument.dispose();
                    }
                } finally {
                    //7. Dispose both EditableDocument instances and Editor itself
                    beforeEditDocument.dispose();
                }
            } finally {
                //7. Dispose both EditableDocument instances and Editor itself
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }
}
