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

        try (InputStream inputStream = Files.newInputStream(inputFile)) {

            // Create load options for this document, including password if protected
            WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
            // Password set for opening if needed
            // Because document is unprotected, this password will be ignored
            loadOptions.setPassword("some_password_to_open_a_document");

            // Load document with options to the Editor instance
            Editor editor = new Editor(inputStream, loadOptions);
            try {
                // Retrieve the FormFieldManager instance
                FormFieldManager fieldManager = editor.getFormFieldManager();
                // Get the collection of form fields in the document
                FormFieldCollection collection = fieldManager.getFormFieldCollection();

                // Remove a specific text form field identified by its name
                TextFormField textField = collection.getFormField("Text1", TextFormField.class);
                fieldManager.removeFormField(textField);
                collection = fieldManager.getFormFieldCollection();

                // Remove multiple form fields by specifying their names
                textField = collection.getFormField("Text7", TextFormField.class);
                CheckBoxForm checkBoxForm = collection.getFormField("Check2", CheckBoxForm.class);
                fieldManager.removeFormFields(Arrays.asList(textField, checkBoxForm));
                collection = fieldManager.getFormFieldCollection();

                // Create document save options specifying the desired format
                WordProcessingFormats docFormat = WordProcessingFormats.Docx;
                WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(docFormat);

                // Optimize memory usage for large documents to avoid OutOfMemoryException
                saveOptions.setOptimizeMemoryUsage(true);

                // Protect document from writing, allowing only form fields to be edited
                saveOptions.setProtection(new WordProcessingProtection(WordProcessingProtectionType.AllowOnlyFormFields, "write_password"));

                try (OutputStream outputStream = Files.newOutputStream(outputPath)) {
                    // Save the document using the editor
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
