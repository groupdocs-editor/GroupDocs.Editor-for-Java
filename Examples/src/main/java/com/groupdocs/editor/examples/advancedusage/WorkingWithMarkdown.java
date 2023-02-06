package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.metadata.IDocumentInfo;
import com.groupdocs.editor.options.WordProcessingSaveOptions;

public class WorkingWithMarkdown {
    public static void run(){
        //0. Prepare a sample file
        String mdInputPath = Constants.SAMPLE_MD;

        //1. Load to the Editor class
        Editor mdEditor = new Editor(mdInputPath);

        //2. Get DocumentInfo
        IDocumentInfo info = mdEditor.getDocumentInfo(null);

        //3. Generate an editable document
        EditableDocument doc = mdEditor.edit();

        //4. Create save option
        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

        //5. Generate and save output Markdown to the file
        String outputPath = Constants.getOutputFilePath("OutputMd", saveOptions.getOutputFormat().getExtension());
        mdEditor.save(doc, outputPath, saveOptions);

        mdEditor.dispose();
        System.out.println("WorkingWithMarkdown routine has successfully finished");
    }
}
