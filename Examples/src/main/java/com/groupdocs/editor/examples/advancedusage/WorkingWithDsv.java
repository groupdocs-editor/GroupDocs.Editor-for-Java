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
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.internal.c.a.ms.System.IO.Path;
import com.groupdocs.editor.options.DelimitedTextEditOptions;
import com.groupdocs.editor.options.DelimitedTextSaveOptions;
import com.groupdocs.editor.options.SpreadsheetSaveOptions;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author AlexT
 */
public class WorkingWithDsv {

    public static void run() throws Exception {
        //1. Get a path to the input DSV file. In our case it is CSV
        String inputFilePath = Constants.SAMPLE_CSV;
        String fileName = Constants.removeExtension(Path.getFileName(inputFilePath));
        //2. Create Editor instance (not load options required)
        Editor editor = new Editor(inputFilePath);

        //3. Create DSV edit options and specify mandatory parameter - separator (delimiter)
        DelimitedTextEditOptions editOptions = new DelimitedTextEditOptions(",");
        editOptions.setConvertDateTimeData(false);
        editOptions.setConvertNumericData(true);
        editOptions.setTreatConsecutiveDelimitersAsOne(true);

        //4. Create EditableDocument instance
        EditableDocument beforeEdit = editor.edit(editOptions);

        //5. Edit is somehow (just for example)
        String originalTextContent = beforeEdit.getContent();
        String updatedTextContent = originalTextContent.replace("SsangYong", "Chevrolet").replace("Kyron", "Camaro");
        List<IHtmlResource> allResources = beforeEdit.getAllResources();

        //6. Create EditableDocument with updated content
        EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);

        //7. Create CSV save options and specify mandatory separator (delimiter) - comma
        DelimitedTextSaveOptions csvSaveOptions = new DelimitedTextSaveOptions(",");
        //csvSaveOptions.TrimLeadingBlankRowAndColumn = true;
        csvSaveOptions.setEncoding(StandardCharsets.UTF_8);

        //8. Create TSV save options and specify mandatory separator (delimiter) - tab character
        DelimitedTextSaveOptions tsvSaveOptions = new DelimitedTextSaveOptions("\t");
        tsvSaveOptions.setTrimLeadingBlankRowAndColumn(false);
        tsvSaveOptions.setEncoding(StandardCharsets.UTF_8);

        //9. Create Spreadsheet save options
        SpreadsheetSaveOptions cellsSaveOptions = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsm);

        //10. Prepare 3 different save paths
        String outputCsvPath = Constants.getOutputFilePath(fileName, "csv");

        String outputTsvPath = Constants.getOutputFilePath(fileName, "tsv");

        String outputCellsPath = Constants.getOutputFilePath(fileName, "xlsm");

        //11. Save edited document to 3 files of different formats
        editor.save(afterEdit, outputCsvPath, csvSaveOptions);
        editor.save(afterEdit, outputTsvPath, tsvSaveOptions);
        editor.save(afterEdit, outputCellsPath, cellsSaveOptions);

        //12. Don't forget to dispose EditableDocument instances manually, because they are not wrapped with "using" operator
        beforeEdit.dispose();
        afterEdit.dispose();

        System.out.println("WorkingWithDsv routine has successfully finished");
    }
}
