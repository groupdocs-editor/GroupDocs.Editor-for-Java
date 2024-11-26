package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.EmailFormats;
import com.groupdocs.editor.formats.PresentationFormats;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.options.*;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.file.Path;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

/*
 * This example demonstrates how to create new documents by format in GroupDocs.Editor and apply edit options.
 */
public class CreateDocument {
    public static Path createWordProcessing() {
        final Path outputPath = makeOutputPath("CreateDocument-createWordProcessing.docx");
        try {
            // Create new WordProcessing document from blank template
            final Editor editor = new Editor(WordProcessingFormats.Docx);
            try {
                // Create EditableDocument instance for editing
                EditableDocument originalDocument = editor.edit();
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
                        // Save edited document to a file
                        editor.save(editedDocument, outputPath.toString(), new WordProcessingSaveOptions(WordProcessingFormats.Docx));
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
        return outputPath;
    }

    public static Path createWordProcessingWithOptions() {
        final Path outputPath = makeOutputPath("CreateDocument-createWordProcessingWithOptions.docx");

        try {
            final Editor editor = new Editor(WordProcessingFormats.Docx);
            try {

                // Edit options for WordProcessing documents
                WordProcessingEditOptions wordProcessingEditOptions = new WordProcessingEditOptions();
                wordProcessingEditOptions.setEnablePagination(false);
                wordProcessingEditOptions.setEnableLanguageInformation(true);
                wordProcessingEditOptions.setFontExtraction(FontExtractionOptions.ExtractAllEmbedded);

                // Create EditableDocument instance for editing
                EditableDocument originalDocument = editor.edit(wordProcessingEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    // Create new EditableDocument with updated content
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
                        // Save the edited document to the specified output path
                        editor.save(editedDocument, outputPath.toString(), new WordProcessingSaveOptions(WordProcessingFormats.Docx));
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
        return outputPath;
    }

    public static Path createSpreadsheet() {
        final Path outputPath = makeOutputPath("CreateDocument-createSpreadsheet.xlsx");

        try {
            final Editor editor = new Editor(SpreadsheetFormats.Xlsx);
            try {
                // Create EditableDocument instance for editing
                EditableDocument originalDocument = editor.edit();
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing
                    // htmlContent = updatedHtmlContent;

                    // Save edited document to file
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
                        // Save the edited document to the specified output path
                        editor.save(editedDocument, outputPath.toString(), new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsx));
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
        return outputPath;
    }

    public static Path createSpreadsheetWithOptions() {
        final Path outputPath = makeOutputPath("CreateDocument-createSpreadsheetWithOptions.xlsx");

        try {
            // Initialize Editor with the XLSX format
            final Editor editor = new Editor(SpreadsheetFormats.Xlsx);
            try {
                // Configure edit options for the spreadsheet
                SpreadsheetEditOptions spreadsheetEditOptions = new SpreadsheetEditOptions();
                spreadsheetEditOptions.setWorksheetIndex(0);
                spreadsheetEditOptions.setExcludeHiddenWorksheets(true);

                // Obtain an editable document with the specified options
                EditableDocument originalDocument = editor.edit(spreadsheetEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing
                    // htmlContent = updatedHtmlContent;

                    // Re-create EditableDocument from updated HTML content and original resources
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
                        // Save the edited document to the output path in XLSX format
                        editor.save(editedDocument, outputPath.toString(), new SpreadsheetSaveOptions(SpreadsheetFormats.Xlsx));
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

        return outputPath;
    }

    public static Path createPresentation() {
        final Path outputPath = makeOutputPath("CreateDocument-createPresentation.pptx");

        try {
            // Initialize Editor with the PPTX format
            final Editor editor = new Editor(PresentationFormats.Pptx);
            try {

                // Obtain an editable document
                EditableDocument originalDocument = editor.edit();
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing
                    // htmlContent = updatedHtmlContent;

                    // Re-create EditableDocument from updated HTML content and original resources
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
                        // Save the edited document to the output path in PPTX format
                        editor.save(editedDocument, outputPath.toString(), new PresentationSaveOptions(PresentationFormats.Pptx));
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
        return outputPath;
    }

    public static Path createPresentationWithOptions() {
        final Path outputPath = makeOutputPath("CreateDocument-createPresentationWithOptions.pptx");

        try {
            final Editor editor = new Editor(PresentationFormats.Pptx);
            try {

                // Create presentation edit options
                PresentationEditOptions presentationEditOptions = new PresentationEditOptions();
                presentationEditOptions.setShowHiddenSlides(false);
                presentationEditOptions.setSlideNumber(0);

                // Get editable document with options
                EditableDocument originalDocument = editor.edit(presentationEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    // Re-create EditableDocument from updated HTML content and original resources
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
                        // Save the edited document to the specified output path
                        editor.save(editedDocument, outputPath.toString(), new PresentationSaveOptions(PresentationFormats.Pptx));
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
        return outputPath;
    }

    public static Path createEmail() {
        final Path outputPath = makeOutputPath("CreateDocument-createEmail.eml");

        try {
            // Create an instance of Editor with the EML format
            final Editor editor = new Editor(EmailFormats.Eml);
            try {

                // Set email edit options
                EmailEditOptions emailEditOptions = new EmailEditOptions();
                emailEditOptions.setMailMessageOutput(MailMessageOutput.All);

                // Get editable document with options
                EditableDocument originalDocument = editor.edit(emailEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    // Re-create EditableDocument from updated HTML content and original resources
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
                        // Save the edited document to the specified output path
                        editor.save(editedDocument, outputPath.toString(), new EmailSaveOptions());
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
        return outputPath;
    }

    public static Path createEmailWithOptions() {
        final Path outputPath = makeOutputPath("CreateDocument-createEmailWithOptions.eml");

        try {
            final Editor editor = new Editor(EmailFormats.Eml);
            try {
                // Configure email edit options to include all email content
                EmailEditOptions emailEditOptions = new EmailEditOptions();
                emailEditOptions.setMailMessageOutput(MailMessageOutput.All);

                // Obtain an editable document with the specified options
                EditableDocument originalDocument = editor.edit(emailEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing
                    // Modify htmlContent with the updated HTML content from the WYSIWYG editor

                    // Create a new EditableDocument with the updated HTML and resources
                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
                        // Save the edited email document to the specified output path
                        editor.save(editedDocument, outputPath.toString(), new EmailSaveOptions());
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
        return outputPath;
    }
}