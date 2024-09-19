package com.groupdocs.editor.examples.advancedusage.formfieldmanagerexamples;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.FormFieldManager;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.words.fieldmanagement.*;

import java.io.FileInputStream;
import java.io.InputStream;

/**
  * This example demonstrates loading a FormFieldCollection and reading form fields.
  */
public class LegacyFormFieldCollection {
   /**
    * Runs the example to demonstrate loading, editing, and reading form fields from a document.
    */
    public static void run() throws Exception
    {
        // 1. Get a path to the input file (or stream with file content).
        // In this case, it is a sample Docx with form fields.
        String inputFilePath = Constants.SampleLegacyFormFields_docx;

        // 2. Create a stream from this path
        InputStream fs = new FileInputStream(inputFilePath);

        // 3. Create load options for this document
        WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
        // 3.1. In case the input document is password-protected, we can specify a password for its opening...
        loadOptions.setPassword("some_password_to_open_a_document");
        // 3.2. ...but, because the document is unprotected, this password will be ignored

        // 4. Load the document with options to the Editor instance
        Editor editor = new Editor(fs, loadOptions);

        // 4.1. Read the FormFieldManager instance
        FormFieldManager fieldManager = editor.getFormFieldManager();
        // 4.2. Read the FormFieldCollection in the document
        FormFieldCollection collection = fieldManager.getFormFieldCollection();
        for (IFormField formField : collection)
        {
            switch (formField.getType())
            {
                case FormFieldType.Text:
                    TextFormField textFormField = collection.getFormField(formField.getName(),TextFormField.class);
                    System.out.println("TextFormField detected, name: "+formField.getName()+", value: "+ textFormField.getValue());
                    break;
                case FormFieldType.CheckBox:
                    CheckBoxForm checkBoxFormField = collection.getFormField(formField.getName(),CheckBoxForm.class);
                    System.out.println("CheckBoxForm detected, name: "+formField.getName()+", value: "+ checkBoxFormField.getValue());
                    break;
                case FormFieldType.Date:
                    DateFormField dateFormField = collection.getFormField(formField.getName(),DateFormField.class);
                    System.out.println("DateFormField detected, name: "+formField.getName()+", value: "+ dateFormField.getValue());
                    break;
                case FormFieldType.Number:
                    NumberFormField numberFormField = collection.getFormField(formField.getName(),NumberFormField.class);
                    System.out.println("NumberFormField detected, name: "+formField.getName()+", value: "+ numberFormField.getValue());
                    break;
                case FormFieldType.DropDown:
                    DropDownFormField dropDownFormField = collection.getFormField(formField.getName(),DropDownFormField.class);
                    System.out.println("DropDownFormField detected, name: "+formField.getName()+", value selected: "+ dropDownFormField.getValue().get(dropDownFormField.getSelectedIndex()));
                    break;
            }
        }

        System.out.println("ReadFormFieldCollection routine has successfully finished");
    }
}