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
            EditableDocument document = EditableDocument.fromFile(inputFile.toString(), null);
            try {
                final Editor editor = new Editor(inputFile.toString());
                try {

                    WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
                    editor.save(document, outputPath.toString(), saveOptions);
                } finally {
                    editor.dispose();
                }
            } finally {
                document.dispose();
            }
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }

        return outputPath;
    }


    public static java.nio.file.Path fromInnerBody(java.nio.file.Path inputFile, java.nio.file.Path resourcesPath) {
        final java.nio.file.Path outputPath1 = makeOutputPath("CreateEditableDocument-fromHtmlFile.html");
        final java.nio.file.Path outputPath2 = makeOutputPath("CreateEditableDocument-fromHtmlFile.docm");
        try {
            //2. Read this markup to String
            String htmlContent = FileUtils.readFileToString(inputFile.toFile(), "utf-8");

            //4. Initialize EditableDocument
            EditableDocument document = EditableDocument.fromMarkupAndResourceFolder(htmlContent, resourcesPath.toString());
            try {
                //5. Check obtained document
                System.out.println("There should be 2 stylesheets, and actually is " + document.getCss().size());
                System.out.println("There should be 2 images, and actually is " + document.getImages().size());

                //6. Save it to the file
                document.save(outputPath1.toString());
                //7. Save it to the document
                //7.1. Get saving format
                WordProcessingFormats saveFormat = WordProcessingFormats.fromExtension("docm");
                //7.2. Get saving options
                WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(saveFormat);
                //7.3. Create instance of Editor class, initialize it with anything
                Editor editor = new Editor(outputPath1.toString());
                try {
                    editor.save(document, outputPath2.toString(), saveOptions);
                } finally {
                    editor.dispose();
                }
            } finally {
                document.dispose();
            }
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath1.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }

        return outputPath1;
    }
}
