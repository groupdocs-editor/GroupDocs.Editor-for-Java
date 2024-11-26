package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.formats.PresentationFormats;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.formats.TextualFormats;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.examples.editor.TestsSetUp;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

class WorkingWithTests extends TestsSetUp {


    @Test
    void testWordProcessingFormats() {
        WordProcessingFormats result = WorkingWith.wordProcessingFormats();
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void testPresentationFormats() {
        PresentationFormats result = WorkingWith.presentationFormats();
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void testSpreadsheetFormats() {
        SpreadsheetFormats result = WorkingWith.spreadsheetFormats();
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void testTextualFormats() {
        TextualFormats result = WorkingWith.textualFormats();
        Assertions.assertThat(result).isNotNull();
    }
}