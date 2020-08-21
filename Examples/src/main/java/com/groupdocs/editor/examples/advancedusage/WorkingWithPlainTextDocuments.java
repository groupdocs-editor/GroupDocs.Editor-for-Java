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
import com.groupdocs.editor.options.TextEditOptions;
import com.groupdocs.editor.options.TextLeadingSpacesOptions;
import com.groupdocs.editor.options.TextSaveOptions;
import com.groupdocs.editor.options.TextTrailingSpacesOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 *
 * @author AlexT
 */
public class WorkingWithPlainTextDocuments {

    public static void run() throws Exception {
        //1. Get a path to the input TXT file (or stream with file content)
        String inputFilePath = Constants.SAMPLE_TXT;

        //2. Create Editor instance (not load options required)
        Editor editor = new Editor(inputFilePath);

        //3. Create TXT editing options
        TextEditOptions editOptions = new TextEditOptions();
        editOptions.setEncoding(StandardCharsets.UTF_8);
        editOptions.setRecognizeLists(true);
        editOptions.setLeadingSpaces(TextLeadingSpacesOptions.ConvertToIndent);
        editOptions.setTrailingSpaces(TextTrailingSpacesOptions.Trim);

        //4. Create EditableDocument instance
        EditableDocument beforeEdit = editor.edit(editOptions);

        //5. Edit is somehow
        String originalTextContent = beforeEdit.getContent();
        String updatedTextContent = originalTextContent.replace("text", "EDITED text");

        List<IHtmlResource> allResources = beforeEdit.getAllResources();

        //6. Create EditableDocument with updated content
        EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);

        //7. Create WordProcessing save options
        WordProcessingSaveOptions wordSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docm);
        wordSaveOptions.setLocale(Locale.US);

        //8. Create TXT saving options
        TextSaveOptions txtSaveOptions = new TextSaveOptions();
        txtSaveOptions.setEncoding(StandardCharsets.UTF_8);
        txtSaveOptions.setPreserveTableLayout(true);

        //9. Prepare paths for saving resultant DOCX and TXT files
        String outputWordPath = Constants.getOutputFilePath(inputFilePath, "docm");
        String outputTxtPath = Constants.getOutputFilePath(inputFilePath, "txt");

        //10. Save
        editor.save(afterEdit, outputWordPath, wordSaveOptions);
        editor.save(afterEdit, outputTxtPath, txtSaveOptions);

        System.out.println("WorkingWithPlainTextDocuments routine has successfully finished");
    }
}
