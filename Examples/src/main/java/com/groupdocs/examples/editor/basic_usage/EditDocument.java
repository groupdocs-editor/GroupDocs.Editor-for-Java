package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.htmlcss.resources.images.IImageResource;
import com.groupdocs.editor.options.*;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.file.Path;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class EditDocument {

    public static void edit(Path inputFile) {
        final Path outputPath = makeOutputPath("EditableDocument-edit.docx");
        try {
            // Create Editor instance and load WordProcessing document
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {

                // Edit WordProcessing document with default options
                EditableDocument originalDocument = editor.edit();
                try {
                    // Get HTML content with embedded resources from the original document
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Get all resources from the original document
                    final List<IHtmlResource> allResources = originalDocument.getAllResources();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    System.out.println("htmlContent length: " + htmlContent.length());

                    System.out.println("allResources: ");
                    for (IHtmlResource resource : allResources) {
                        System.out.println("\t" + resource.getType().getFormalName() + ": " + resource.getName());
                    }

                    // Save changed document
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, allResources);
                    try {
                        // Save the edited document to the specified output path
                        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
                        editor.save(editedDocument, outputPath.toString(), saveOptions);
                    } finally {
                        editedDocument.dispose();
                    }
                } finally {
                    originalDocument.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void editWithSpecificOptions(Path inputFile) {
        final Path outputPath = makeOutputPath("EditableDocument-editWithSpecificOptions.docx");
        try {
            // Initialize the Editor with WordProcessingLoadOptions
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // Configure editing options for the WordProcessing document
                WordProcessingEditOptions wordProcessingEditOptions = new WordProcessingEditOptions();
                wordProcessingEditOptions.setEnablePagination(false);
                wordProcessingEditOptions.setEnableLanguageInformation(true);
                wordProcessingEditOptions.setFontExtraction(FontExtractionOptions.ExtractAllEmbedded);

                // Edit the document using the specified options
                EditableDocument originalDocument = editor.edit(wordProcessingEditOptions);
                try {
                    // Extract HTML content and resources for editing
                    String htmlContent = originalDocument.getEmbeddedHtml();
                    final List<IHtmlResource> allResources = originalDocument.getAllResources();

                    // Modify htmlContent using a WYSIWYG editor
                    // For demonstration, we print the content length
                    System.out.println("htmlContent length: " + htmlContent.length());

                    System.out.println("allResources: ");
                    for (IHtmlResource resource : allResources) {
                        System.out.println("\t" + resource.getType().getFormalName() + ": " + resource.getName());
                    }

                    // Create a new EditableDocument with updated content
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, allResources);
                    try {
                        // Save the edited document in DOCX format
                        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
                        editor.save(editedDocument, outputPath.toString(), saveOptions);
                    } finally {
                        editedDocument.dispose();
                    }
                } finally {
                    originalDocument.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }

    }

    public static void editWithDifferentSpecificOptions(Path inputFile) {
        final Path outputPath1 = makeOutputPath("EditableDocument-editWithDifferentSpecificOptions1.docx");
        final Path outputPath2 = makeOutputPath("EditableDocument-editWithDifferentSpecificOptions2.docx");
        try {
            // Initialize the Editor with WordProcessingLoadOptions
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // Configure editing options for the first document version
                WordProcessingEditOptions wordProcessingEditOptions1 = new WordProcessingEditOptions();
                wordProcessingEditOptions1.setEnablePagination(false);
                wordProcessingEditOptions1.setEnableLanguageInformation(true);
                wordProcessingEditOptions1.setFontExtraction(FontExtractionOptions.ExtractAllEmbedded);

                // Edit the document using the specified options
                EditableDocument originalDocument1 = editor.edit(wordProcessingEditOptions1);
                try {
                    String htmlContent1 = originalDocument1.getEmbeddedHtml();
                    final List<IHtmlResource> allResources1 = originalDocument1.getAllResources();

                    // Pass the htmlContent1 to a WYSIWYG editor for user modifications
                    // htmlContent1 = updatedHtmlContent;

                    System.out.println("htmlContent1 length: " + htmlContent1.length());

                    System.out.println("allResources1: ");
                    for (IHtmlResource resource : allResources1) {
                        System.out.println("\t" + resource.getType().getFormalName() + ": " + resource.getName());
                    }

                    // Create a new EditableDocument with updated content
                    EditableDocument editedDocument1 = EditableDocument.fromMarkup(htmlContent1, allResources1);
                    try {
                        WordProcessingSaveOptions saveOptions1 = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

                        // Save the edited document to the specified output path
                        editor.save(editedDocument1, outputPath1.toString(), saveOptions1);
                    } finally {
                        editedDocument1.dispose();
                    }
                } finally {
                    originalDocument1.dispose();
                }

                // Configure editing options for the second document version
                WordProcessingEditOptions wordProcessingEditOptions2 = new WordProcessingEditOptions(true);
                wordProcessingEditOptions2.setFontExtraction(FontExtractionOptions.ExtractAll);

                // Edit the document using the specified options
                EditableDocument originalDocument2 = editor.edit(wordProcessingEditOptions2);
                try {
                    String htmlContent2 = originalDocument2.getEmbeddedHtml();
                    final List<IHtmlResource> allResources2 = originalDocument2.getAllResources();

                    // Pass the htmlContent2 to a WYSIWYG editor for user modifications
                    // htmlContent2 = updatedHtmlContent;

                    System.out.println("htmlContent2 length: " + htmlContent2.length());

                    System.out.println("allResources2: ");
                    for (IHtmlResource resource : allResources2) {
                        System.out.println("\t" + resource.getType().getFormalName() + ": " + resource.getName());
                    }

                    // Create a new EditableDocument with updated content
                    EditableDocument editedDocument2 = EditableDocument.fromMarkup(htmlContent2, allResources2);
                    try {
                        WordProcessingSaveOptions saveOptions2 = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

                        // Save the edited document to the specified output path
                        editor.save(editedDocument2, outputPath2.toString(), saveOptions2);
                    } finally {
                        editedDocument2.dispose();
                    }
                } finally {
                    originalDocument2.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.\nCheck output: " + outputPath1.getParent());
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void editSpreadsheetTabs(Path inputFile) {
        try {
            // Load the document with SpreadsheetLoadOptions
            Editor editor = new Editor(inputFile.toString(), new SpreadsheetLoadOptions());
            try {

                // Edit 1st tab of this Spreadsheet
                SpreadsheetEditOptions sheetTab1EditOptions = new SpreadsheetEditOptions();
                // index is 0-based, so this is 1st tab
                sheetTab1EditOptions.setWorksheetIndex(0);

                // Create EditableDocument from the first tab
                EditableDocument tab1Document = editor.edit(sheetTab1EditOptions);
                try {
                    // Get HTML markup from inside the HTML->BODY element
                    String tab1BodyContent = tab1Document.getBodyContent();
                    // Get Full HTML markup of all document, with HTML->HEAD header and all its content
                    String tab1FullContent = tab1Document.getContent();
                    // Get all images from this tab
                    List<IImageResource> tab1Images = tab1Document.getImages();
                    // Get all resources (images, fonts, stylesheets) from this tab
                    List<IHtmlResource> tab1Resources = tab1Document.getAllResources();

                    System.out.println("tab1BodyContent length: " + tab1BodyContent.length());
                    System.out.println("tab1FullContent length: " + tab1FullContent.length());

                    System.out.println("tab1Images: ");
                    // List all images from the tab with their type and name
                    for (IImageResource image : tab1Images) {
                        System.out.println("\t" + image.getType() + ": " + image.getName() + ", Aspect Ratio: " + image.getAspectRatio());
                    }

                    System.out.println("tab1Resources: ");
                    // List all resources from the tab with their type and name
                    for (IHtmlResource resource : tab1Resources) {
                        System.out.println("\t" + resource.getType().getFormalName() + ": " + resource.getName());
                    }
                } finally {
                    tab1Document.dispose();
                }

                // Edit 2nd tab of this Spreadsheet
                SpreadsheetEditOptions sheetTab2EditOptions = new SpreadsheetEditOptions();
                // index is 0-based, so this is 2nd tab
                sheetTab2EditOptions.setWorksheetIndex(1);

                // Create EditableDocument from the second tab
                EditableDocument tab2Document = editor.edit(sheetTab2EditOptions);
                try {
                    // Get HTML markup from inside the HTML->BODY element
                    String tab2BodyContent = tab2Document.getBodyContent();
                    // Get Full HTML markup of all document, with HTML->HEAD header and all its content
                    String tab2FullContent = tab2Document.getContent();
                    // Get all images from this tab
                    List<IImageResource> tab2Images = tab2Document.getImages();
                    // Get all resources (images, fonts, stylesheets) from this tab
                    List<IHtmlResource> tab2Resources = tab2Document.getAllResources();

                    System.out.println("tab2BodyContent length: " + tab2BodyContent.length());
                    System.out.println("tab2FullContent length: " + tab2FullContent.length());

                    System.out.println("tab2Images: ");
                    // List all images from the tab with their type and name
                    for (IImageResource image : tab2Images) {
                        System.out.println("\t" + image.getType() + ": " + image.getName() + ", Aspect Ratio: " + image.getAspectRatio());
                    }

                    System.out.println("tab2Resources: ");
                    // List all resources from the tab with their type and name
                    for (IHtmlResource resource : tab2Resources) {
                        System.out.println("\t" + resource.getType().getFormalName() + ": " + resource.getName());
                    }
                } finally {
                    tab2Document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }
}
