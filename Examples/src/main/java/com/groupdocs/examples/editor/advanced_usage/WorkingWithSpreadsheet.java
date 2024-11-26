/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.options.SpreadsheetEditOptions;
import com.groupdocs.editor.options.SpreadsheetLoadOptions;
import com.groupdocs.editor.options.SpreadsheetSaveOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class WorkingWithSpreadsheet {

    public static List<java.nio.file.Path> run(Path inputFile) {
        final java.nio.file.Path outputXlsmPath = makeOutputPath("WorkingWithSpreadsheet.xlsm");
        final java.nio.file.Path outputXlsbPath = makeOutputPath("WorkingWithSpreadsheet.xlsb");

        try (InputStream inputStream = Files.newInputStream(inputFile)) {
            Editor editor = new Editor(inputStream, new SpreadsheetLoadOptions());
            try {
                // Create an EditableDocument from the first worksheet
                SpreadsheetEditOptions firstTabEditOptions = new SpreadsheetEditOptions();
                firstTabEditOptions.setWorksheetIndex(0);  // Index is 0-based
                EditableDocument firstTabDocument = editor.edit(firstTabEditOptions);
                try {
                    // Create an EditableDocument from the second worksheet
                    SpreadsheetEditOptions secondTabEditOptions = new SpreadsheetEditOptions();
                    secondTabEditOptions.setWorksheetIndex(1);  // Index is 0-based
                    EditableDocument secondTabDocument = editor.edit(secondTabEditOptions);
                    try {
                        // Save first worksheet from the EditableDocument to separate document
                        SpreadsheetSaveOptions firstTabSaveOptions = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsm);
                        editor.save(firstTabDocument, outputXlsmPath.toString(), firstTabSaveOptions);

                        // Save second worksheet from the EditableDocument to separate document
                        SpreadsheetSaveOptions saveOptions2 = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsb);
                        editor.save(secondTabDocument, outputXlsbPath.toString(), saveOptions2);
                    } finally {
                        secondTabDocument.dispose();
                    }
                } finally {
                    firstTabDocument.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocuments saved successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return Arrays.asList(outputXlsmPath, outputXlsbPath);
    }
}
