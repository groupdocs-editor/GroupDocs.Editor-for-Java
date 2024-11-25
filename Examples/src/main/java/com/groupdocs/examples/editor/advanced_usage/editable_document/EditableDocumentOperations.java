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
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    List<IImageResource> images = document.getImages();
                    List<FontResourceBase> fonts = document.getFonts();
                    List<CssText> stylesheets = document.getCss();

                    for (IImageResource oneImage : images) {
                        System.out.println(
                                "Saving " + oneImage.getFilenameWithExtension()
                                        + " of " + oneImage.getType().getFormalName()
                                        + "type and " + oneImage.getLinearDimensions() + " dimensions");
                        oneImage.save(outputPath.resolve(oneImage.getFilenameWithExtension()).toString());
                    }

                    for (FontResourceBase oneFont : fonts) {
                        System.out.println("Saving " + oneFont.getFilenameWithExtension() + " of " + oneFont.getType().getFormalName() + " type");
                        oneFont.save(outputPath.resolve(oneFont.getFilenameWithExtension()).toString());
                    }

                    for (CssText oneStylesheet : stylesheets) {
                        System.out.println(
                                "Saving " + oneStylesheet.getFilenameWithExtension()
                                        + " of " + oneStylesheet.getType().getFormalName()
                                        + "type and " + oneStylesheet.getEncoding() + " encoding");
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
        final java.nio.file.Path outputPath = makeOutputPath("EditableDocumentOperations-saveHtmlResourcesToFolder.html");
        try {
            Files.createDirectories(outputPath);
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
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
        final java.nio.file.Path outputPath = makeOutputPath("EditableDocumentOperations.html");

        try {
            //1. Lets create a EditableDocument instance in usual way, by loading and editing input document of some supportable format
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument beforeEdit = editor.edit(new WordProcessingEditOptions());
                try {
                    //2. Now beforeEdit contains "disassembled" version of input DOCX, and it is possible to get it whole or get some of its parts

                    //2.1. It is possible to generate a single String, that contains all document with all its resources as a single HTML, where stylesheets are embedded into STYLE tags, while images and fonts are embedded using base64 encoding.
                    String htmlWithEmbeddedResources = beforeEdit.getEmbeddedHtml();//note: this String is huge

                    //2.2. There is ability to extract all images
                    List<IImageResource> allImages = beforeEdit.getImages();

                    //2.3. Ability to extract all fonts
                    List<FontResourceBase> allFonts = beforeEdit.getFonts();

                    //2.4. Ability to extract all stylesheets and present them in textual (serialized) form
                    List<CssText> allStylesheets = beforeEdit.getCss();

                    //2.5. There is also an ability to gather all resources at one call
                    List<IHtmlResource> allResources = beforeEdit.getAllResources();
                    //allResources in fact is a concatenation of all images, fonts and stylesheets (allImages + allFonts + allStylesheets)

                    //2.6. Obtain HTML markup of the document (without embedded resources)
                    String htmlMarkup = beforeEdit.getContent();

                    //3. Sometimes it is necessary to obtain HTML markup, where all external links will be adjusted to some remote resource,
                    // that can handle these resource requests. EditableDocument allows to specify such prefix.
                    // Such markup, with tuned links, can be injected directly into WYSIWYG-editor.

                    //3.1. Preparing prefixes, that will prepend original external links
                    String customImagesRequestHandlerUri = "http://example.com/ImagesHandler/id=";
                    String customCssRequestHandlerUri = "http://example.com/CssHandler/id=";
                    String customFontsRequestHandlerUri = "http://example.com/FontsHandler/id=";

                    //3.2. Generating prefixed HTML markup to a String.
                    // Now, for example, <img src="car.jpg" /> is turned into <img src="http://example.com/ImagesHandler/id=car.jpg" />
                    String prefixedHtmlMarkup = beforeEdit.getContentString(customImagesRequestHandlerUri, customCssRequestHandlerUri);

                    //3.3. Some WYSIWYG-editors can handle only pure TML markup, without header (in other words, only internals of HTML->BODY element).
                    // EditableDocument can provide such part of a document.
                    String onlyBodyContent = beforeEdit.getBodyContent();

                    //3.4. Like with overall content, it is possible to specify a custom prefix for external images
                    String prefixedBodyContent = beforeEdit.getBodyContent(customImagesRequestHandlerUri);

                    //3.5. Sometimes it is required to obtain only stylesheet for the document.
                    // Depending on the scenario and input document type, document can have one or more stylesheets.
                    // EditableDocument can return all of them as a list of Strings, where one String represents one stylesheet
                    List<String> stylesheets = beforeEdit.getCssContent();

                    //3.6. Like with overall and BODY-only content, it is possible to specify an external resource prefix
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

                    //4. It is possible to save the document from EditableDocument as a simple HTML-file with resources to the disk
                    // In the example below a separate directory for resources (stylesheets, images, and fonts) will be created automatically.
                    // However, by using a 2-parameter overload of a "Save" method you can create it manually.
                    beforeEdit.save(outputPath.toString());

                    //5. Along with implementing IDisposable, EditableDocument provides ability to check whether current instance is disposed
                    // and subscribe to the disposing event
                    String res = !beforeEdit.isDisposed() ? "not" : "already";
                    System.out.println("beforeEdit variable of EditableDocument type is " + res + "disposed");

                    //6. It is possible to create instance of EditableDocument from input HTML document (with optional resources). There are two static factories:
                    //6.1. From HTML file with resources on disk
                    EditableDocument afterEditFromFile = EditableDocument.fromFile(outputPath.toString(), null);
                    try {
                        final String afterEditFromFileHtmlContent = afterEditFromFile.getBodyContent();
                        final List<IImageResource> afterEditFromFileImages = afterEditFromFile.getImages();

                        System.out.println("afterEditFromFileHtmlContent length: " + afterEditFromFileHtmlContent.length());

                        System.out.println("afterEditFromFileImages: ");
                        for (IImageResource imageResource : afterEditFromFileImages) {
                            System.out.println("\t" + imageResource.getType().getFormalName() + ": " + imageResource.getName());
                        }

                        //6.2.
                        EditableDocument afterEditFromMarkup = EditableDocument.fromMarkup(htmlMarkup, allResources);
                        try {
                            final String afterEditFromMarkupHtmlContent = afterEditFromMarkup.getBodyContent();
                            final List<IImageResource> afterEditFromMarkupImages = afterEditFromMarkup.getImages();
                            //6.3. In the examples above two EditableDocument instances (afterEditFromFile and afterEditFromMarkup)
                            //were created from content of initial EditableDocument (beforeEdit). So, all 3 of them are identical.

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


    public static void workingWithResources(Path inputFile) {
        final java.nio.file.Path outputPath = makeOutputPath("EditableDocumentOperations-workingWithResources.html");
        /*
         * Almost every document contains or may contain some attachments of binary or textual nature; first of all they are images and/or fonts.
         * Also, when input document is converted to the intermediate EditableDocument instance,
         * all style-related data may be represented as stylesheets (CSS).
         * All of these are grouped in the GroupDocs.Editor.HtmlCss.Resources namespace, where every resource has its own class and methods.
         */
        System.out.println("****************************************");
        System.out.println("Investigating resources of DOCX document");
        try {
            Files.createDirectories(outputPath);
            //1. Lets create a EditableDocument instance in usual way, by loading and editing input document of some supportable format
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                WordProcessingEditOptions editOptions = new WordProcessingEditOptions();
                //1.1. Enable max font extraction - ExtractAll
                editOptions.setFontExtraction(FontExtractionOptions.ExtractAll);

                //1.2. Create EditableDocument instance
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {
                    //2. Gather resources
                    List<IImageResource> images = beforeEdit.getImages();
                    List<FontResourceBase> fonts = beforeEdit.getFonts();
                    List<CssText> stylesheets = beforeEdit.getCss();

                    //3. Print detailed info about every used resource and save every resource to the file

                    //3.1. All image resources implement IImageResource interface.
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

                    //3.2. All fonts implement FontResourceBase abstract class
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

                    //3.3. All stylesheets are of CssText type
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
                    System.out.println();

                    //4. For every resource it is possible to get its content:
                    //4.1. As a byte stream...
                    InputStream ms = images.get(0).getByteContent(); //do with this stream something
                    //4.2. ...and as a base64-encoded String
                    String base64EncodedResource = images.get(0).getTextContent();

                    //Don't forget to dispose all resources
                    beforeEdit.dispose();
                    editor.dispose();
                    System.out.println("WorkingWithResources routine has successfully finished");
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
    }
}
