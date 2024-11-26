package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.testng.annotations.Test;

class LoadDocumentTests extends TestsSetUp {


    @Test
    void testFromFile() {
        LoadDocument.fromFile(SampleFiles.SAMPLE_XLSX);
    }

    @Test
    void testFromFileWithOptions() {
        LoadDocument.fromFileWithOptions(SampleFiles.SAMPLE_DOCX);
    }

    @Test
    void testFromStream() {
        LoadDocument.fromStream(SampleFiles.SAMPLE_XLSX);
    }

    @Test
    void testFromStreamWithOptions() {
        LoadDocument.fromStreamWithOptions(SampleFiles.SAMPLE_PPTX);
    }
}