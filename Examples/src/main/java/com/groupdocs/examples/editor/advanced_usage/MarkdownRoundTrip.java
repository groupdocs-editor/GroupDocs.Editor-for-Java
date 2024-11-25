package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.options.*;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class MarkdownRoundTrip {
    public static void run(Path inputFile, Path inputFolder) {
        final Path outputPath = makeOutputPath("MarkdownRoundTrip.md");
        final Path outputFolderPath = makeOutputPath("MarkdownRoundTrip");

        try {
            Files.createDirectories(outputFolderPath);

            MarkdownEditOptions editOptions = new MarkdownEditOptions();
            editOptions.setImageLoadCallback(new MdImageLoader(inputFolder));

            MarkdownSaveOptions saveOptions = new MarkdownSaveOptions();
            saveOptions.setTableContentAlignment(MarkdownTableContentAlignment.Center);
            saveOptions.setImagesFolder(outputFolderPath.toString());

            Editor editor = new Editor(inputFile.toString());
            try {
                EditableDocument editableDocument = editor.edit(editOptions);
                try {
                    System.out.println("Images count is 3: " + (editableDocument.getImages().size() == 3));
                    // edit "editableDocument" in WYSIWYG-editor and obtain its edited version

                    editor.save(editableDocument, outputPath.toString(), saveOptions);
                } finally {
                    editableDocument.dispose();
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
