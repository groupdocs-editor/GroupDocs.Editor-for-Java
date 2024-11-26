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
        final Path outputCsvPath = makeOutputPath("WorkingWithCsv.csv");
        final Path outputTsvPath = makeOutputPath("WorkingWithCsv.tsv");
        final Path outputXlsmPath = makeOutputPath("WorkingWithCsv.xlsm");
        try {
            // Create Editor instance
            Editor editor = new Editor(inputFile.toString());
            try {
                // Create CSV edit options and specify separator
                DelimitedTextEditOptions editOptions = new DelimitedTextEditOptions(",");
                editOptions.setConvertDateTimeData(false);
                editOptions.setConvertNumericData(true);
                editOptions.setTreatConsecutiveDelimitersAsOne(true);

                // Create EditableDocument instance for editing
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {
                    // Update document content: replace strings "SsangYong" with "Chevrolet", "Kyron" with "Camaro"
                    String originalTextContent = beforeEdit.getContent();
                    String updatedTextContent = originalTextContent.replace("SsangYong", "Chevrolet").replace("Kyron", "Camaro");
                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    // Create new EditableDocument with updated content
                    EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);
                    try {
                        // Configure save options for CSV and TSV formats, including encoding setup
                        DelimitedTextSaveOptions csvSaveOptions = new DelimitedTextSaveOptions(",");
                        // csvSaveOptions.TrimLeadingBlankRowAndColumn = true;
                        csvSaveOptions.setEncoding(StandardCharsets.UTF_8);

                        DelimitedTextSaveOptions tsvSaveOptions = new DelimitedTextSaveOptions("\t");
                        tsvSaveOptions.setTrimLeadingBlankRowAndColumn(false);
                        tsvSaveOptions.setEncoding(StandardCharsets.UTF_8);

                        // Create save options for Spreadsheet format
                        SpreadsheetSaveOptions cellsSaveOptions = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsm);

                        // Save edited document to 3 files with different formats
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
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return Arrays.asList(outputCsvPath, outputTsvPath, outputXlsmPath);
    }
}
