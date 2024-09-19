package com.groupdocs.editor.examples.advancedusage.formfieldmanagerexamples;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.FormFieldManager;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingProtection;
import com.groupdocs.editor.options.WordProcessingProtectionType;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.editor.words.fieldmanagement.CheckBoxForm;
import com.groupdocs.editor.words.fieldmanagement.FormFieldCollection;
import com.groupdocs.editor.words.fieldmanagement.IFormField;
import com.groupdocs.editor.words.fieldmanagement.TextFormField;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * This example demonstrates loading, editing and saving Presentation documents with combination of different options and possibilities
 */
public class RemoveFormFieldCollection {
    public static void run() throws Exception
    {
        //1. Get a path to the input file (or stream with file content).
        //In this case it is sample Docx with three slides.
        String inputFilePath = Constants.SampleLegacyFormFields_docx;

        //2. Create stream from this path
        InputStream fs = new FileInputStream(inputFilePath);

        //3. Create load options for this document
        WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
        //3.1. In case if input document is password-protected, we can specify password for its opening...
        loadOptions.setPassword("some_password_to_open_a_document");
        //3.2. ...but, because document is unprotected, this password will be ignored

        //4. Load document with options to the Editor instance
        Editor editor = new Editor(fs, loadOptions);

        //4.1. read FormFieldManager instance
        FormFieldManager fieldManager = editor.getFormFieldManager();
        //4.1. read FormFieldCollection in the document.
        FormFieldCollection collection = fieldManager.getFormFieldCollection();

        //4.2. Remove a specific text form field
        TextFormField textField = collection.getFormField("Text1",TextFormField.class);
        fieldManager.removeFormField(textField);
        collection = fieldManager.getFormFieldCollection();

        //4.3. Remove multiple form fields
        textField = collection.getFormField("Text7",TextFormField.class);
        CheckBoxForm checkBoxForm = collection.getFormField("Check2",CheckBoxForm.class);
        fieldManager.removeFormFields(Arrays.asList(new IFormField[] { textField, checkBoxForm }));
        collection = fieldManager.getFormFieldCollection();

        //5. Create document save options
        WordProcessingFormats docFormat = WordProcessingFormats.Docx;
        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(docFormat);

        //5.1. If document is really huge and causes OutOfMemoryException, you can set memory optimization option
        saveOptions.setOptimizeMemoryUsage(true);

        //5.2. You can protect document from writing (make it Allow Only FormFields) with password
        saveOptions.setProtection(new WordProcessingProtection(WordProcessingProtectionType.AllowOnlyFormFields, "write_password"));

        //6. Save it
        //6.2. Prepare stream for saving
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        {
            //6.3. Save
            editor.save(outputStream, saveOptions);
        }

        System.out.println("RemoveFormFieldCollection routine has successfully finished");
    }
}
