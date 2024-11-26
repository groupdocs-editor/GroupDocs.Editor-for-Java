package com.groupdocs.examples.editor.advanced_usage.editable_document;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

class EditableDocumentOperationsTests extends TestsSetUp {


    @Test
    void testSaveHtmlResourcesToFolder() throws IOException {
        final Path outputPath = EditableDocumentOperations.saveHtmlResourcesToFolder(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull();
        // outputPath is a directory name that contains output files.
        try (final Stream<Path> pathStream = Files.list(outputPath)) {
            pathStream.forEach(file -> Assertions.assertThat(file).exists());
        }
    }

    @Test
    void testSaveHtmlToFolder() {
        final Path outputPath = EditableDocumentOperations.saveHtmlToFolder(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testComplexOperations() {
        Path outputPath = EditableDocumentOperations.complexOperations(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testWorkingWithResources() throws IOException {
        final Path outputPath = EditableDocumentOperations.workingWithResources(SampleFiles.SAMPLE2_DOCX);
        Assertions.assertThat(outputPath).isNotNull();
        // outputPath is a directory name that contains output files.
        try (final Stream<Path> pathStream = Files.list(outputPath)) {
            pathStream.forEach(file -> Assertions.assertThat(file).exists());
        }
    }
}