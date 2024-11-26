package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;

class WorkingWithMarkdownTests extends TestsSetUp {


    @Test
    void testRun() {
        final Path outputPath = WorkingWithMarkdown.run(SampleFiles.SAMPLE_MD, SampleFiles.SAMPLE_MD_FOLDER);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }
}