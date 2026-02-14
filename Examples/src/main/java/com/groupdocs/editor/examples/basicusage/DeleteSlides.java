package com.groupdocs.editor.examples.basicusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.PresentationFormats;
import com.groupdocs.editor.options.*;



public class DeleteSlides {
    public static void run() throws Exception
    {
        String inputFilePath = Constants.SAMPLE_PPTX_DELETE_SLIDES;

        // Load input presentation to the Editor and specify loading options
        Editor editor = new Editor(inputFilePath, new PresentationLoadOptions());
        try {
            // Prepare edit options and set 2nd slide to edit
            PresentationEditOptions editOptions = new PresentationEditOptions();
            editOptions.setShowHiddenSlides(true);
            editOptions.setSlideNumber(1); // 2nd slide to edit, here SlideNumber is 0-based (historical reasons)

            // generate EditableDocument with original content of 2nd slide
            EditableDocument slide2OpenedForEdit = editor.edit(editOptions);
            try {
                // Get the HTML-markup from the EditableDocument with original content
                String originalHtmlContentOf2ndSlide = slide2OpenedForEdit.getEmbeddedHtml();

                // emulate HTML content editing in WYSIWYG-editor in browser or somewhere else
                String editedHtmlContentOf2ndSlide =
                        originalHtmlContentOf2ndSlide.replace("Tips to be Covered", "Edited tips on 2nd slide");

                // generate EditableDocument with edited content of 2nd slide
                EditableDocument slide2AfterEdit = EditableDocument.fromMarkup(editedHtmlContentOf2ndSlide, null);
                try {
                    // prepare save options without deletions
                    PresentationSaveOptions saveOptionsWithoutDelete = new PresentationSaveOptions(PresentationFormats.Pptx);
                    // let it be the 2nd slide...
                    saveOptionsWithoutDelete.setSlideNumber(2); // here SlideNumber is 1-based
                    // ... and we also save the original 2nd slide, which is pushed to the 3rd position
                    saveOptionsWithoutDelete.setInsertAsNewSlide(true);

                    // So now the presentation must have 22 slides, not 21. Save it to file
                    String outputSlides_without_delete = Constants.getOutputDirectoryPath("Output22Slides-without-delete.pptx");
                    editor.save(slide2AfterEdit, outputSlides_without_delete, saveOptionsWithoutDelete);

                    // Create another save options, with deletions at this time
                    PresentationSaveOptions saveOptionsWithDelete = new PresentationSaveOptions(PresentationFormats.Pptx);
                    // let the new slide be 2nd for now, but after deleting it will become the 1st later
                    saveOptionsWithDelete.setSlideNumber(2);

                    // ... and we also save the original 2nd slide, which will be next sibling to the newly inserted
                    saveOptionsWithDelete.setInsertAsNewSlide(true);

                    // delete the 1st and last slide
                    saveOptionsWithDelete.setSlideNumbersToDelete(new int[]{
                            1,  // 1st slide
                            22  // last slide is 22 (because we inserted a new slide without rewriting the original 2nd slide)
                    });

                    // Save it again to distinct file. Output PPTX should have 20 slides now.
                    String outputSlides_with_delete = Constants.getOutputDirectoryPath("Output20Slides-with-delete.pptx");
                    editor.save(slide2AfterEdit, outputSlides_with_delete, saveOptionsWithDelete);
                } finally {
                    slide2AfterEdit.dispose();
                }
            } finally {
                slide2OpenedForEdit.dispose();
            }
        } finally {
            editor.dispose();
        }
    }
}
