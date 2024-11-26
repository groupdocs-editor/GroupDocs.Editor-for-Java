package com.groupdocs.examples.editor.advanced_usage.editable_document;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class CreateEditableDocument {
    public static Path fromHtmlFile(Path inputFile) {
        final java.nio.file.Path outputPath = makeOutputPath("CreateEditableDocument-fromHtmlFile.docx");

        try {
            // Create an EditableDocument from the input HTML file
            EditableDocument document = EditableDocument.fromFile(inputFile.toString(), null);
            try {
                // Initialize the editor for the input HTML file
                final Editor editor = new Editor(inputFile.toString());
                try {

                    // Define save options for Word processing in DOCX format
                    WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
                    // Save the edited document to the output path
                    editor.save(document, outputPath.toString(), saveOptions);
                } finally {
                    editor.dispose();
                }
            } finally {
                document.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }

        return outputPath;
    }


    public static java.nio.file.Path fromInnerBody(java.nio.file.Path inputFile, java.nio.file.Path resourcesPath) {
        final java.nio.file.Path outputPath1 = makeOutputPath("CreateEditableDocument-fromInnerBody.html");
        final java.nio.file.Path outputPath2 = makeOutputPath("CreateEditableDocument-fromInnerBody.docm");
        try {
            // Read the HTML content from the input file into a String
            String htmlContent = FileUtils.readFileToString(inputFile.toFile(), "utf-8");

            // Create an EditableDocument from the HTML content and resource folder
            EditableDocument document = EditableDocument.fromMarkupAndResourceFolder(htmlContent, resourcesPath.toString());
            try {
                System.out.println("Expected 2 stylesheets, found: " + document.getCss().size());
                System.out.println("Expected 2 images, found: " + document.getImages().size());

                // Save the document in HTML format
                document.save(outputPath1.toString());

                // Prepare to save the document in DOCM format
                WordProcessingFormats saveFormat = WordProcessingFormats.fromExtension("docm");
                WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(saveFormat);

                // Create an Editor instance to handle DOCM saving
                Editor editor = new Editor(outputPath1.toString());
                try {
                    editor.save(document, outputPath2.toString(), saveOptions);
                } finally {
                    editor.dispose();
                }
            } finally {
                document.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }

        return outputPath1;
    }
}
