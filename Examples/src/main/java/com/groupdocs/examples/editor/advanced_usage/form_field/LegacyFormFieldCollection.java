package com.groupdocs.examples.editor.advanced_usage.form_field;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.FormFieldManager;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.words.fieldmanagement.*;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/**
 * This example demonstrates loading a FormFieldCollection and reading form fields.
 */
public class LegacyFormFieldCollection {
    /**
     * Runs the example to demonstrate loading, editing, and reading form fields from a document.
     */
    public static Path run(Path inputFile) {
        final Path outputPath = makeOutputPath("LegacyFormFieldCollection.docx");
        // 2. Create a stream from this path
        try (InputStream inputStream = Files.newInputStream(inputFile)) {

            // 3. Create load options for this document
            WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
            // 3.1. In case the input document is password-protected, we can specify a password for its opening...
            loadOptions.setPassword("some_password_to_open_a_document");
            // 3.2. ...but, because the document is unprotected, this password will be ignored

            // 4. Load the document with options to the Editor instance
            Editor editor = new Editor(inputStream, loadOptions);
            try {
                // 4.1. Read the FormFieldManager instance
                FormFieldManager fieldManager = editor.getFormFieldManager();
                // 4.2. Read the FormFieldCollection in the document
                FormFieldCollection collection = fieldManager.getFormFieldCollection();
                for (IFormField formField : collection) {
                    switch (formField.getType()) {
                        case FormFieldType.Text:
                            TextFormField textFormField = collection.getFormField(formField.getName(), TextFormField.class);
                            System.out.println("TextFormField detected, name: " + formField.getName() + ", value: " + textFormField.getValue());
                            break;
                        case FormFieldType.CheckBox:
                            CheckBoxForm checkBoxFormField = collection.getFormField(formField.getName(), CheckBoxForm.class);
                            System.out.println("CheckBoxForm detected, name: " + formField.getName() + ", value: " + checkBoxFormField.getValue());
                            break;
                        case FormFieldType.Date:
                            DateFormField dateFormField = collection.getFormField(formField.getName(), DateFormField.class);
                            System.out.println("DateFormField detected, name: " + formField.getName() + ", value: " + dateFormField.getValue());
                            break;
                        case FormFieldType.Number:
                            NumberFormField numberFormField = collection.getFormField(formField.getName(), NumberFormField.class);
                            System.out.println("NumberFormField detected, name: " + formField.getName() + ", value: " + numberFormField.getValue());
                            break;
                        case FormFieldType.DropDown:
                            DropDownFormField dropDownFormField = collection.getFormField(formField.getName(), DropDownFormField.class);
                            System.out.println("DropDownFormField detected, name: " + formField.getName() + ", value selected: " + dropDownFormField.getValue().get(dropDownFormField.getSelectedIndex()));
                            break;
                    }
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