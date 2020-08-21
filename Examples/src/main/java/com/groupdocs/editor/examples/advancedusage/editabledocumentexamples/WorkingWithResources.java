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
import com.groupdocs.editor.options.FontExtractionOptions;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import java.io.InputStream;

/**
 *
 * @author AlexT
 */
public class WorkingWithResources {

    public static void run() throws Exception {
        /*
             * Almost every document contains or may contain some attachments of binary or textual nature; first of all they are images and/or fonts.
             * Also, when input document is converted to the intermediate EditableDocument instance,
             * all style-related data may be represented as stylesheets (CSS).
             * All of these are grouped in the GroupDocs.Editor.HtmlCss.Resources namespace, where every resource has its own class and methods.
         */
        System.out.println("****************************************");
        System.out.println("Investigating resources of DOCX document");

        //1. Lets create a EditableDocument instance in usual way, by loading and editing input document of some supportable format
        String inputFilePath = Constants.SAMPLE_DOCX;
        Editor editor = new Editor(inputFilePath, new WordProcessingLoadOptions());

        WordProcessingEditOptions editOptions = new WordProcessingEditOptions();
        //1.1. Enable max font extraction - ExtractAll
        editOptions.setFontExtraction(FontExtractionOptions.ExtractAll);

        //1.2. Create EditableDocument instance
        EditableDocument beforeEdit = editor.edit(editOptions);

        //2. Gather resources
        List<IImageResource> images = beforeEdit.getImages();
        List<FontResourceBase> fonts = beforeEdit.getFonts();
        List<CssText> stylesheets = beforeEdit.getCss();

        //3. Print detailed info about every used resource and save every resource to the file
        String outputFolderPath = Constants.getOutputDirectoryPath("");

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
            oneImage.save(outputFolderPath + oneImage.getFilenameWithExtension());
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
            oneFont.save(outputFolderPath + oneFont.getFilenameWithExtension());
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
            oneStylesheet.save(outputFolderPath + oneStylesheet.getFilenameWithExtension());
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
    }
}
