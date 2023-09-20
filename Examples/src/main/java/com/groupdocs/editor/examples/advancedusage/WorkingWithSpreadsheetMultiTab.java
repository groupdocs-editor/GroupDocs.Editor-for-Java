/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.internal.c.a.ms.System.IO.Path;
import com.groupdocs.editor.options.SpreadsheetEditOptions;
import com.groupdocs.editor.options.SpreadsheetLoadOptions;
import com.groupdocs.editor.options.SpreadsheetSaveOptions;

import java.io.*;

/**
 *
 * @author AlexT
 */
public class WorkingWithSpreadsheetMultiTab {

    public static void run() throws Exception {
        //1. Get a path to the input file (or stream with file content).
        //In this case it is sample XLSX (OOXML) with two tabs.
        String inputFilePath = Constants.SAMPLE_XLSX;

        //2. Load it into Editor instance from stream
        InputStream inputStream = new FileInputStream(inputFilePath);

        Editor editor = new Editor(inputStream, new SpreadsheetLoadOptions());

        //3. Let's create an intermediate EditableDocument from 1st tab
        SpreadsheetEditOptions editOptions1 = new SpreadsheetEditOptions();
        editOptions1.setWorksheetIndex(0);//index is 0-based
        EditableDocument firstTabBeforeEdit = editor.edit(editOptions1);

        //4. Let's create an intermediate EditableDocument from 2nd tab
        SpreadsheetEditOptions editOptions2 = new SpreadsheetEditOptions();
        editOptions2.setWorksheetIndex(1);//index is 0-based
        EditableDocument secondTabBeforeEdit = editor.edit(editOptions2);

        //5. Save first tab from EditableDocument #1 to separate document
        SpreadsheetSaveOptions saveOptions1 = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsm);
        String outputPath1 = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(inputFilePath)) + "_tab1", "xlsm");

        OutputStream outputStream = new ByteArrayOutputStream();
        //FileStream outputStream = File.create(outputPath)

        editor.save(firstTabBeforeEdit, outputPath1, saveOptions1);

        //6. Save second tab from EditableDocument #2 to separate document
        SpreadsheetSaveOptions saveOptions2 = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsb);
        String outputPath2 = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(inputFilePath)) + "_tab2", "xlsb");

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        //FileStream outputStream1 = File.create(outputPath)

        editor.save(secondTabBeforeEdit, outputPath2, saveOptions2);

        try(OutputStream outputFile = new FileOutputStream(outputPath2)) {
            outputStream1.writeTo(outputFile);
        }

        //7. Dispose both EditableDocument instances
        firstTabBeforeEdit.dispose();
        secondTabBeforeEdit.dispose();

        System.out.println("WorkingWithSpreadsheetMultiTab routine has successfully finished");
    }
}
