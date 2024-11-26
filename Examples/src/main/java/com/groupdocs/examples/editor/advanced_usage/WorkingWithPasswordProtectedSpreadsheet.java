/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.IncorrectPasswordException;
import com.groupdocs.editor.PasswordRequiredException;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.options.*;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.file.Path;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class WorkingWithPasswordProtectedSpreadsheet {

    public static java.nio.file.Path run(Path inputFile) {
        try {
            // Attempt opening document without password
            Editor editorNoPassword = new Editor(inputFile.toString());
            try {
                editorNoPassword.edit();
            } catch (PasswordRequiredException ex) {
                System.out.println("Document requires a password to open");
            } finally {
                editorNoPassword.dispose();
            }

            // Attempt opening document with incorrect password
            SpreadsheetLoadOptions loadOptions = new SpreadsheetLoadOptions();
            loadOptions.setPassword("incorrect_password");
            Editor editorWrongPassword = new Editor(inputFile.toString(), loadOptions);
            try {
                editorWrongPassword.edit();
            } catch (IncorrectPasswordException ex) {
                System.out.println("Incorrect password specified for document");
            } finally {
                editorWrongPassword.dispose();
            }

            // Attempt opening document with valid password and memory optimization option set
            loadOptions.setPassword("excel_password");
            loadOptions.setOptimizeMemoryUsage(true);

            Editor editor = new Editor(inputFile.toString(), loadOptions);
            try {
                // Configure editing options
                SpreadsheetEditOptions editOptions = new SpreadsheetEditOptions();

                // Create editable document
                EditableDocument editableDocument = editor.edit(editOptions);
                java.nio.file.Path outputPath;
                try {
                    // Configure save options, including password and write protection settings
                    SpreadsheetFormats xlsmFormat = SpreadsheetFormats.Xlsm;
                    SpreadsheetSaveOptions saveOptions = new SpreadsheetSaveOptions(xlsmFormat);
                    saveOptions.setPassword("new password");
                    saveOptions.setWorksheetProtection(new WorksheetProtection(WorksheetProtectionType.All, "write password"));

                    // Save document and return output path
                    outputPath = makeOutputPath("WorkingWithPasswordProtectedSpreadsheet" + xlsmFormat.getExtension());
                    editor.save(editableDocument, outputPath.toString(), saveOptions);
                } finally {
                    editableDocument.dispose();
                }

                System.out.println("\nDocuments saved successfully.");
                return outputPath;
            } finally {
                editor.dispose();
                System.out.printf("Editor instance was %s disposed.%n", editor.isDisposed() ? "" : "NOT");
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }
}
