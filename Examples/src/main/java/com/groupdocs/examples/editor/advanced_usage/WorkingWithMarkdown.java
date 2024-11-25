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
    public static void run(Path inputFile, Path inputFolder) {
        final Path outputPath = makeOutputPath("WorkingWithMarkdown.docx");

        try {
            //1. Creating the edit options
            MarkdownEditOptions editOptions = new MarkdownEditOptions();
            editOptions.setImageLoadCallback(new MdImageLoader(inputFolder));

            final Editor editor = new Editor(inputFile.toString());
            try {

                //3. Generate an editable document
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {
                    //4. Make sure there are 2 images here
                    System.out.println("Images count is 2: " + (beforeEdit.getImages().size() == 2));
                    System.out.println("First image type is png: " + ("png".equals(beforeEdit.getImages().get(0).getType().getFileExtension())));
                    System.out.println("Second image type is png: " + ("jpeg".equals(beforeEdit.getImages().get(1).getType().getFileExtension())));

                    String originalHtmlContent = beforeEdit.getEmbeddedHtml();

                    //5. Send the 'originalHtmlContent' to the client-side WYSIWYG-editor,
                    // obtain the edited version and create a new EditableDocument from it
                    EditableDocument afterEdit = EditableDocument.fromMarkup(originalHtmlContent, null);

                    try {
                        //6. Make sure 2 images are still here
                        System.out.println("Images count is 2: " + (afterEdit.getImages().size() == 2));
                        System.out.println("First image type is png: " + ("png".equals(afterEdit.getImages().get(0).getType().getFileExtension())));
                        System.out.println("Second image type is png: " + ("jpeg".equals(afterEdit.getImages().get(1).getType().getFileExtension())));

                        //7. Save to the DOCX, for example
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
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    static class MdImageLoader implements IMarkdownImageLoadCallback {
        private final Path _imagesFolder;

        public MdImageLoader(Path imagesFolder) {
            this._imagesFolder = imagesFolder;
        }

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
