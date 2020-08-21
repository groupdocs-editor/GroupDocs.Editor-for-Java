/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage.editabledocumentexamples;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author AlexT
 */
public class CreateEditableDocumentFromInnerBody {

    public static void run() throws Exception {
        //1. Get path to the file with HTML BODY inner markup
        String pathToHtmlFile = Constants.SAMPLE_HTML_BODY;

        //2. Read this markup to String
        String content = FileUtils.readFileToString(new File(pathToHtmlFile), "utf-8");

        //3. Get path to the resource folder
        String pathToResourceFolder = Constants.SAMPLE_HTML_BODY_RESOURCES;

        //4. Initialize EditableDocument
        EditableDocument inputDoc = EditableDocument.fromBodyMarkupAndResourceFolder(content, pathToResourceFolder);

        //5. Check obtained document
        System.out.println("There should be 2 stylesheets, and actually is " + inputDoc.getCss().size());
        System.out.println("There should be 2 images, and actually is " + inputDoc.getImages().size());

        //6. Save it to the file
        String outputHtmlFilePath = Constants.getOutputDirectoryPath("_output.html");
        inputDoc.save(outputHtmlFilePath);

        //7. Save it to the document
        //7.1. Get saving format
        WordProcessingFormats saveFormat = WordProcessingFormats.fromExtension("docm");
        //7.2. Get saving options
        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(saveFormat);
        //7.3. Create instance of Editor class, initialize it with anything
        Editor editor = new Editor(pathToHtmlFile);
        //7.4. Prepare output path for the DOCM document
        String outputDocmFilePath = Constants.getOutputFilePath("_output", saveFormat.getExtension());
        //7.5. Save it
        editor.save(inputDoc, outputDocmFilePath, saveOptions);

        System.out.println("CreateEditableDocumentFromInnerBody routine has successfully finished");
    }
}
