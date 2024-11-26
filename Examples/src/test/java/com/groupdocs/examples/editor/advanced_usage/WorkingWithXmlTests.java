package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.metadata.TextualDocumentInfo;
import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.List;

class WorkingWithXmlTests extends TestsSetUp {


    @Test
    void testEditAndSave() {
        List<Path> outputPaths = WorkingWithXml.editAndSave(SampleFiles.SAMPLE_XML);
        Assertions.assertThat(outputPaths).hasSize(2);
        // Check that each path exist
        outputPaths.forEach(path -> Assertions.assertThat(path).isNotNull().exists());
    }

    @Test
    void testLoadFromStream() {
        WorkingWithXml.loadFromStream(SampleFiles.SAMPLE_XML);
    }

    @Test
    void testLoadFromPath() {
        WorkingWithXml.loadFromPath(SampleFiles.SAMPLE_XML);
    }

    @Test
    void testEditXmlShort() {
        WorkingWithXml.editXmlShort(SampleFiles.SAMPLE_XML);
    }

    @Test
    void testHighlightOptionsDemo() {
        WorkingWithXml.highlightOptionsDemo();
    }

    @Test
    void testFormatOptionsDemo() {
        WorkingWithXml.formatOptionsDemo();
    }

    @Test
    void testComplexEditDemo() {
        List<Path> outputPaths = WorkingWithXml.complexEditDemo(SampleFiles.SAMPLE_XML);
        Assertions.assertThat(outputPaths).hasSize(2);
        // Check that each path exist
        outputPaths.forEach(path -> Assertions.assertThat(path).isNotNull().exists());
    }

    @Test
    void testGetXmlMetaInfo() {
        TextualDocumentInfo result = WorkingWithXml.getXmlMetaInfo(SampleFiles.SAMPLE_XML);
        Assertions.assertThat(result).isNotNull();
    }
}