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

/**
 * @author AlexT
 */
public class WorkingWithPasswordProtectedSpreadsheet {

    public static java.nio.file.Path run(Path inputFile) {
        try {
            //1.1. First of all let's try to open document without password at all
            Editor editorNoPassword = new Editor(inputFile.toString());
            try {
                editorNoPassword.edit();
            } catch (PasswordRequiredException ex) {
                System.out.println("Cannot edit a document, because it is password-protected, so the password is required");
            } finally {

                editorNoPassword.dispose();
            }

            //1.2. Now let's try to specify incorrect password
            SpreadsheetLoadOptions loadOptions = new SpreadsheetLoadOptions();
            loadOptions.setPassword("incorrect_password");
            Editor editorWrongPassword = new Editor(inputFile.toString(), loadOptions);
            try {
                editorWrongPassword.edit();
            } catch (IncorrectPasswordException ex) {
                System.out.println("Cannot edit a document, because it is password-protected, and password was specified, but it is incorrect");
            } finally {
                editorWrongPassword.dispose();
            }


            //1.3. Finally, let's open file with valid password
            loadOptions.setPassword("excel_password");

            //1.4. Also let's specify memory optimization option
            loadOptions.setOptimizeMemoryUsage(true);

            Editor editor = new Editor(inputFile.toString(), loadOptions);
            try {
                //2. Create and adjust editing options
                SpreadsheetEditOptions editOptions = new SpreadsheetEditOptions();

                //3. Create intermediate EditableDocument
                EditableDocument editableDocument = editor.edit(editOptions);
                java.nio.file.Path outputPath;
                try {
                    //4. Create save options
                    SpreadsheetFormats xlsmFormat = SpreadsheetFormats.Xlsm;
                    SpreadsheetSaveOptions saveOptions = new SpreadsheetSaveOptions(xlsmFormat);

                    //4.1. Set new opening password
                    saveOptions.setPassword("new password");

                    //4.2. Set write protection
                    saveOptions.setWorksheetProtection(new WorksheetProtection(WorksheetProtectionType.All, "write password"));

                    //5. Save document without modification
                    //5.1. Prepare output filename and path
                    outputPath = makeOutputPath("WorkingWithPasswordProtectedSpreadsheet" + xlsmFormat.getExtension());

                    //5.3. Save
                    editor.save(editableDocument, outputPath.toString(), saveOptions);
                } finally {
                    editableDocument.dispose();
                }
                System.out.printf(
                        "WorkingWithSpreadsheetPasswordProtected routine has successfully finished. Editor instance was manually " +
                                (editor.isDisposed() ? "disposed" : "NOT disposed") + "%n");
                System.out.println("\nDocuments saved successfully.");
                return outputPath;
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }
}
