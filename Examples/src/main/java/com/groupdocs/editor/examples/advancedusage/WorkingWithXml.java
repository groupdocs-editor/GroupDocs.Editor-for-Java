/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage;

import java.util.List;
import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.htmlcss.serialization.QuoteType;
import com.groupdocs.editor.internal.c.a.ms.System.IO.Path;
import com.groupdocs.editor.options.TextSaveOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.editor.options.XmlEditOptions;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author AlexT
 */
public class WorkingWithXml {

    public static void run() throws Exception {
//1. Get a path to the input XML file (or stream with file content)
        String inputFilePath = Constants.SAMPLE_XML;

        //2. Create Editor instance (not load options required)
        Editor editor = new Editor(inputFilePath);

        //3. Create XML editing options
        XmlEditOptions editOptions = new XmlEditOptions();
        editOptions.setAttributeValuesQuoteType(QuoteType.DoubleQuote);
        editOptions.setRecognizeEmails(true);
        editOptions.setRecognizeUris(true);
        editOptions.setTrimTrailingWhitespaces(true);

        //4. Create EditableDocument instance
        EditableDocument beforeEdit = editor.edit(editOptions);

        //5. Edit is somehow
        String originalTextContent = beforeEdit.getContent();
        String updatedTextContent = originalTextContent.replace("John", "Samuel");

        List<IHtmlResource> allResources = beforeEdit.getAllResources();

        //6. Create EditableDocument with updated content
        EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);

        //7. Create WordProcessing save options
        WordProcessingSaveOptions wordSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

        //8. Create TXT save options
        TextSaveOptions txtSaveOptions = new TextSaveOptions();
        txtSaveOptions.setEncoding(StandardCharsets.UTF_8);

        //9. Prepare paths for saving resultant DOCX and TXT files
        String outputWordPath = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(inputFilePath)), "docx");

        String outputTxtPath = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(inputFilePath)), "txt");

        //10. Save
        editor.save(afterEdit, outputWordPath, wordSaveOptions);
        editor.save(afterEdit, outputTxtPath, txtSaveOptions);
        System.out.println("WorkingWithXml routine has successfully finished");
    }
}
