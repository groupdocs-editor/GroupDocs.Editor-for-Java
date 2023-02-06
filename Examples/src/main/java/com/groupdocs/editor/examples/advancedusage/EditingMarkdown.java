package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.*;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Files;

public class EditingMarkdown {
    public static void run(){
        //0. Prepare a sample file and resources folder
        String inputMdPath = Constants.SAMPLE_MD_EDIT;
        String imagesFolder = Constants.SAMPLE_MD_FOLDER;

        //1. Creating the edit options
        MarkdownEditOptions editOptions = new MarkdownEditOptions();
        editOptions.setImageLoadCallback(new MdImageLoader(imagesFolder));

        //2. Load to the Editor class
        Editor editor = new Editor(inputMdPath);

        //3. Generate an editable document
        EditableDocument beforeEdit = editor.edit(editOptions);

        //4. Make sure there are 2 images here
        Assert.assertEquals(2, beforeEdit.getImages().size());
        Assert.assertEquals("png", beforeEdit.getImages().get(0).getType().getFileExtension());
        Assert.assertEquals("jpeg", beforeEdit.getImages().get(1).getType().getFileExtension());

        String originalHtmlContent = beforeEdit.getEmbeddedHtml();

        //5. Send the 'originalHtmlContent' to the client-side WYSIWYG-editor,
        // obtain the edited version and create a new EditableDocument from it
        EditableDocument afterEdit = EditableDocument.fromMarkup(originalHtmlContent, null);

        //6. Make sure 2 images are still here
        Assert.assertEquals(2, afterEdit.getImages().size());
        Assert.assertEquals("png", afterEdit.getImages().get(0).getType().getFileExtension());
        Assert.assertEquals("jpeg", afterEdit.getImages().get(1).getType().getFileExtension());

        //7. Save to the DOCX, for example
        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
        String outputDocxPath = Constants.getOutputFilePath( "OutputEditingMarkdown", saveOptions.getOutputFormat().getExtension());

        editor.save(afterEdit, outputDocxPath, saveOptions);

        editor.dispose();
        System.out.println("EditingMarkdown routine has successfully finished");
    }

    static class MdImageLoader implements IMarkdownImageLoadCallback
    {
        private final String _imagesFolder;

        public MdImageLoader(String imagesFolder)
        {
            this._imagesFolder = imagesFolder;
        }

        public final byte processImage(MarkdownImageLoadArgs args)
        {
            File filePath = new File(this._imagesFolder, new File(args.getImageFileName()).getName());
            try
            {
                byte[] data = Files.readAllBytes(filePath.toPath());
                args.setData(data);
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
            return MarkdownImageLoadingAction.UserProvided;
        }
    }
}
