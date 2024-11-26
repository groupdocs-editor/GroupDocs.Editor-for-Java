package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.metadata.IDocumentInfo;
import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

class ExtractingDocumentInfoTests extends TestsSetUp {


    @Test
    void testFromDocx() {
        IDocumentInfo documentInfo = ExtractingDocumentInfo.fromDocx(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(documentInfo).isNotNull();
    }

    @Test
    void testFromXlsx() {
        IDocumentInfo documentInfo = ExtractingDocumentInfo.fromXlsx(SampleFiles.SAMPLE_XLSX);
        Assertions.assertThat(documentInfo).isNotNull();
    }

    @Test
    void testFromProtectedXlsx() {
        IDocumentInfo documentInfo = ExtractingDocumentInfo.fromProtectedXlsx(SampleFiles.SAMPLE_XLS_PROTECTED);
        Assertions.assertThat(documentInfo).isNotNull();
    }

    @Test
    void testFromXml() {
        IDocumentInfo documentInfo = ExtractingDocumentInfo.fromXml(SampleFiles.SAMPLE_XML);
        Assertions.assertThat(documentInfo).isNotNull();
    }

    @Test
    void testFromTxt() {
        IDocumentInfo documentInfo = ExtractingDocumentInfo.fromTxt(SampleFiles.SAMPLE_TXT);
        Assertions.assertThat(documentInfo).isNotNull();
    }
}