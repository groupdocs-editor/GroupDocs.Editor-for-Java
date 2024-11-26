package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.*;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class WorkingWithMarkdown {
    public static Path run(Path inputFile, Path inputFolder) {
        final Path outputPath = makeOutputPath("WorkingWithMarkdown.docx");

        try {
            // Creating the edit options and setting image load callback
            MarkdownEditOptions editOptions = new MarkdownEditOptions();
            editOptions.setImageLoadCallback(new MdImageLoader(inputFolder));

            final Editor editor = new Editor(inputFile.toString());
            try {
                // Generate an editable document from the input file
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {
                    System.out.println("Images count is 2: " + (beforeEdit.getImages().size() == 2));
                    System.out.println("First image type is png: " + ("png".equals(beforeEdit.getImages().get(0).getType().getFileExtension())));
                    System.out.println("Second image type is jpeg: " + ("jpeg".equals(beforeEdit.getImages().get(1).getType().getFileExtension())));

                    String originalHtmlContent = beforeEdit.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    // Creating a new editable document from the edited HTML content
                    EditableDocument afterEdit = EditableDocument.fromMarkup(originalHtmlContent, null);

                    try {
                        System.out.println("Images count is 2: " + (afterEdit.getImages().size() == 2));
                        System.out.println("First image type is png: " + ("png".equals(afterEdit.getImages().get(0).getType().getFileExtension())));
                        System.out.println("Second image type is jpeg: " + ("jpeg".equals(afterEdit.getImages().get(1).getType().getFileExtension())));

                        // Saving the editable document to DOCX format at the output path
                        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
                        editor.save(afterEdit, outputPath.toString(), saveOptions);
                    } finally {
                        afterEdit.dispose();
                    }
                } finally {
                    beforeEdit.dispose();
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

    static class MdImageLoader implements IMarkdownImageLoadCallback {
        private final Path _imagesFolder;

        public MdImageLoader(Path imagesFolder) {
            this._imagesFolder = imagesFolder;
        }

        // Load image from the specified folder and return its data as a byte array
        @Override
        public final byte processImage(MarkdownImageLoadArgs args) {
            Path filePath = this._imagesFolder.resolve(Paths.get(args.getImageFileName()).getFileName());
            try {
                byte[] data = Files.readAllBytes(filePath);
                args.setData(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return MarkdownImageLoadingAction.UserProvided;
        }
    }
}
