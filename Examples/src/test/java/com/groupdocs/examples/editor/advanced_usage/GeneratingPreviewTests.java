package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;

class GeneratingPreviewTests extends TestsSetUp {


    @Test
    void testRun() {
        Path outputPath = GeneratingPreview.run(SampleFiles.SAMPLE_PPTX_FORMATTING);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }
}