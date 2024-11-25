/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.options.*;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/**
 * @author AlexT
 */
public class WorkingWithText {

    public static List<Path> run(Path inputFile) {
        final java.nio.file.Path outputDocmPath = makeOutputPath("WorkingWithText.docm");
        final java.nio.file.Path outputTxtPath = makeOutputPath("WorkingWithText.txt");
        try {
            //2. Create Editor instance (not load options required)
            Editor editor = new Editor(inputFile.toString());
            try {

                //3. Create TXT editing options
                TextEditOptions editOptions = new TextEditOptions();
                editOptions.setEncoding(StandardCharsets.UTF_8);
                editOptions.setRecognizeLists(true);
                editOptions.setLeadingSpaces(TextLeadingSpacesOptions.ConvertToIndent);
                editOptions.setTrailingSpaces(TextTrailingSpacesOptions.Trim);

                //4. Create EditableDocument instance
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {

                    //5. Edit is somehow
                    String originalTextContent = beforeEdit.getContent();
                    String updatedTextContent = originalTextContent.replace("text", "EDITED text");

                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    //6. Create EditableDocument with updated content
                    EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);
                    try {

                        //7. Create WordProcessing save options
                        WordProcessingSaveOptions wordSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docm);
                        wordSaveOptions.setLocale(Locale.US);

                        //8. Create TXT saving options
                        TextSaveOptions txtSaveOptions = new TextSaveOptions();
                        txtSaveOptions.setEncoding(StandardCharsets.UTF_8);
                        txtSaveOptions.setPreserveTableLayout(true);


                        //10. Save
                        editor.save(afterEdit, outputDocmPath.toString(), wordSaveOptions);
                        editor.save(afterEdit, outputTxtPath.toString(), txtSaveOptions);
                        //10. Dispose all resources
                    } finally {
                        afterEdit.dispose();
                    }
                } finally {
                    beforeEdit.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocuments saved successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return Arrays.asList(outputDocmPath, outputTxtPath);
    }
}
