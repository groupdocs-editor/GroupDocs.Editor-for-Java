package com.groupdocs.examples.editor.advanced_usage.form_field;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.FormFieldManager;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingProtection;
import com.groupdocs.editor.options.WordProcessingProtectionType;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.editor.words.fieldmanagement.CheckBoxForm;
import com.groupdocs.editor.words.fieldmanagement.FormFieldCollection;
import com.groupdocs.editor.words.fieldmanagement.TextFormField;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/**
 * This example demonstrates loading, editing and saving Presentation documents with combination of different options and possibilities
 */
public class RemoveFormFieldCollection {
    public static Path run(Path inputFile) {

        final Path outputPath = makeOutputPath("RemoveFormFieldCollection.docx");
        // 2. Create a stream from this path
        try (InputStream inputStream = Files.newInputStream(inputFile)) {

            //3. Create load options for this document
            WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
            //3.1. In case if input document is password-protected, we can specify password for its opening...
            loadOptions.setPassword("some_password_to_open_a_document");
            //3.2. ...but, because document is unprotected, this password will be ignored

            //4. Load document with options to the Editor instance
            Editor editor = new Editor(inputStream, loadOptions);
            try {
                //4.1. read FormFieldManager instance
                FormFieldManager fieldManager = editor.getFormFieldManager();
                //4.1. read FormFieldCollection in the document.
                FormFieldCollection collection = fieldManager.getFormFieldCollection();

                //4.2. Remove a specific text form field
                TextFormField textField = collection.getFormField("Text1", TextFormField.class);
                fieldManager.removeFormField(textField);
                collection = fieldManager.getFormFieldCollection();

                //4.3. Remove multiple form fields
                textField = collection.getFormField("Text7", TextFormField.class);
                CheckBoxForm checkBoxForm = collection.getFormField("Check2", CheckBoxForm.class);
                fieldManager.removeFormFields(Arrays.asList(textField, checkBoxForm));
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
                try (OutputStream outputStream = Files.newOutputStream(outputPath)) {
                    // 6.2. Save the document
                    editor.save(outputStream, saveOptions);
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return outputPath;
    }
}
