/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.formats.PresentationFormats;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.formats.TextualFormats;
import com.groupdocs.editor.formats.WordProcessingFormats;

/**
 * @author AlexT
 */
public class WorkingWith {

    public static WordProcessingFormats wordProcessingFormats() {
        // Displaying all available Word Processing formats
        for (WordProcessingFormats format : WordProcessingFormats.getAll()) {
            System.out.println("Name is " + format.getName() + ", extension is " + format.getExtension());
        }
        // Parsing a specific format from its extension
        WordProcessingFormats format = WordProcessingFormats.fromExtension(".docx");
        System.out.println("Parsed Word Processing format is " + format.getName());

        return format;
    }

    public static PresentationFormats presentationFormats() {

        // Displaying all available Presentation formats
        for (PresentationFormats format : PresentationFormats.getAll()) {
            System.out.println("Name is " + format.getName() + ", extension is " + format.getExtension());
        }

        // Parsing a specific format from its extension
        PresentationFormats format = PresentationFormats.fromExtension(".pptx");
        System.out.println("Parsed Presentation format is " + format.getName());
        return format;
    }

    public static SpreadsheetFormats spreadsheetFormats() {
        // Displaying all available Spreadsheet formats
        for (SpreadsheetFormats format : SpreadsheetFormats.getAll()) {
            System.out.println("Name is " + format.getName() + ", extension is " + format.getExtension());
        }

        // Parsing a specific format from its extension
        SpreadsheetFormats format = SpreadsheetFormats.fromExtension(".xlsm");
        System.out.println("Parsed Spreadsheet format is " + format.getName());
        return format;
    }

    public static TextualFormats textualFormats() {
        // Displaying all available Textual formats
        for (TextualFormats format : TextualFormats.getAll()) {
            System.out.println("Name is " + format.getName() + ", extension is " + format.getExtension());
        }

        // Parsing a specific format from its extension
        TextualFormats format = TextualFormats.fromExtension("html");
        System.out.println("Parsed Textual format is " + format.getName());
        return format;
    }
}
