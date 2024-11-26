package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;

class SaveDocumentTests extends TestsSetUp {


    @Test
    void testAsRtfThroughFile() {
        Path outputPath = SaveDocument.asRtfThroughFile(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testAsDocmThroughStream() {
        Path outputPath = SaveDocument.asDocmThroughStream(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testAsTxtThroughFile() {
        Path outputPath = SaveDocument.asTxtThroughFile(SampleFiles.SAMPLE_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }
}