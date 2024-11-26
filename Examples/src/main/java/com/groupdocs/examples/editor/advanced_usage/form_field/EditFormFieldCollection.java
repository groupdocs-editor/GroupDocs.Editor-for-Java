package com.groupdocs.examples.editor.advanced_usage.form_field;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.FormFieldManager;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingProtection;
import com.groupdocs.editor.options.WordProcessingProtectionType;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.editor.words.fieldmanagement.FormFieldCollection;
import com.groupdocs.editor.words.fieldmanagement.TextFormField;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/**
 * This example demonstrates how to edit form fields in a Word document using GroupDocs.Editor for Java.
 */
public class EditFormFieldCollection {
    /**
     * Runs the example to demonstrate loading, editing, and saving form fields in a document.
     */
    public static Path run(Path inputFile) {
        final Path outputPath = makeOutputPath("EditFormFieldCollection.docx");

        try (InputStream inputStream = Files.newInputStream(inputFile)) {
            // Initialize load options and set a password (for protected documents, otherwise it's ignored)
            WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
            loadOptions.setPassword("some_password_to_open_a_document");

            // Load the document into an editor context
            Editor editor = new Editor(inputStream, loadOptions);
            try {
                // Access FormFieldManager and get all form fields in the document
                FormFieldManager fieldManager = editor.getFormFieldManager();
                FormFieldCollection collection = fieldManager.getFormFieldCollection();

                // Update a specific text form field's value and locale ID
                TextFormField textField = collection.getFormField("Text1", TextFormField.class);
                textField.setLocaleId(1029);
                textField.setValue("new Value");
                // Persist the modified form fields in the document
                fieldManager.updateFormFields(collection);

                // Configure save options for the output document
                WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
                saveOptions.setOptimizeMemoryUsage(true); // Helpful for large documents to avoid OOM exceptions

                // Set a write-only protection on the output document using a password
                saveOptions.setProtection(new WordProcessingProtection(WordProcessingProtectionType.AllowOnlyFormFields, "write_password"));

                // Save the modified document to disk
                try (OutputStream outputStream = Files.newOutputStream(outputPath)) {
                    editor.save(outputStream, saveOptions);
                }
            } finally {
                editor.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return outputPath;
    }
}
