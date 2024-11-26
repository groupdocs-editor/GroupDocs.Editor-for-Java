package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.TextSaveOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class SaveDocument {

    public static Path asRtfThroughFile(Path inputFile) {
        final Path outputPath = makeOutputPath("SaveDocument-asRtfThroughFile.rtf");
        try {
            // Initialize the Editor with WordProcessingLoadOptions
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // Create EditableDocument instance for editing
                EditableDocument originalDocument = editor.edit();

                // Simulate document editing by modifying HTML content
                String allEmbeddedInsideString = originalDocument.getEmbeddedHtml();
                String allEmbeddedInsideStringEdited = allEmbeddedInsideString.replace("Subtitle", "Edited subtitle");

                // Create a new EditableDocument instance from modified HTML content
                EditableDocument editedDocument = EditableDocument.fromMarkup(allEmbeddedInsideStringEdited, null);
                try {
                    // Save the edited document as RTF format to the specified output path
                    WordProcessingSaveOptions rtfSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Rtf);
                    try {
                        // Save the edited document in RTF format
                        editor.save(editedDocument, outputPath.toString(), rtfSaveOptions);
                    } finally {
                        editedDocument.dispose();
                    }
                } finally {
                    originalDocument.dispose();
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

    public static Path asDocmThroughStream(Path inputFile) {
        final Path outputPath = makeOutputPath("SaveDocument-asDocmThroughStream.docm");
        try {
            // Initialize the Editor with WordProcessingLoadOptions
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument originalDocument = editor.edit();
                try {

                    // Simulate document editing by modifying HTML content
                    String allEmbeddedInsideString = originalDocument.getEmbeddedHtml();
                    String allEmbeddedInsideStringEdited = allEmbeddedInsideString.replace("Subtitle", "Edited subtitle");

                    // Create a new EditableDocument instance from modified HTML content
                    EditableDocument editedDocument = EditableDocument.fromMarkup(allEmbeddedInsideStringEdited, null);

                    // Save the edited document as DOCM format to the specified output path
                    WordProcessingSaveOptions docmSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docm);

                    // Save the edited document as DOCM, specified through stream
                    try (OutputStream outputStream = Files.newOutputStream(outputPath)) {
                        editor.save(editedDocument, outputStream, docmSaveOptions);
                    } finally {
                        editedDocument.dispose();
                    }
                } finally {
                    originalDocument.dispose();
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

    public static Path asTxtThroughFile(Path inputFile) {
        final Path outputPath = makeOutputPath("SaveDocument-asTxtThroughFile.txt");
        try {
            // Initialize the Editor with WordProcessingLoadOptions
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument originalDocument = editor.edit();
                try {

                    // Modify its content somehow. Because there is no attached WYSIWYG-editor, this code simulates document editing
                    String allEmbeddedInsideString = originalDocument.getEmbeddedHtml();
                    String allEmbeddedInsideStringEdited = allEmbeddedInsideString.replace("Subtitle", "Edited subtitle");//Modified version

                    // Create new EditableDocument instance from modified HTML content
                    EditableDocument editedDocument = EditableDocument.fromMarkup(allEmbeddedInsideStringEdited, null);
                    try {

                        // Save edited document as plain text, specified through file path.
                        // Note that all complex content and resources (like images and fonts) will be missed in output TXT
                        TextSaveOptions textSaveOptions = new TextSaveOptions();
                        textSaveOptions.setEncoding(StandardCharsets.UTF_8);
                        textSaveOptions.setPreserveTableLayout(true);

                        // Save the edited document in TXT format
                        editor.save(editedDocument, outputPath.toString(), textSaveOptions);
                    } finally {
                        editedDocument.dispose();
                    }
                } finally {
                    originalDocument.dispose();
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
