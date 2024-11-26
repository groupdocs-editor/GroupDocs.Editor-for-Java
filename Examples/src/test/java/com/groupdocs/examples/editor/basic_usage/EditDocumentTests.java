package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.List;

class EditDocumentTests extends TestsSetUp {


    @Test
    void testEdit() {
        Path outputPath = EditDocument.edit(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testEditWithSpecificOptions() {
        Path outputPath = EditDocument.editWithSpecificOptions(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testEditWithDifferentSpecificOptions() {
        List<Path> outputPaths = EditDocument.editWithDifferentSpecificOptions(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPaths).hasSize(2);
        // Check that each path exist
        outputPaths.forEach(path -> Assertions.assertThat(path).isNotNull().exists());
    }

    @Test
    void testEditSpreadsheetTabs() {
        EditDocument.editSpreadsheetTabs(SampleFiles.SAMPLE_XLSX);
    }
}