package com.groupdocs.editor.examples.advancedusage.formfieldmanagerexamples;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.FormFieldManager;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingProtection;
import com.groupdocs.editor.options.WordProcessingProtectionType;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.editor.words.fieldmanagement.FormFieldCollection;
import com.groupdocs.editor.words.fieldmanagement.TextFormField;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * This example demonstrates how to edit form fields in a Word document using GroupDocs.Editor for Java.
 */
public class EditFormFieldCollection {
     /**
      * Runs the example to demonstrate loading, editing, and saving form fields in a document.
      */
    public static void run() throws Exception
    {
        // 1. Get a path to the input file (or stream with file content).
        // In this case, it is a sample DOCX with form fields.
        String inputFilePath = Constants.SampleLegacyFormFields_docx;

        // 2. Create a stream from this path
        InputStream fs = new FileInputStream(inputFilePath);

        // 3. Create load options for this document
        WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
        // 3.1. If the input document is password-protected, specify the password for its opening...
        loadOptions.setPassword("some_password_to_open_a_document");
        // 3.2. ...but, because the document is unprotected, this password will be ignored

        // 4. Load the document with options into the Editor instance
        Editor editor = new Editor(fs, loadOptions);

        // 4.1. Read the FormFieldManager instance
        FormFieldManager fieldManager = editor.getFormFieldManager();
        // 4.2. Read the FormFieldCollection in the document
        FormFieldCollection collection = fieldManager.getFormFieldCollection();

        // 4.3. Update a specific text form field
        TextFormField textField = collection.getFormField("Text1",TextFormField.class);
        textField.setLocaleId(1029);
        textField.setValue("new Value");
        fieldManager.updateFormFields(collection);

        // 5. Create document save options
        WordProcessingFormats docFormat = WordProcessingFormats.Docx;
        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(docFormat);

        // 5.1. If the document is large and causes OutOfMemoryException, set the memory optimization option
        saveOptions.setOptimizeMemoryUsage(true);

        // 5.2. Protect the document from writing (allow only form fields) with a password
        saveOptions.setProtection(new WordProcessingProtection(WordProcessingProtectionType.AllowOnlyFormFields, "write_password"));

        // 6. Save the document
        // 6.1. Prepare a stream for saving
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        {
            // 6.2. Save the document
            editor.save(outputStream, saveOptions);
        }


        System.out.println("EditFormFieldCollection routine has successfully finished");
    }
}
