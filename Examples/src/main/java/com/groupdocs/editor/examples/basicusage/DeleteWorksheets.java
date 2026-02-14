package com.groupdocs.editor.examples.basicusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.options.SpreadsheetEditOptions;
import com.groupdocs.editor.options.SpreadsheetLoadOptions;
import com.groupdocs.editor.options.SpreadsheetSaveOptions;


public class DeleteWorksheets {
    public static void run() throws Exception {

        String inputFilePath = Constants.SAMPLE_XLSX_DELETE_WORKSHEETS;

        SpreadsheetLoadOptions loadOptions = new SpreadsheetLoadOptions();

        Editor editor = new Editor(inputFilePath, loadOptions);
        // Prepare edit options: open 2nd worksheet (WorksheetIndex is 0-based)
        SpreadsheetEditOptions editOptions = new SpreadsheetEditOptions();
        editOptions.setWorksheetIndex(1);

        try {

            EditableDocument worksheet2OpenedForEdit = editor.edit(editOptions);
            // Get embedded HTML of the worksheet
            String originalHtml = worksheet2OpenedForEdit.getEmbeddedHtml();

            // Emulate HTML edit
            String editedHtml = originalHtml.replace("2nd row", "Edited 2nd row at 1st column");

            // Build EditableDocument from edited markup
            try {
                EditableDocument worksheet2AfterEdit = EditableDocument.fromMarkup(editedHtml, null);
                // ---- Save #1: insert edited worksheet as new, without deleting anything
                SpreadsheetSaveOptions saveOptionsWithoutDelete = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsx);
                saveOptionsWithoutDelete.setWorksheetNumber(2);          // 1-based
                saveOptionsWithoutDelete.setInsertAsNewWorksheet(true);  // don't overwrite, insert new
                String outputWorksheets_without_delete = Constants.getOutputDirectoryPath("Output4Worksheets-without-delete.xlsx");
                editor.save(
                        worksheet2AfterEdit,
                        outputWorksheets_without_delete,
                        saveOptionsWithoutDelete
                );

                // ---- Save #2: insert edited worksheet as new, and delete 1st + last worksheet
                SpreadsheetSaveOptions saveOptionsWithDelete = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsx);
                saveOptionsWithDelete.setWorksheetNumber(2);            // 1-based
                saveOptionsWithDelete.setInsertAsNewWorksheet(true);

                // After insertion, total worksheets become 4 => delete #1 and #4
                saveOptionsWithDelete.setWorksheetNumbersToDelete(new int[]{1, 4});
                String outputWorksheets_with_delete = Constants.getOutputDirectoryPath("Output2Worksheets-with-delete.xlsx");
                editor.save(
                        worksheet2AfterEdit,
                        outputWorksheets_with_delete,
                        saveOptionsWithDelete
                );
            }finally {
                worksheet2OpenedForEdit.dispose();
            }
        }finally {
            editor.dispose();
        }

    }
}



