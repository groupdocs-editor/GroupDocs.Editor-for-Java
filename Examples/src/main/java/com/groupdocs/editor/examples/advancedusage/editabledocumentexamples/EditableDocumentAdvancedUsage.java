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
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.htmlcss.resources.fonts.FontResourceBase;
import com.groupdocs.editor.htmlcss.resources.images.IImageResource;
import com.groupdocs.editor.htmlcss.resources.textual.CssText;
import com.groupdocs.editor.internal.c.a.ms.System.IO.Path;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;

/**
 *
 * @author AlexT
 */
public class EditableDocumentAdvancedUsage {

    public static void run() throws Exception {
        /*
             * Instance of EditableDocument class can be produced by the Editor.Edit method or created by the user himself.
             * EditableDocument internally stores document in its own internal closed format,
             * which is compatible (convertible) with all import and export formats, that GroupDocs.Editor supports.
             * In order to make document editable in any WYSIWYG client-side editor (like CKEditor or TinyMCE), EditableDocument provides methods for generating HTML markup and producing resources, that can be accepted by the user.
             */

            //1. Lets create a EditableDocument instance in usual way, by loading and editing input document of some supportable format
            String inputFilePath = Constants.SAMPLE_DOCX;
            String fileName = Constants.removeExtension(Path.getFileName(inputFilePath));
            Editor editor = new Editor(inputFilePath, new WordProcessingLoadOptions());
            EditableDocument beforeEdit = editor.edit(new WordProcessingEditOptions());

            //2. Now beforeEdit contains "disassembled" version of input DOCX, and it is possible to get it whole or get some of its parts

            //2.1. It is possible to generate a single String, that contains all document with all its resources as a single HTML, where stylesheets are embedded into STYLE tags, while images and fonts are embedded using base64 encoding.
            String allAsHtmlInsideOneString = beforeEdit.getEmbeddedHtml();//note: this String is huge

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
            String customImagesRequesthandlerUri = "http://example.com/ImagesHandler/id=";
            String customCssRequesthandlerUri = "http://example.com/CssHandler/id=";
            String customFontsRequesthandlerUri = "http://example.com/FontsHandler/id=";

            //3.2. Generating prefixed HTML markup to a String.
            // Now, for example, <img src="car.jpg" /> is turned into <img src="http://example.com/ImagesHandler/id=car.jpg" />
            String prefixedHtmlMarkup = beforeEdit.getContent(customImagesRequesthandlerUri, customCssRequesthandlerUri);

            //3.3. Some WYSIWYG-editors can handle only pure TML markup, without header (in other words, only internals of HTML->BODY element).
            // EditableDocument can provide such part of a document.
            String onlyBodyContent = beforeEdit.getBodyContent();

            //3.4. Like with overall content, it is possible to specify a custom prefix for external images
            String prefixedBodyContent = beforeEdit.getBodyContent(customImagesRequesthandlerUri);

            //3.5. Sometimes it is required to obtain only stylesheet for the document.
            // Depending on the scenario and input document type, document can have one or more stylesheets.
            // EditableDocument can return all of them as a list of Strings, where one String represents one stylesheet
            List<String> stylesheets = beforeEdit.getCssContent();

            //3.6. Like with overall and BODY-only content, it is possible to specify an external resource prefix
            List<String> prefixedStylesheets = beforeEdit.getCssContent(customImagesRequesthandlerUri, customFontsRequesthandlerUri);

            //4. It is possible to save the document from EditableDocument as a simple HTML-file with resources to the disk
            // In the example below a separate directory for resources (stylesheets, images, and fonts) will be created automatically.
            // However, by using a 2-parameter overload of a "Save" method you can create it manually.
            String htmlFilePath = Constants.getOutputFilePath(fileName, "html");
            beforeEdit.save(htmlFilePath);

            //5. Along with implementing IDisposable, EditableDocument provides ability to check whether current instance is disposed
            // and subscribe to the disposing event
            String res = !beforeEdit.isDisposed() ? "not" : "already";
            System.out.println("beforeEdit variable of EditableDocument type is " + res + "disposed");


            //6. It is possible to create instance of EditableDocument from input HTML document (with optional resources). There are two static factories:
            //6.1. From HTML file with resources on disk
            EditableDocument afterEditFromFile = EditableDocument.fromFile(htmlFilePath, null);
            //6.2.
            EditableDocument afterEditFromMarkup = EditableDocument.fromMarkup(htmlMarkup, allResources);

            //6.3. In the examples above two EditableDocument instances (afterEditFromFile and afterEditFromMarkup)
            //were created from content of initial EditableDocument (beforeEdit). So, all 3 of them are identical.

            //7. Manually dispose beforeEdit. Note that 'someMethod' event handler will fire only now
            beforeEdit.dispose();
            afterEditFromFile.dispose();
            afterEditFromMarkup.dispose();
            editor.dispose();

            System.out.println("EditableDocumentAdvancedUsage routine has successfully finished");
    }
}
