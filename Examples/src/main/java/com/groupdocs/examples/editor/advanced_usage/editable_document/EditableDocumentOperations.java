package com.groupdocs.examples.editor.advanced_usage.editable_document;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.htmlcss.resources.fonts.FontResourceBase;
import com.groupdocs.editor.htmlcss.resources.images.IImageResource;
import com.groupdocs.editor.htmlcss.resources.textual.CssText;
import com.groupdocs.editor.options.FontExtractionOptions;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class EditableDocumentOperations {

    public static void saveHtmlResourcesToFolder(Path inputFile) {
        final java.nio.file.Path outputPath = makeOutputPath("EditableDocumentOperations-getHtmlContentWithPrefix");
        try {
            Files.createDirectories(outputPath);

            // Create new Editor with input file path and load options
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    // Retrieve images, fonts, and stylesheets from the document
                    List<IImageResource> images = document.getImages();
                    List<FontResourceBase> fonts = document.getFonts();
                    List<CssText> stylesheets = document.getCss();

                    // Save each image type and dimension to the output path
                    for (IImageResource oneImage : images) {
                        System.out.println(
                                "Saving image: " + oneImage.getFilenameWithExtension()
                                        + " of type: " + oneImage.getType().getFormalName()
                                        + ", with dimensions: " + oneImage.getLinearDimensions());
                        oneImage.save(outputPath.resolve(oneImage.getFilenameWithExtension()).toString());
                    }

                    // Save each font type to the output path
                    for (FontResourceBase oneFont : fonts) {
                        System.out.println("Saving font: " + oneFont.getFilenameWithExtension() + " of type: " + oneFont.getType().getFormalName());
                        oneFont.save(outputPath.resolve(oneFont.getFilenameWithExtension()).toString());
                    }

                    // Save each stylesheet encoding and type to the output path
                    for (CssText oneStylesheet : stylesheets) {
                        System.out.println(
                                "Saving stylesheet: " + oneStylesheet.getFilenameWithExtension()
                                        + ", of type: " + oneStylesheet.getType().getFormalName()
                                        + ", with encoding: " + oneStylesheet.getEncoding());
                        oneStylesheet.save(outputPath.resolve(oneStylesheet.getFilenameWithExtension()).toString());
                    }
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void saveHtmlToFolder(Path inputFile) {
        final java.nio.file.Path outputPath = makeOutputPath("EditableDocumentOperations-saveHtmlToFolder.html");
        try {
            Files.createDirectories(outputPath);

            // Initializing an Editor object and opening the document from input file path
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // Editing document using word processing options
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    // Saving the edited document to specified output path
                    document.save(outputPath.toString());
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    /*
     * Instance of EditableDocument class can be produced by the Editor.Edit method or created by the user himself.
     * EditableDocument internally stores document in its own internal closed format,
     * which is compatible (convertible) with all import and export formats, that GroupDocs.Editor supports.
     * In order to make document editable in any WYSIWYG client-side editor (like CKEditor or TinyMCE), EditableDocument provides methods for generating HTML markup and producing resources, that can be accepted by the user.
     */
    public static Path complexOperations(Path inputFile) {
        final java.nio.file.Path outputPath = makeOutputPath("EditableDocumentOperations-complexOperations.html");

        try {
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // The EditableDocument instance holds the "disassembled" version of the input DOCX,
                // allowing access to parts or the whole document.
                EditableDocument beforeEdit = editor.edit(new WordProcessingEditOptions());
                try {

                    // Generate a single String that contains the entire document in HTML format,
                    // with embedded stylesheets in STYLE tags and images/fonts encoded in base64.
                    String htmlWithEmbeddedResources = beforeEdit.getEmbeddedHtml();//note: this String is huge

                    // Extract all images from the document.
                    List<IImageResource> allImages = beforeEdit.getImages();

                    // Extract all fonts used in the document.
                    List<FontResourceBase> allFonts = beforeEdit.getFonts();

                    // Extract all stylesheets in serialized textual form.
                    List<CssText> allStylesheets = beforeEdit.getCss();

                    // Gather all resources (images, fonts, stylesheets) in one call.
                    List<IHtmlResource> allResources = beforeEdit.getAllResources();
                    // All resources include all images, fonts, and stylesheets.

                    // Obtain HTML markup of the document without embedded resources.
                    String htmlMarkup = beforeEdit.getContent();

                    // Prepare prefixes for remote resource handling,
                    // allowing external links to point to specific handlers for images, CSS, and fonts.
                    String customImagesRequestHandlerUri = "http://example.com/ImagesHandler/id=";
                    String customCssRequestHandlerUri = "http://example.com/CssHandler/id=";
                    String customFontsRequestHandlerUri = "http://example.com/FontsHandler/id=";

                    // Generate HTML markup with adjusted external links using custom prefixes.
                    String prefixedHtmlMarkup = beforeEdit.getContentString(customImagesRequestHandlerUri, customCssRequestHandlerUri);

                    // Retrieve only the body content of the document.
                    String onlyBodyContent = beforeEdit.getBodyContent();

                    // Obtain body content with a custom prefix for external images.
                    String prefixedBodyContent = beforeEdit.getBodyContent(customImagesRequestHandlerUri);

                    // Obtain all stylesheets as a list of Strings, where each String represents one stylesheet.
                    List<String> stylesheets = beforeEdit.getCssContent();

                    // Retrieve stylesheets with external resource prefixes applied.
                    List<String> prefixedStylesheets = beforeEdit.getCssContent(customImagesRequestHandlerUri, customFontsRequestHandlerUri);

                    System.out.println("htmlWithEmbeddedResources length: " + htmlWithEmbeddedResources.length());
                    System.out.println("prefixedHtmlMarkup length: " + prefixedHtmlMarkup.length());
                    System.out.println("onlyBodyContent length: " + onlyBodyContent.length());
                    System.out.println("prefixedBodyContent length: " + prefixedBodyContent.length());

                    System.out.println("allImages: ");
                    for (IImageResource imageResource : allImages) {
                        System.out.println("\t" + imageResource.getType().getFormalName() + ": " + imageResource.getName());
                    }

                    System.out.println("allFonts: ");
                    for (FontResourceBase fontResource : allFonts) {
                        System.out.println("\t" + fontResource.getType().getFormalName() + ": " + fontResource.getName());
                    }

                    System.out.println("allStylesheets: ");
                    for (CssText cssText : allStylesheets) {
                        System.out.println("\t" + cssText.getType().getFormalName() + ": " + cssText.getName());
                    }

                    System.out.println("stylesheets: ");
                    for (String stylesheet : stylesheets) {
                        System.out.println("\tstylesheet length: " + stylesheet.length());
                    }

                    System.out.println("prefixedStylesheets: ");
                    for (String prefixedStylesheet : prefixedStylesheets) {
                        System.out.println("\tprefixedStylesheet length: " + prefixedStylesheet.length());
                    }

                    // Save the edited document as an HTML file, along with its resources.
                    beforeEdit.save(outputPath.toString());

                    String res = !beforeEdit.isDisposed() ? "not" : "already";
                    System.out.println("beforeEdit variable of EditableDocument type is " + res + "disposed");

                    // Create an instance of EditableDocument from the saved HTML file.
                    EditableDocument afterEditFromFile = EditableDocument.fromFile(outputPath.toString(), null);
                    try {
                        final String afterEditFromFileHtmlContent = afterEditFromFile.getBodyContent();
                        final List<IImageResource> afterEditFromFileImages = afterEditFromFile.getImages();

                        System.out.println("afterEditFromFileHtmlContent length: " + afterEditFromFileHtmlContent.length());

                        System.out.println("afterEditFromFileImages: ");
                        for (IImageResource imageResource : afterEditFromFileImages) {
                            System.out.println("\t" + imageResource.getType().getFormalName() + ": " + imageResource.getName());
                        }

                        // Create an instance of EditableDocument from the HTML markup.
                        EditableDocument afterEditFromMarkup = EditableDocument.fromMarkup(htmlMarkup, allResources);
                        try {
                            final String afterEditFromMarkupHtmlContent = afterEditFromMarkup.getBodyContent();
                            final List<IImageResource> afterEditFromMarkupImages = afterEditFromMarkup.getImages();

                            System.out.println("afterEditFromMarkupHtmlContent length: " + afterEditFromMarkupHtmlContent.length());

                            System.out.println("afterEditFromMarkupImages: ");
                            for (IImageResource imageResource : afterEditFromMarkupImages) {
                                System.out.println("\t" + imageResource.getType().getFormalName() + ": " + imageResource.getName());
                            }
                        } finally {
                            afterEditFromMarkup.dispose();
                        }
                    } finally {
                        afterEditFromFile.dispose();
                    }
                } finally {
                    beforeEdit.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }

        return outputPath;
    }


    /*
     * Almost every document contains or may contain some attachments of binary or textual nature; first of all they are images and/or fonts.
     * Also, when input document is converted to the intermediate EditableDocument instance,
     * all style-related data may be represented as stylesheets (CSS).
     * All of these are grouped in the GroupDocs.Editor.HtmlCss.Resources namespace, where every resource has its own class and methods.
     */
    public static void workingWithResources(Path inputFile) {
        final java.nio.file.Path outputPath = makeOutputPath("EditableDocumentOperations-workingWithResources.html");
        try {
            Files.createDirectories(outputPath);
            // Initializing an Editor object and opening the document from input file path
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                WordProcessingEditOptions editOptions = new WordProcessingEditOptions();
                // Enable maximum font extraction - Extract all fonts
                editOptions.setFontExtraction(FontExtractionOptions.ExtractAll);

                // Create EditableDocument instance for editing
                EditableDocument editableDocument = editor.edit(editOptions);
                try {
                    // Gather resources used in the document
                    List<IImageResource> images = editableDocument.getImages();
                    List<FontResourceBase> fonts = editableDocument.getFonts();
                    List<CssText> stylesheets = editableDocument.getCss();

                    // Print detailed info about each used resource and save each resource to the output file

                    System.out.println(images.size() + " images are used:");
                    for (int i = 0; i < images.size(); i++) {
                        IImageResource oneImage = images.get(i);
                        System.out.println(
                                i + ". "
                                        + oneImage.getFilenameWithExtension() + ". Type: "
                                        + oneImage.getType().getFormalName() + ". Aspect ratio: "
                                        + oneImage.getAspectRatio().toString() + ". Linear dimensions: "
                                        + oneImage.getLinearDimensions().toString() + "px"
                        );
                        oneImage.save(outputPath.resolve(oneImage.getFilenameWithExtension()).toString());
                    }
                    System.out.println();

                    System.out.println(fonts.size() + " fonts are used:");
                    for (int i = 0; i < fonts.size(); i++) {
                        FontResourceBase oneFont = fonts.get(i);
                        System.out.println(
                                i + ". "
                                        + oneFont.getFilenameWithExtension() + ". Type: "
                                        + oneFont.getType().getFormalName() + "."
                        );
                        oneFont.save(outputPath.resolve(oneFont.getFilenameWithExtension()).toString());
                    }
                    System.out.println();

                    System.out.println(stylesheets.size() + " stylesheets are used:");
                    for (int i = 0; i < stylesheets.size(); i++) {
                        CssText oneStylesheet = stylesheets.get(i);
                        System.out.println(
                                i + ". "
                                        + oneStylesheet.getFilenameWithExtension() + ". Type: "
                                        + oneStylesheet.getType().getFormalName() + ". Encoding: "
                                        + oneStylesheet.getEncoding() + "."
                        );
                        oneStylesheet.save(outputPath.resolve(oneStylesheet.getFilenameWithExtension()).toString());
                    }

                    // Also retrieve as a base64-encoded String
                    String base64EncodedResource = images.get(0).getTextContent();
                    // NOTE: Closing stream that is returned by getByteContent will make it impossible to use getTextContent method

                    // Retrieve content for the first image resource as a byte stream
                    try (InputStream ms = images.get(0).getByteContent()) {
                        // Process this stream as needed
                    }
                } finally {
                    editableDocument.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }
}
