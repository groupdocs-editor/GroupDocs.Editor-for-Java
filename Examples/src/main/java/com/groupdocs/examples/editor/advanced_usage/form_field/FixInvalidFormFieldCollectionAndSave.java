package com.groupdocs.examples.editor.advanced_usage.form_field;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.FormFieldManager;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingProtection;
import com.groupdocs.editor.options.WordProcessingProtectionType;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.editor.words.fieldmanagement.FormFieldCollection;
import com.groupdocs.editor.words.fieldmanagement.InvalidFormField;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/**
 * This example demonstrates how to fix invalid form fields in a collection and save the document using GroupDocs.Editor for Java.
 */
public class FixInvalidFormFieldCollectionAndSave {
    /**
     * Runs the example to demonstrate loading, fixing invalid form fields, and saving a document.
     */
    public static Path run(Path inputFile) {
        final Path outputPath = makeOutputPath("FixInvalidFormFieldCollectionAndSave.docx");

        try (InputStream inputStream = Files.newInputStream(inputFile)) {
            // Loading options for the document
            WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
            loadOptions.setPassword("some_password_to_open_a_document");
            /* Since the document is unprotected, this password will be ignored */

            Editor editor = new Editor(inputStream, loadOptions);
            try {
                FormFieldManager fieldManager = editor.getFormFieldManager();
                /* Read original collection of form fields */
                FormFieldCollection originalFormFieldCollection = fieldManager.getFormFieldCollection();

                // Automatically attempt to fix invalid form fields and manually handle remaining ones
                fieldManager.fixInvalidFormFieldNames(new ArrayList<>());
                /* Read updated collection of form fields */
                FormFieldCollection autoFixedFormFieldCollection = fieldManager.getFormFieldCollection();

                boolean hasInvalidFormFields = fieldManager.hasInvalidFormFields();
                System.out.println("FormFieldCollection contains invalid items: " + hasInvalidFormFields);

                /* If there are still some invalid form fields, generate unique names for them and fix the items */
                if (hasInvalidFormFields) {
                    // Get all invalid form field names
                    Collection<InvalidFormField> invalidFormFields = fieldManager.getInvalidFormFieldNames();

                    // Create unique names for invalid fields
                    for (InvalidFormField invalidItem : invalidFormFields) {
                        invalidItem.setFixedName(String.format("%s_%s", invalidItem.getName(), java.util.UUID.randomUUID()));
                    }

                    // Fix invalid form fields using the generated unique names
                    fieldManager.fixInvalidFormFieldNames(new ArrayList<>(invalidFormFields));
                    FormFieldCollection manualFixedFormFieldCollection = fieldManager.getFormFieldCollection();
                }

                /* Configure save options for the document */
                WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

                // Optimize memory usage and protect the document from writing with a password
                saveOptions.setOptimizeMemoryUsage(true);
                saveOptions.setProtection(new WordProcessingProtection(WordProcessingProtectionType.AllowOnlyFormFields, "write_password"));

                // Save the document to the output path
                try (OutputStream outputStream = Files.newOutputStream(outputPath)) {
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
