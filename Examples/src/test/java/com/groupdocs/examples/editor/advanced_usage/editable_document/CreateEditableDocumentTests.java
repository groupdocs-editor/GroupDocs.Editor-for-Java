package com.groupdocs.examples.editor.advanced_usage.editable_document;


import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;

class CreateEditableDocumentTests extends TestsSetUp {

    @Test
    void testFromHtmlFile() {
        Path outputPath = CreateEditableDocument.fromHtmlFile(SampleFiles.SAMPLE_HTML);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }

    @Test
    void testFromInnerBody() {
        Path outputPath = CreateEditableDocument.fromInnerBody(SampleFiles.PURE_CONTENT_HTML, SampleFiles.PURE_CONTENT_HTML_RESOURCES);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }
}