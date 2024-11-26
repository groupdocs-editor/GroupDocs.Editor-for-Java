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

public class WorkingWithWordProcessing {

    public static java.nio.file.Path run(Path inputFile) {
        try (InputStream inputStream = Files.newInputStream(inputFile)) {
            // Create load options for this document
            WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();

            // In case the input document is password-protected, specify the password; however, since it's unprotected, this will be ignored.
            loadOptions.setPassword("some_password_to_open_a_document");

            // Load document with options to the Editor instance
            Editor editor = new Editor(inputStream, loadOptions);
            try {
                // Create editing options
                WordProcessingEditOptions editOptions = new WordProcessingEditOptions();

                // Enable font extraction from original document to intermediate EditableDocument
                editOptions.setFontExtraction(FontExtractionOptions.ExtractEmbeddedWithoutSystem);

                // Enable extracting language information for better spell-checking on client side
                editOptions.setEnableLanguageInformation(true);

                // Switch to pagination mode instead of default float mode
                editOptions.setEnablePagination(true);

                // Create intermediate EditableDocument
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {
                    // Extract textual content as HTML markup with all resources
                    String originalContent = beforeEdit.getContent();
                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    // Modify content on client side (example: replace "document" with "edited document")
                    String editedContent = originalContent.replace("document", "edited document");

                    // Create new EditableDocument instance with edited content
                    EditableDocument afterEdit = EditableDocument.fromMarkup(editedContent, allResources);
                    try {
                        // Create document save options
                        WordProcessingFormats docmFormat = WordProcessingFormats.Docm;
                        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(docmFormat);

                        // Encrypt output document by setting a non-null and non-empty opening password
                        saveOptions.setPassword("password");

                        // As pagination was enabled in WordProcessingEditOptions, enable it here for better output result
                        saveOptions.setEnablePagination(true);

                        // Set locale for the output WordProcessing document manually
                        saveOptions.setLocale(Locale.US);

                        // Enable memory optimization for large documents to prevent OutOfMemoryException
                        saveOptions.setOptimizeMemoryUsage(true);

                        // Protect the document from writing (make it read-only) with a password
                        saveOptions.setProtection(new WordProcessingProtection(WordProcessingProtectionType.ReadOnly, "write_password"));

                        final java.nio.file.Path outputPath = makeOutputPath("WorkingWithWordProcessing" + docmFormat.getExtension());

                        // Save the edited document
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
