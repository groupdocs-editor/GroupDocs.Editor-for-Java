package com.groupdocs.examples.editor.advanced_usage.editable_document;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

class EditableDocumentContentTests extends TestsSetUp {

    @Test
    void testGetAllEmbeddedHtmlContent() {
        final String content = EditableDocumentContent.getAllEmbeddedHtmlContent(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(content).isNotNull().isNotBlank();
    }

    @Test
    void testGetExternalCssContent() {
        final List<String> content = EditableDocumentContent.getExternalCssContent(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(content).isNotNull().isNotEmpty();
    }

    @Test
    void testGetExternalCssContentWithPrefix() {
        final List<String> content = EditableDocumentContent.getExternalCssContentWithPrefix(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(content).isNotNull().isNotEmpty();
    }

    @Test
    void testGetHtmlBodyContent() {
        final String content = EditableDocumentContent.getHtmlBodyContent(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(content).isNotNull().isNotBlank();
    }

    @Test
    void testGetHtmlBodyContentWithPrefix() {
        final String content = EditableDocumentContent.getHtmlBodyContentWithPrefix(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(content).isNotNull().isNotBlank();
    }

    @Test
    void testGetHtmlContent() {
        final String content = EditableDocumentContent.getHtmlContent(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(content).isNotNull().isNotBlank();
    }

    @Test
    void testGetHtmlContentWithPrefix() {
        final String content = EditableDocumentContent.getHtmlContentWithPrefix(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(content).isNotNull().isNotBlank();
    }
}