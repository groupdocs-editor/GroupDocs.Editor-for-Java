package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.options.MarkdownEditOptions;
import com.groupdocs.editor.options.MarkdownSaveOptions;
import com.groupdocs.editor.options.MarkdownTableContentAlignment;
import org.testng.Assert;

public class MarkdownRoundtrip {
    public static void run(){

        String inputFolderPath = Constants.getSampleFilePath("Markdown");
        String outputFolder = Constants.getOutputDirectoryPath("");
        String outputMdPath = Constants.getOutputFilePath("MarkdownRoundtripOutput", "md");

        String inputPath = Constants.SAMPLE_MD;


        MarkdownEditOptions editOptions = new MarkdownEditOptions();
        editOptions.setImageLoadCallback(new EditingMarkdown.MdImageLoader(inputFolderPath));

        MarkdownSaveOptions saveOptions = new MarkdownSaveOptions();
        saveOptions.setTableContentAlignment(MarkdownTableContentAlignment.Center);
        saveOptions.setImagesFolder(outputFolder);

        Editor editor = new Editor(inputPath);
        {
            EditableDocument doc = editor.edit(editOptions);
            {
                Assert.assertEquals(3, doc.getImages().size());
                // edit "doc" in WYSIWYG-editor and obtain its edited version

                editor.save(doc, outputMdPath, saveOptions);
            }
        }
        editor.dispose();
        System.out.println("MarkdownRoundtrip routine has successfully finished");
    }
}
