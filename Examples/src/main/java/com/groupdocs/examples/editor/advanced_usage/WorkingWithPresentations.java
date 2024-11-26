/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.PresentationFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.options.PresentationEditOptions;
import com.groupdocs.editor.options.PresentationLoadOptions;
import com.groupdocs.editor.options.PresentationSaveOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class WorkingWithPresentations {

    public static Path run(Path inputPath) {
        try (InputStream inputStream = Files.newInputStream(inputPath)) {
            // Create load options for this document
            PresentationLoadOptions loadOptions = new PresentationLoadOptions();

            // In case the input document is password-protected, specify the password for opening it
            loadOptions.setPassword("some_password_to_open_a_document");

            // Load document with options to the Editor instance
            Editor editor = new Editor(inputStream, loadOptions);
            try {
                // Create editing options
                PresentationEditOptions editOptions = new PresentationEditOptions();

                // Specify slide index from original document (0-based)
                editOptions.setSlideNumber(0);

                // Enable option to show hidden slides in original document.
                editOptions.setShowHiddenSlides(true);

                // Extract textual content as HTML markup with all resources from the document
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {
                    // Extract textual content as HTML markup with all resources
                    String originalContent = beforeEdit.getContent();
                    // Extract all resources from original document
                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    // Modify content as per the requirements (replace "New text" with "edited text")
                    String editedContent = originalContent.replace("New text", "edited text");

                    // Create a new EditableDocument instance with edited content and same old resources
                    EditableDocument afterEdit = EditableDocument.fromMarkup(editedContent, allResources);
                    try {
                        // Create document save options and set output format as PPTM
                        PresentationSaveOptions saveOptions = new PresentationSaveOptions(PresentationFormats.Pptm);

                        // Encrypt the output document by setting a non-null and non-empty opening password
                        saveOptions.setPassword("password");

                        // Prepare saving filename and path, then save the document
                        final Path outputPath = makeOutputPath("WorkingWithPresentations" + saveOptions.getOutputFormat().getExtension());
                        try (OutputStream outputStream = Files.newOutputStream(outputPath)) {
                            editor.save(afterEdit, outputStream, saveOptions);
                        }
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
