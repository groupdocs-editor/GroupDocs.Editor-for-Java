/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage;

import java.io.*;
import java.util.List;
import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.PresentationFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.options.PresentationEditOptions;
import com.groupdocs.editor.options.PresentationLoadOptions;
import com.groupdocs.editor.options.PresentationSaveOptions;

/**
 *
 * @author AlexT
 */
public class WorkingWithPresentations {

    public static void run() throws Exception {
        //1. Get a path to the input file (or stream with file content).
        //In this case it is sample PPTX (OOXML) with three slides.
        String inputFilePath = Constants.SAMPLE_PPTX;

        //2. Create stream from this path
        InputStream fs = new FileInputStream(inputFilePath);
        //3. Create load options for this document
        PresentationLoadOptions loadOptions = new PresentationLoadOptions();

        //3.1. In case if input document is password-protected, we can specify password for its opening...
        loadOptions.setPassword("some_password_to_open_a_document");
        //3.2. ...but, because document is unprotected, this password will be ignored

        //4. Load document with options to the Editor instance
        Editor editor = new Editor(fs, loadOptions);

        //5. Create editing options
        PresentationEditOptions editOptions = new PresentationEditOptions();

        //5.1. Specify slide index from original document.
        editOptions.setSlideNumber(0);//Because index is 0-based, it is 1st slide

        //5.2. Enable option that allows to show hidden slides in original document.
        editOptions.setShowHiddenSlides(true);

        //6. Create intermediate EditableDocument with editing options, so it is only 1st slide
        EditableDocument beforeEdit = editor.edit(editOptions);

        //7. Extract textual content as HTML markup with all resources
        String originalContent = beforeEdit.getContent();
        //7.1. Extract all resources from original document
        List<IHtmlResource> allResources = beforeEdit.getAllResources();

        //8. Modify content somehow on client side
        String editedContent = originalContent.replace("New text", "edited text");

        //9. Create new EditableDocument instance with edited content and same old resources
        EditableDocument afterEdit = EditableDocument.fromMarkup(editedContent, allResources);

        //10. Create document save options
        PresentationSaveOptions saveOptions = new PresentationSaveOptions(PresentationFormats.Pptm);

        //10.1 Encrypt output document by setting non-null and non-empty opening password
        saveOptions.setPassword("password");

        //11. Save it
        //11.1. Prepare saving filename and path
        String outputPath = Constants.getOutputFilePath("sample_out", saveOptions.getOutputFormat().getExtension());

        //11.2. Prepare stream for saving
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //FileStream outputStream = File.create(outputPath)

        //11.3. Save
        editor.save(afterEdit, outputStream, saveOptions);

        try(OutputStream outputFile = new FileOutputStream(outputPath)) {
            outputStream.writeTo(outputFile);
        }

        System.out.println("WorkingWithPresentations routine has successfully finished");
    }
}
