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
        // 2. Create a stream from this path
        try (InputStream inputStream = Files.newInputStream(inputFile)) {

            // 3. Create load options for this document
            WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
            // 3.1. If the input document is password-protected, specify the password for its opening...
            loadOptions.setPassword("some_password_to_open_a_document");
            // 3.2. ...but, because the document is unprotected, this password will be ignored

            // 4. Load the document with options into the Editor instance
            Editor editor = new Editor(inputStream, loadOptions);
            try {
                // 4.1. Read the FormFieldManager instance
                FormFieldManager fieldManager = editor.getFormFieldManager();
                // 4.2. Read the FormFieldCollection in the document
                FormFieldCollection originalFormFieldCollection = fieldManager.getFormFieldCollection();

                // 4.3. Try to auto-fix invalid form fields
                fieldManager.fixInvalidFormFieldNames(new ArrayList<>());
                FormFieldCollection autoFixedFormFieldCollection = fieldManager.getFormFieldCollection();

                boolean hasInvalidFormFields = fieldManager.hasInvalidFormFields();
                System.out.println("FormFieldCollection contains invalid items: " + hasInvalidFormFields);

                // 4.4. Try to fix invalid form fields
                // 4.4.1. Get all invalid form field names
                Collection<InvalidFormField> invalidFormFields = fieldManager.getInvalidFormFieldNames();

                // 4.4.2. Create unique names for invalid fields
                for (InvalidFormField invalidItem : invalidFormFields) {
                    invalidItem.setFixedName(String.format("%s_%s", invalidItem.getName(), java.util.UUID.randomUUID()));
                }

                // 4.4.3. Fix invalid fields
                fieldManager.fixInvalidFormFieldNames(new ArrayList<>(invalidFormFields));
                FormFieldCollection manualFixedFormFieldCollection = fieldManager.getFormFieldCollection();

                // 5. Create document save options
                WordProcessingFormats docFormat = WordProcessingFormats.Docx;
                WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(docFormat);

                // 5.1. If the document is large and causes OutOfMemoryException, set the memory optimization option
                saveOptions.setOptimizeMemoryUsage(true);

                // 5.2. Protect the document from writing (allow only form fields) with a password
                saveOptions.setProtection(new WordProcessingProtection(WordProcessingProtectionType.AllowOnlyFormFields, "write_password"));

                // 6. Save the document
                // 6.1. Prepare a stream for saving
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
