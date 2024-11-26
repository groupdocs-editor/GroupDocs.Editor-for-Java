/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;


/**
 * @author AlexT
 */
public class SaveDocumentInDifferentFormats {

    public static List<Path> run(Path inputFile) {
        final List<Path> outputPaths = new ArrayList<>();
        try {

            //2. Create load options for this document
            WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();

            //3. Load document with options to the Editor instance
            Editor editor = new Editor(inputFile.toString(), loadOptions);
            try {
                //4. Create editing options
                WordProcessingEditOptions editOptions = new WordProcessingEditOptions();

                //5. Open document for editing by creating intermediate EditableDocument instance
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {

                    //6.1. Get document as a single base64-encoded String, where all resources (images, fonts, etc) are embedded inside this String along with main textual content
                    String allEmbeddedInsideString = beforeEdit.getEmbeddedHtml();
                    //6.2. For example, edit its content somehow
                    String allEmbeddedInsideStringEdited = allEmbeddedInsideString.replace("Subtitle", "Edited subtitle");

                    //7. Create new EditableDocument instance from edited content and resources
                    EditableDocument afterEdit = EditableDocument.fromMarkup(allEmbeddedInsideStringEdited, null);
                    try {

                        //8. Iterate over all supportable WordProcessing formats and save a document in some of this format at one step
                        for (WordProcessingFormats oneFormat : WordProcessingFormats.getAll()) {
                            //8.1. Prepare option class
                            WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(oneFormat);

                            //8.2. Prepare save path
                            final Path outputPath = makeOutputPath("SaveDocumentInDifferentFormats." + saveOptions.getOutputFormat().getExtension());

                            //8.3. Save to this path using save options
                            editor.save(afterEdit, outputPath.toString(), saveOptions);
                            outputPaths.add(outputPath);
                        }
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
        return outputPaths;
    }
}
