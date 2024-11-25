/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.PresentationFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.options.PresentationEditOptions;
import com.groupdocs.editor.options.PresentationLoadOptions;
import com.groupdocs.editor.options.PresentationSaveOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class WorkingWithPresentations {

    public static Path run(Path inputPath) {
        try (InputStream inputStream = Files.newInputStream(inputPath)) {
            //3. Create load options for this document
            PresentationLoadOptions loadOptions = new PresentationLoadOptions();

            //3.1. In case if input document is password-protected, we can specify password for its opening...
            loadOptions.setPassword("some_password_to_open_a_document");
            //3.2. ...but, because document is unprotected, this password will be ignored

            //4. Load document with options to the Editor instance
            Editor editor = new Editor(inputStream, loadOptions);
            try {

                //5. Create editing options
                PresentationEditOptions editOptions = new PresentationEditOptions();

                //5.1. Specify slide index from original document.
                editOptions.setSlideNumber(0);//Because index is 0-based, it is 1st slide

                //5.2. Enable option that allows to show hidden slides in original document.
                editOptions.setShowHiddenSlides(true);

                //6. Create intermediate EditableDocument with editing options, so it is only 1st slide
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {

                    //7. Extract textual content as HTML markup with all resources
                    String originalContent = beforeEdit.getContent();
                    //7.1. Extract all resources from original document
                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    //8. Modify content somehow on client side
                    String editedContent = originalContent.replace("New text", "edited text");

                    //9. Create new EditableDocument instance with edited content and same old resources
                    EditableDocument afterEdit = EditableDocument.fromMarkup(editedContent, allResources);
                    try {

                        //10. Create document save options
                        PresentationSaveOptions saveOptions = new PresentationSaveOptions(PresentationFormats.Pptm);

                        //10.1 Encrypt output document by setting non-null and non-empty opening password
                        saveOptions.setPassword("password");

                        //11. Save it
                        //11.1. Prepare saving filename and path
                        final Path outputPath = makeOutputPath("WorkingWithEmail" + saveOptions.getOutputFormat().getExtension());

                        //11.3. Save
                        try (OutputStream outputStream = Files.newOutputStream(outputPath)) {
                            editor.save(afterEdit, outputStream, saveOptions);
                        }
                        System.out.println("\nDocuments saved successfully.");
                        return outputPath;
                    } finally {
                        afterEdit.dispose();
                    }
                } finally {
                    beforeEdit.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }
}
