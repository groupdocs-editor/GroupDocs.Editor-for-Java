package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;

class WorkingWithPasswordProtectedSpreadsheetTests extends TestsSetUp {


    @Test
    void testRun() {
        Path outputPath = WorkingWithPasswordProtectedSpreadsheet.run(SampleFiles.SAMPLE_XLS_PROTECTED);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }
}