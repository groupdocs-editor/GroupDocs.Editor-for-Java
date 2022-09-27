/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.IncorrectPasswordException;
import com.groupdocs.editor.PasswordRequiredException;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.internal.c.a.ms.System.IO.Path;
import com.groupdocs.editor.options.SpreadsheetEditOptions;
import com.groupdocs.editor.options.SpreadsheetLoadOptions;
import com.groupdocs.editor.options.SpreadsheetSaveOptions;
import com.groupdocs.editor.options.WorksheetProtection;
import com.groupdocs.editor.options.WorksheetProtectionType;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 *
 * @author AlexT
 */
public class WorkingWithSpreadsheetPasswordProtected {

    public static void run() throws Exception {
        //1. Get a path to the input file (or stream with file content).
        //In this case it is sample XLS (pre-OOXML) with one tab, and it is encoded with password. Let's try to open it.
        String inputFilePath = Constants.SAMPLE_XLS_PROTECTED;

        //1.1. First of all let's try to open document without password at all
        Editor editor = new Editor(inputFilePath);
        try {
            editor.edit();
        } catch (PasswordRequiredException ex) {
            System.out.println("Cannot edit a document, because it is password-protected, so the password is required");
        }
        editor.dispose();

        //1.2. Now let's try to specify incorrect password
        SpreadsheetLoadOptions loadOptions = new SpreadsheetLoadOptions();
        loadOptions.setPassword("incorrect_password");
        editor = new Editor(inputFilePath, loadOptions);
        try {
            editor.edit();
        } catch (IncorrectPasswordException ex) {
            System.out.println("Cannot edit a document, because it is password-protected, and password was specified, but it is incorrect");
        }

        editor.dispose();

        //1.3. Finally, let's open file with valid password
        loadOptions.setPassword("excel_password");

        //1.4. Also let's specify memory optimization option
        loadOptions.setOptimizeMemoryUsage(true);

        editor = new Editor(inputFilePath, loadOptions);

        //2. Create and adjust editing options
        SpreadsheetEditOptions editOptions = new SpreadsheetEditOptions();

        //3. Create intermediate EditableDocument
        EditableDocument beforeEdit = editor.edit(editOptions);
        //4. Create save options
        SpreadsheetFormats xlsmFormat = SpreadsheetFormats.Xlsm;
        SpreadsheetSaveOptions saveOptions = new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsm);

        //4.1. Set new opening password
        saveOptions.setPassword("new password");

        //4.2. Set write protection
        saveOptions.setWorksheetProtection(new WorksheetProtection(WorksheetProtectionType.All, "write password"));

        //5. Save document without modification
        //5.1. Prepare output filename and path
        String outputPath = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(inputFilePath)), xlsmFormat.getExtension());

        //5.2. Create output stream
        OutputStream outputStream = new ByteArrayOutputStream();
        //FileStream outputStream1 = File.create(outputPath)

        //5.3. Save
        editor.save(beforeEdit, outputStream, saveOptions);

        //6. Dispose Editor instance
        editor.dispose();

        System.out.println(String.format(
                "WorkingWithSpreadsheetPasswordProtected routine has successfully finished. Editor instance was manually " +
                        (editor.isDisposed() ? "disposed" : "NOT disposed")));
    }
}
