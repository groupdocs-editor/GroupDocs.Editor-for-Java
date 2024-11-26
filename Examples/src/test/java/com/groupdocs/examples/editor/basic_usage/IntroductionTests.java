package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;

class IntroductionTests extends TestsSetUp {


    @Test
    void testRun() {
        final Path outputPath = Introduction.run(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }
}