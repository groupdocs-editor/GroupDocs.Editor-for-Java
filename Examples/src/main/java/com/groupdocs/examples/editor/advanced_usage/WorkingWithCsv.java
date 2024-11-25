/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.options.DelimitedTextEditOptions;
import com.groupdocs.editor.options.DelimitedTextSaveOptions;
import com.groupdocs.editor.options.SpreadsheetSaveOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/**
 * @author AlexT
 */
public class WorkingWithCsv {

    public static List<Path> run(Path inputFile) {
        final Path outputCsvPath = makeOutputPath("WorkingWithDsv.csv");
        final Path outputTsvPath = makeOutputPath("WorkingWithDsv.tsv");
        final Path outputXlsmPath = makeOutputPath("WorkingWithDsv.xlsm");
        try {
            //2. Create Editor instance (not load options required)
            Editor editor = new Editor(inputFile.toString());
            try {

                //3. Create DSV edit options and specify mandatory parameter - separator (delimiter)
                DelimitedTextEditOptions editOptions = new DelimitedTextEditOptions(",");
                editOptions.setConvertDateTimeData(false);
                editOptions.setConvertNumericData(true);
                editOptions.setTreatConsecutiveDelimitersAsOne(true);

                //4. Create EditableDocument instance
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {

                    //5. Edit is somehow (just for example)
                    String originalTextContent = beforeEdit.getContent();
                    String updatedTextContent = originalTextContent.replace("SsangYong", "Chevrolet").replace("Kyron", "Camaro");
                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    //6. Create EditableDocument with updated content
                    EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);
                    try {

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


                        //11. Save edited document to 3 files of different formats
                        editor.save(afterEdit, outputCsvPath.toString(), csvSaveOptions);
                        editor.save(afterEdit, outputTsvPath.toString(), tsvSaveOptions);
                        editor.save(afterEdit, outputXlsmPath.toString(), cellsSaveOptions);
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
        return Arrays.asList(outputCsvPath, outputTsvPath, outputXlsmPath);
    }
}
