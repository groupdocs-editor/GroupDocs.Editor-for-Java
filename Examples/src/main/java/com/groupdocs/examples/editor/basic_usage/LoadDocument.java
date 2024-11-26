package com.groupdocs.examples.editor.basic_usage;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.options.SpreadsheetLoadOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoadDocument {

    public static void fromFile(Path inputFile) {
        try {
            // Load document as file via path and without load options
            Editor editor = new Editor(inputFile.toString());

            // Do stuff with editor

            editor.dispose();

            System.out.println("\nDocument loaded successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void fromFileWithOptions(Path inputFile) {
        try {
            // Load document as file via path and with load options
            WordProcessingLoadOptions wordLoadOptions = new WordProcessingLoadOptions();
            wordLoadOptions.setPassword("some password");

            Editor editor = new Editor(inputFile.toString(), wordLoadOptions);

            // Do stuff with editor

            editor.dispose();

            System.out.println("\nDocument loaded successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void fromStream(Path inputFile) {
        try (InputStream inputStream = Files.newInputStream(inputFile)) {
            // Load document as content from byte stream and without load options
            Editor editor = new Editor(inputStream);

            // Do stuff with editor

            editor.dispose();

            System.out.println("\nDocument loaded successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void fromStreamWithOptions(Path inputFile) {
        try (InputStream inputStream = Files.newInputStream(inputFile)) {
            // Configure editing options for the Spreadsheet document
            SpreadsheetLoadOptions sheetLoadOptions = new SpreadsheetLoadOptions();
            sheetLoadOptions.setOptimizeMemoryUsage(true);

            // Load document as content from byte stream and without load options
            Editor editor = new Editor(inputStream, sheetLoadOptions);

            // Do stuff with editor

            editor.dispose();

            System.out.println("\nDocument loaded successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }
}
