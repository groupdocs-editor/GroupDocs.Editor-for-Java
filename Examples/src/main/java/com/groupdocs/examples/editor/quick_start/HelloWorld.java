package com.groupdocs.examples.editor.quick_start;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.file.Path;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/**
 * This class demonstrates a basic usage of the GroupDocs.Editor API
 * to edit a word processing document by modifying its HTML content.
 */
public class HelloWorld {

    /**
     * Runs the editing process for a given input file path and returns the output file path.
     *
     * @param inputFile The path to the input document file.
     * @return The path to the edited document file.
     */
    public static Path run(Path inputFile) {
        final Path outputPath = makeOutputPath("HelloWorld.docx");

        try {
            // Initialize an Editor instance with the provided input file.
            final Editor editor = new Editor(inputFile.toString());
            try {
                WordProcessingEditOptions editOptions = new WordProcessingEditOptions();
                EditableDocument originalDocument = editor.edit(editOptions);
                try {
                    // Extract the HTML content from the original document.
                    String htmlContent = originalDocument.getContent();
                    final List<IHtmlResource> allResources = originalDocument.getAllResources();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    System.out.println("htmlContent length: " + htmlContent.length());

                    System.out.println("allResources: ");
                    for (IHtmlResource resource : allResources) {
                        System.out.println("\t" + resource.getType().getFormalName() + ": " + resource.getName());
                    }

                    // Create a new EditableDocument from the updated HTML content and resources.
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, allResources);
                    try {
                        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

                        // Save the edited document to the specified output path using the provided save options.
                        editor.save(editedDocument, outputPath.toString(), saveOptions);
                    } finally {
                        editedDocument.dispose();
                    }
                } finally {
                    originalDocument.dispose();
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