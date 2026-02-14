package com.groupdocs.editor.examples.basicusage;


import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.*;
import com.groupdocs.editor.options.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/*
 * This example demonstrates how to create new documents by format in GroupDocs.Editor and apply edit options.
 */
public class CreateDocument {
    public static void run() throws Exception
    {
        // Create a new WordProcessing document and save it using a callback .
        Editor editorWord = new Editor(WordProcessingFormats.Docx);
        {
            // Edit the WordProcessing document with default options.
            EditableDocument defaultWordProcessingDoc = editorWord.edit();

            // Edit the WordProcessing document with specified options and some defined settings.
            WordProcessingEditOptions wordProcessingEditOptions = new WordProcessingEditOptions();
            wordProcessingEditOptions.setEnablePagination(false);  // Disable pagination for the document.
            wordProcessingEditOptions.setEnableLanguageInformation(true);  // Enable language information for the document.
            wordProcessingEditOptions.setFontExtraction(FontExtractionOptions.ExtractAllEmbedded);  // Extract all embedded fonts.

            EditableDocument editableWordProcessingDocument = editorWord.edit(wordProcessingEditOptions);
        }

        // Create a new Spreadsheet document and save it via callback .
        Editor editorSpreadsheet = new Editor(SpreadsheetFormats.Xlsx);
        {
            // Edit the Spreadsheet document with default options.
            EditableDocument defaultEditableSpreadsheetDocument = editorSpreadsheet.edit();

            // Edit the Spreadsheet document with specified options and some defined settings.
            SpreadsheetEditOptions spreadsheetEditOptions = new SpreadsheetEditOptions();
            spreadsheetEditOptions.setWorksheetIndex(0);
            spreadsheetEditOptions.setExcludeHiddenWorksheets(true);

            EditableDocument editableSpreadsheetDocument = editorSpreadsheet.edit(spreadsheetEditOptions);
        }

        // Create a new Presentation document and save it via callback Action<Stream>.
        Editor editorPresentation = new Editor(PresentationFormats.Pptx);
        {
            // Edit the Presentation document with default options.
            EditableDocument defaultEditablePresentationDocument = editorPresentation.edit();

            // Edit the Presentation document with specified options and some defined settings.
            PresentationEditOptions presentationEditOptions = new PresentationEditOptions();
            presentationEditOptions.setShowHiddenSlides(false);
            presentationEditOptions.setSlideNumber(0);

            EditableDocument editablePresentationDocument = editorPresentation.edit(presentationEditOptions);
        }

        // Create a new Email document and save it via callback .
        Editor editorEmail = new Editor(EmailFormats.Eml);
        {
            // Edit the Email document with default options.
            EditableDocument defaultEditableEmailDocument = editorEmail.edit();

            // Edit the Email document with specified options and some defined settings.
            EmailEditOptions emailEditOptions = new EmailEditOptions();
            emailEditOptions.setMailMessageOutput(MailMessageOutput.All);

            EditableDocument editableEmailDocument = editorEmail.edit(emailEditOptions);
        }
        // Display completion message
        System.out.println("CreateDocument routine has successfully finished");
    }

}
