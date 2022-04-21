/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.basicusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.resources.fonts.FontResourceBase;
import com.groupdocs.editor.htmlcss.resources.images.IImageResource;
import com.groupdocs.editor.htmlcss.resources.textual.CssText;
import com.groupdocs.editor.internal.c.a.ms.System.IO.Path;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author AlexT
 */
public class Introduction {

    public static void run() throws Exception {
        //1. Get a path to the input file (or stream with file content). In this case it is sample DOCX with complex content and formatting
        String inputFilePath = Constants.SAMPLE_DOCX;

        //2. Instantiate Editor object by loading the input file
        Editor editor = new Editor(inputFilePath);
        //3. Open input document for edit — obtain an intermediate document, that can be edited
        EditableDocument beforeEdit = editor.edit();

        //4. Grab document content and associated resources from editable document
        String content = beforeEdit.getContent();
        List<IImageResource> images = beforeEdit.getImages();
        List<FontResourceBase> fonts = beforeEdit.getFonts();
        List<CssText> stylesheets = beforeEdit.getCss();

        //4.1. Get document as a single base64-encoded String, where all resources (images, fonts, etc) are embedded inside this String along with main textual content
        String allEmbeddedInsideString = beforeEdit.getEmbeddedHtml();
        //4.2. For example, edit its content somehow
        String allEmbeddedInsideStringEdited = allEmbeddedInsideString.replace("Subtitle", "Edited subtitle");

        //5. Create new EditableDocument instance from edited content and resources
        EditableDocument afterEdit = EditableDocument.fromMarkup(allEmbeddedInsideStringEdited, null);

        //6. Save edited document to the output format
        //6.1. In order to do that, prepare stream or path for saving (writing) output document...
        String outputPath = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(inputFilePath)), "rtf");
        //6.2. ...and prepare saving options
        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Rtf);
        //6.3. Finally, save to path
        editor.save(afterEdit, outputPath, saveOptions);
        //6.4. Alternatively, output document can be saved into any stream, that support writing
        try (OutputStream outputStream = new ByteArrayOutputStream()) {
            editor.save(afterEdit, outputStream, saveOptions);
        }

        //7. Dispose both EditableDocument instances and Editor itself
        beforeEdit.dispose();
        afterEdit.dispose();
        editor.dispose();

        System.out.println("Introduction routine has successfully finished");
    }
}
