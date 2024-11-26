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

public class WorkingWithText {

    public static List<Path> run(Path inputFile) {
        final Path outputDocmPath = makeOutputPath("WorkingWithText.docm");
        final Path outputTxtPath = makeOutputPath("WorkingWithText.txt");
        try {
            // Create Editor instance to handle document operations
            Editor editor = new Editor(inputFile.toString());
            try {

                // Configure text editing options for UTF-8 encoding and list recognition
                TextEditOptions editOptions = new TextEditOptions();
                editOptions.setEncoding(StandardCharsets.UTF_8);
                editOptions.setRecognizeLists(true);
                editOptions.setLeadingSpaces(TextLeadingSpacesOptions.ConvertToIndent);
                editOptions.setTrailingSpaces(TextTrailingSpacesOptions.Trim);

                // Load the document into an EditableDocument for editing
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {

                    // Retrieve original text content
                    String originalTextContent = beforeEdit.getContent();

                    // Pass the originalTextContent to a WYSIWYG editor for editing ...
                    String updatedTextContent = originalTextContent.replace("text", "EDITED text");

                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    // Create a new EditableDocument with the modified content and resources
                    EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);
                    try {

                        // Set save options for DOCM format with US locale
                        WordProcessingSaveOptions wordSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docm);
                        wordSaveOptions.setLocale(Locale.US);

                        // Set save options for TXT format with UTF-8 encoding and table layout preservation
                        TextSaveOptions txtSaveOptions = new TextSaveOptions();
                        txtSaveOptions.setEncoding(StandardCharsets.UTF_8);
                        txtSaveOptions.setPreserveTableLayout(true);

                        // Save the document in both DOCM and TXT formats
                        editor.save(afterEdit, outputDocmPath.toString(), wordSaveOptions);
                        editor.save(afterEdit, outputTxtPath.toString(), txtSaveOptions);
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