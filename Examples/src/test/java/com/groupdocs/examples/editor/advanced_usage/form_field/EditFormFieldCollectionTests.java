package com.groupdocs.examples.editor.advanced_usage.form_field;

import com.groupdocs.examples.editor.SampleFiles;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;

class EditFormFieldCollectionTests extends TestsSetUp {


    @Test
    void testRun() {
        Path outputPath = EditFormFieldCollection.run(SampleFiles.LEGACY_FORM_FIELDS_DOCX);
        Assertions.assertThat(outputPath).isNotNull().exists();
    }
}