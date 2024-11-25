/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.options.*;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/**
 * @author AlexT
 */
public class WorkingWithWordProcessing {

    public static java.nio.file.Path run(Path inputFile) {
        try (InputStream inputStream = Files.newInputStream(inputFile)) {
            //3. Create load options for this document
            WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();

            //3.1. In case if input document is password-protected, we can specify password for its opening...
            loadOptions.setPassword("some_password_to_open_a_document");
            //3.2. ...but, because document is unprotected, this password will be ignored

            //4. Load document with options to the Editor instance
            Editor editor = new Editor(inputStream, loadOptions);
            try {
                //5. Create editing options
                WordProcessingEditOptions editOptions = new WordProcessingEditOptions();

                //5.1. Enable font extraction from original document to intermediate EditableDocument
                editOptions.setFontExtraction(FontExtractionOptions.ExtractEmbeddedWithoutSystem);

                //5.2. Enable extracting language information for better subsequent spell-checking on client side
                editOptions.setEnableLanguageInformation(true);

                //5.3. Switch to pagination mode instead of default float mode
                editOptions.setEnablePagination(true);

                //6. Create intermediate EditableDocument
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {
                    //7. Extract textual content as HTML markup with all resources
                    String originalContent = beforeEdit.getContent();
                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    //8. Modify content somehow on client side
                    String editedContent = originalContent.replace("document", "edited document");

                    //9. Create new EditableDocument instance with edited content
                    EditableDocument afterEdit = EditableDocument.fromMarkup(editedContent, allResources);
                    try {
                        //10. Create document save options
                        WordProcessingFormats docmFormat = WordProcessingFormats.Docm;
                        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(docmFormat);

                        //10.1 Encrypt output document by setting non-null and non-empty opening password
                        saveOptions.setPassword("password");

                        //10.2. Because pagination was previously enabled in WordProcessingEditOptions (editOptions variable),
                        //for better output result it is highly recommended to enable it in WordProcessingSaveOptions (saveOptions variable)
                        saveOptions.setEnablePagination(true);

                        //10.3. You can set locale for output WordProcessing document manually
                        saveOptions.setLocale(Locale.US);

                        //10.4. If document is really huge and causes OutOfMemoryException, you can set memory optimization option
                        saveOptions.setOptimizeMemoryUsage(true);

                        //10.5. You can protect document from writing (make it read-only) with password
                        saveOptions.setProtection(new WordProcessingProtection(WordProcessingProtectionType.ReadOnly, "write_password"));

                        //11. Save it
                        //11.1. Prepare saving filename and path
                        final java.nio.file.Path outputPath = makeOutputPath("WorkingWithWordProcessing" + docmFormat.getExtension());


                        //11.3. Save
                        editor.save(afterEdit, outputPath.toString(), saveOptions);

                        System.out.println("\nDocuments saved successfully.");
                        return outputPath;
                    } finally {
                        afterEdit.dispose();
                    }
                } finally {
                    beforeEdit.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }
}
