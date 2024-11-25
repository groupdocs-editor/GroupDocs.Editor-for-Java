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

public class HelloWorld {

    public static Path run(Path inputFile) {
        final Path outputPath = makeOutputPath("HelloWorld.docx");

        try {
            final Editor editor = new Editor(inputFile.toString());
            try {
                WordProcessingEditOptions editOptions = new WordProcessingEditOptions();
                EditableDocument originalDocument = editor.edit(editOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();
                    final List<IHtmlResource> allResources = originalDocument.getAllResources();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    System.out.println("htmlContent length: " + htmlContent.length());

                    System.out.println("allResources: ");
                    for (IHtmlResource resource : allResources) {
                        System.out.println("\t" + resource.getType().getFormalName() + ": " + resource.getName());
                    }

                    // Save changed document
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, allResources);
                    try {
                        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

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
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }

        return outputPath;
    }
}

