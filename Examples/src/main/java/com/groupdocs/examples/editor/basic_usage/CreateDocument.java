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
            final Editor editor = new Editor(WordProcessingFormats.Docx);

            EditableDocument originalDocument = editor.edit();
            try {
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
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

                WordProcessingEditOptions wordProcessingEditOptions = new WordProcessingEditOptions();
                wordProcessingEditOptions.setEnablePagination(false);
                wordProcessingEditOptions.setEnableLanguageInformation(true);
                wordProcessingEditOptions.setFontExtraction(FontExtractionOptions.ExtractAllEmbedded);

                EditableDocument originalDocument = editor.edit(wordProcessingEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
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

                EditableDocument originalDocument = editor.edit();
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
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
            final Editor editor = new Editor(SpreadsheetFormats.Xlsx);
            try {
                SpreadsheetEditOptions spreadsheetEditOptions = new SpreadsheetEditOptions();
                spreadsheetEditOptions.setWorksheetIndex(0);
                spreadsheetEditOptions.setExcludeHiddenWorksheets(true);

                EditableDocument originalDocument = editor.edit(spreadsheetEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
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
            final Editor editor = new Editor(PresentationFormats.Pptx);
            try {

                EditableDocument originalDocument = editor.edit();
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
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

                PresentationEditOptions presentationEditOptions = new PresentationEditOptions();
                presentationEditOptions.setShowHiddenSlides(false);
                presentationEditOptions.setSlideNumber(0);

                EditableDocument originalDocument = editor.edit(presentationEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
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
            final Editor editor = new Editor(EmailFormats.Eml);
            try {

                EmailEditOptions emailEditOptions = new EmailEditOptions();
                emailEditOptions.setMailMessageOutput(MailMessageOutput.All);

                EditableDocument originalDocument = editor.edit(emailEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, null);
                    try {
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

                EmailEditOptions emailEditOptions = new EmailEditOptions();
                emailEditOptions.setMailMessageOutput(MailMessageOutput.All);

                EditableDocument originalDocument = editor.edit(emailEditOptions);
                try {
                    String htmlContent = originalDocument.getEmbeddedHtml();

                    // Pass the htmlContent to a WYSIWYG editor for editing ...
                    // htmlContent = updatedHtmlContent;

                    EditableDocument editedDocument = EditableDocument.fromMarkup(htmlContent, originalDocument.getAllResources());
                    try {
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