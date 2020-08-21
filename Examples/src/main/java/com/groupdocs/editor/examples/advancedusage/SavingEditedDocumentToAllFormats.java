/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;

/**
 *
 * @author AlexT
 */
public class SavingEditedDocumentToAllFormats {

    public static void run() throws Exception {
        //1. Get a path to the input WordProcessing file (or stream with file content)
        String inputFilePath = Constants.SAMPLE_DOCX;

        //2. Create load options for this document
        WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();

        //3. Load document with options to the Editor instance
        Editor editor = new Editor(inputFilePath, loadOptions);
        //4. Create editing options
        WordProcessingEditOptions editOptions = new WordProcessingEditOptions();

        //5. Open document for editing by creating intermediate EditableDocument instance
        EditableDocument beforeEdit = editor.edit(editOptions);
        //6.1. Get document as a single base64-encoded String, where all resources (images, fonts, etc) are embedded inside this String along with main textual content
        String allEmbeddedInsideString = beforeEdit.getEmbeddedHtml();
        //6.2. For example, edit its content somehow
        String allEmbeddedInsideStringEdited = allEmbeddedInsideString.replace("Subtitle", "Edited subtitle");

        //7. Create new EditableDocument instance from edited content and resources
        EditableDocument afterEdit = EditableDocument.fromMarkup(allEmbeddedInsideStringEdited, null);

        //8. Iterate over all supportable WordProcessing formats and save a document in some of this format at one step
        for (WordProcessingFormats oneFormat : WordProcessingFormats.All) {
            //8.1. Prepare option class
            WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(oneFormat);

            //8.2. Prepare save path
            String savePath = Constants.getOutputFilePath("MultipleOutFormats", saveOptions.getOutputFormat().getExtension());

            //8.3. Save to this path using save options
            editor.save(afterEdit, savePath, saveOptions);
        }
        System.out.println("SavingEditedDocumentToAllFormats routine has successfully finished");
    }

}
