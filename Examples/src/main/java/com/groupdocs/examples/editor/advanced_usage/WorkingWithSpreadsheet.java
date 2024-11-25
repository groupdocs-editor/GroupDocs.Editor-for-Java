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
        final java.nio.file.Path outputXlsmPath = makeOutputPath("WorkingWithText.xlsm");
        final java.nio.file.Path outputXlsbPath = makeOutputPath("WorkingWithText.xlsb");
        //1. Get a path to the input file (or stream with file content).
        //In this case it is sample XLSX (OOXML) with two tabs.
        try (InputStream inputStream = Files.newInputStream(inputFile)) {

            Editor editor = new Editor(inputStream, new SpreadsheetLoadOptions());
            try {

                //3. Let's create an intermediate EditableDocument from 1st tab
                SpreadsheetEditOptions firstTabEditOptions = new SpreadsheetEditOptions();
                firstTabEditOptions.setWorksheetIndex(0);//index is 0-based
                EditableDocument firstTabDocument = editor.edit(firstTabEditOptions);
                try {

                    //4. Let's create an intermediate EditableDocument from 2nd tab
                    SpreadsheetEditOptions secondTabEditOptions = new SpreadsheetEditOptions();
                    secondTabEditOptions.setWorksheetIndex(1);//index is 0-based
                    EditableDocument secondTabDocument = editor.edit(secondTabEditOptions);
                    try {

                        //5. Save first tab from EditableDocument #1 to separate document
                        SpreadsheetSaveOptions firstTabSaveOptions = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsm);

                        editor.save(firstTabDocument, outputXlsmPath.toString(), firstTabSaveOptions);

                        //6. Save second tab from EditableDocument #2 to separate document
                        SpreadsheetSaveOptions saveOptions2 = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsb);

                        editor.save(secondTabDocument, outputXlsbPath.toString(), saveOptions2);

                        //7. Dispose both EditableDocument instances
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
