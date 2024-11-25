package com.groupdocs.examples.editor;

import com.groupdocs.examples.editor.advanced_usage.*;
import com.groupdocs.examples.editor.advanced_usage.editable_document.CreateEditableDocument;
import com.groupdocs.examples.editor.advanced_usage.editable_document.EditableDocumentContent;
import com.groupdocs.examples.editor.advanced_usage.editable_document.EditableDocumentOperations;
import com.groupdocs.examples.editor.advanced_usage.form_field.EditFormFieldCollection;
import com.groupdocs.examples.editor.advanced_usage.form_field.FixInvalidFormFieldCollectionAndSave;
import com.groupdocs.examples.editor.advanced_usage.form_field.LegacyFormFieldCollection;
import com.groupdocs.examples.editor.advanced_usage.form_field.RemoveFormFieldCollection;
import com.groupdocs.examples.editor.basic_usage.*;
import com.groupdocs.examples.editor.quick_start.HelloWorld;
import com.groupdocs.examples.editor.quick_start.licensing.SetLicenseFromStream;
import com.groupdocs.examples.editor.utils.FailureRegister;

public class Main {
    public static void main(String[] args) {
        System.out.println("Open `src/main/java/com/groupdocs/examples/editor/Main.java` file. \nIn runExamples() method uncomment the example that you want to run.");
        System.out.println("=====================================================");

        runExamples();

        final boolean printFailedSamplesStacktrace = System.getenv("PRINT_FAILED_SAMPLES_STACKTRACE") != null;
        FailureRegister.getInstance().printFailedSamples(printFailedSamplesStacktrace);

        System.out.println("\nAll done.");
        System.exit(FailureRegister.getInstance().getFailedSamplesCount());
    }

    public static void runExamples() {
        // TODO: Comment examples which you don't want to run

        { // Licensing
//            SetLicenseFromFile.run();
            SetLicenseFromStream.run();
//            SetMeteredLicense.run();
        }
        { // Quick start
            HelloWorld.run(SampleFiles.SAMPLE_DOCX);
        }
        { // Basic usage
            Introduction.run(SampleFiles.SAMPLE_DOCX);

            CreateDocument.createWordProcessing();
            CreateDocument.createWordProcessingWithOptions();
            CreateDocument.createSpreadsheet();
            CreateDocument.createSpreadsheetWithOptions();
            CreateDocument.createPresentation();
            CreateDocument.createPresentationWithOptions();
            CreateDocument.createEmail();
            CreateDocument.createEmailWithOptions();

            EditDocument.edit(SampleFiles.SAMPLE_DOCX);
            EditDocument.editWithSpecificOptions(SampleFiles.SAMPLE_DOCX);
            EditDocument.editWithDifferentSpecificOptions(SampleFiles.SAMPLE_DOCX);
            EditDocument.editSpreadsheetTabs(SampleFiles.SAMPLE_XLSX);

            LoadDocument.fromFile(SampleFiles.SAMPLE_XLSX);
            LoadDocument.fromFileWithOptions(SampleFiles.SAMPLE_DOCX);
            LoadDocument.fromStream(SampleFiles.SAMPLE_XLSX);
            LoadDocument.fromStreamWithOptions(SampleFiles.SAMPLE_PPTX);

            SaveDocument.asRtfThroughFile(SampleFiles.SAMPLE_DOCX);
            SaveDocument.asDocmThroughStream(SampleFiles.SAMPLE_DOCX);
            SaveDocument.asTxtThroughFile(SampleFiles.SAMPLE_DOCX);

            CreateEditableDocument.fromHtmlFile(SampleFiles.SAMPLE_HTML);
            CreateEditableDocument.fromInnerBody(SampleFiles.PURE_CONTENT_HTML, SampleFiles.PURE_CONTENT_HTML_RESOURCES);
        }
        { // Advanced usage
            EditableDocumentContent.getAllEmbeddedHtmlContent(SampleFiles.SAMPLE_DOCX);
            EditableDocumentContent.getExternalCssContent(SampleFiles.SAMPLE_DOCX);
            EditableDocumentContent.getExternalCssContentWithPrefix(SampleFiles.SAMPLE_DOCX);
            EditableDocumentContent.getHtmlBodyContent(SampleFiles.SAMPLE_DOCX);
            EditableDocumentContent.getHtmlBodyContentWithPrefix(SampleFiles.SAMPLE_DOCX);
            EditableDocumentContent.getHtmlContent(SampleFiles.SAMPLE_DOCX);
            EditableDocumentContent.getHtmlContentWithPrefix(SampleFiles.SAMPLE_DOCX);

            EditableDocumentOperations.saveHtmlResourcesToFolder(SampleFiles.SAMPLE_DOCX);
            EditableDocumentOperations.saveHtmlToFolder(SampleFiles.SAMPLE_DOCX);
            EditableDocumentOperations.complexOperations(SampleFiles.SAMPLE_DOCX);
            EditableDocumentOperations.workingWithResources(SampleFiles.SAMPLE2_DOCX);

            EditFormFieldCollection.run(SampleFiles.LEGACY_FORM_FIELDS_DOCX);
            FixInvalidFormFieldCollectionAndSave.run(SampleFiles.LEGACY_FORM_FIELDS_DOCX);
            LegacyFormFieldCollection.run(SampleFiles.LEGACY_FORM_FIELDS_DOCX);
            RemoveFormFieldCollection.run(SampleFiles.LEGACY_FORM_FIELDS_DOCX);

            ExtractingDocumentInfo.fromDocx(SampleFiles.SAMPLE_DOCX);
            ExtractingDocumentInfo.fromXlsx(SampleFiles.SAMPLE_XLSX);
            ExtractingDocumentInfo.fromProtectedXlsx(SampleFiles.SAMPLE_XLS_PROTECTED);
            ExtractingDocumentInfo.fromXml(SampleFiles.SAMPLE_XML);
            ExtractingDocumentInfo.fromTxt(SampleFiles.SAMPLE_TXT);

            MarkdownRoundTrip.run(SampleFiles.SAMPLE_MD, SampleFiles.SAMPLE_MD_FOLDER);

            SaveDocumentInDifferentFormats.run(SampleFiles.SAMPLE_DOCX);

            WorkingWith.wordProcessingFormats();
            WorkingWith.presentationFormats();
            WorkingWith.spreadsheetFormats();
            WorkingWith.textualFormats();

            GeneratingPreview.run(SampleFiles.SAMPLE_PPTX_FORMATTING);

            WorkingWithCsv.run(SampleFiles.SAMPLE_CSV);
            WorkingWithEmail.run(SampleFiles.SAMPLE_MSG);
            WorkingWithMarkdown.run(SampleFiles.SAMPLE_MD, SampleFiles.SAMPLE_MD_FOLDER);
            WorkingWithText.run(SampleFiles.SAMPLE_TXT);
            WorkingWithPresentations.run(SampleFiles.SAMPLE_PPTX);
            WorkingWithSpreadsheet.run(SampleFiles.SAMPLE_XLSX);
            WorkingWithPasswordProtectedSpreadsheet.run(SampleFiles.SAMPLE_XLS_PROTECTED);
            WorkingWithWordProcessing.run(SampleFiles.SAMPLE_DOCX);
            WorkingWithXml.editAndSave(SampleFiles.SAMPLE_XML);
            WorkingWithXml.loadFromStream(SampleFiles.SAMPLE_XML);
            WorkingWithXml.loadFromPath(SampleFiles.SAMPLE_XML);
            WorkingWithXml.editXmlShort(SampleFiles.SAMPLE_XML);
            WorkingWithXml.highlightOptionsDemo();
            WorkingWithXml.formatOptionsDemo();
            WorkingWithXml.complexEditDemo(SampleFiles.SAMPLE_XML);
            WorkingWithXml.getXmlMetaInfo(SampleFiles.SAMPLE_XML);
        }
    }
}
