package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.List;

class MarkdownRoundTripTests extends TestsSetUp {


    @Test
    void testRun() {
        final List<Path> outputPaths = MarkdownRoundTrip.run(SampleFiles.SAMPLE_MD, SampleFiles.SAMPLE_MD_FOLDER);
        Assertions.assertThat(outputPaths).hasSize(2);
        // Check that each path exist
        outputPaths.forEach(path -> Assertions.assertThat(path).isNotNull().exists());
    }
}