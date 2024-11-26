package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.List;

class SaveDocumentInDifferentFormatsTests extends TestsSetUp {


    @Test
    void testRun() {
        List<Path> outputPaths = SaveDocumentInDifferentFormats.run(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPaths).hasSize(11);
        // Check that each path exist
        outputPaths.forEach(path -> Assertions.assertThat(path).isNotNull().exists());
    }
}