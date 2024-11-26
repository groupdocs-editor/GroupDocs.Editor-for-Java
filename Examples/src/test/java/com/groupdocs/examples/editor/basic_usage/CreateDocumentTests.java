package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;

class CreateDocumentTests extends TestsSetUp {


    @Test
    void testCreateWordProcessing() {
        Path outputPath = CreateDocument.createWordProcessing();
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testCreateWordProcessingWithOptions() {
        Path outputPath = CreateDocument.createWordProcessingWithOptions();
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testCreateSpreadsheet() {
        Path outputPath = CreateDocument.createSpreadsheet();
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testCreateSpreadsheetWithOptions() {
        Path outputPath = CreateDocument.createSpreadsheetWithOptions();
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testCreatePresentation() {
        Path outputPath = CreateDocument.createPresentation();
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testCreatePresentationWithOptions() {
        Path outputPath = CreateDocument.createPresentationWithOptions();
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testCreateEmail() {
        Path outputPath = CreateDocument.createEmail();
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testCreateEmailWithOptions() {
        Path outputPath = CreateDocument.createEmailWithOptions();
        Assertions.assertThat(outputPath).isNotNull().exists();
    }
}