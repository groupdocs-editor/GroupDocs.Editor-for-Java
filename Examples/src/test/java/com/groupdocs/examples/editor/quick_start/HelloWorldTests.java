package com.groupdocs.examples.editor.quick_start;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;

class HelloWorldTests extends TestsSetUp {


    @Test
    void testRun() {
        Path outputPath = HelloWorld.run(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }
}