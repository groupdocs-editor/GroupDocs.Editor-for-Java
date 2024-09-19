/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.formats.PresentationFormats;
import com.groupdocs.editor.formats.SpreadsheetFormats;
import com.groupdocs.editor.formats.TextualFormats;
import com.groupdocs.editor.formats.WordProcessingFormats;

import java.util.Iterator;

/**
 *
 * @author AlexT
 */
public class WorkingWithFormats {

    public static void run() throws Exception {
        //WordProcessing
        for (Iterator<WordProcessingFormats> it = WordProcessingFormats.getAll().iterator(); it.hasNext(); ) {
            WordProcessingFormats oneFormat = it.next();
            System.out.println("Name is "+oneFormat.getName()+", extension is "+ oneFormat.getExtension());
        }

        //Presentation
        for (Iterator<PresentationFormats> it = PresentationFormats.getAll().iterator(); it.hasNext(); ) {
            PresentationFormats oneFormat = it.next();
            System.out.println("Name is "+oneFormat.getName()+", extension is " + oneFormat.getExtension());
        }

        //Parsing from extension
        SpreadsheetFormats expectedXlsm = SpreadsheetFormats.fromExtension(".xlsm");
        System.out.println("Parsed Spreadsheet format is " + expectedXlsm.getName());
        TextualFormats expectedHtml = TextualFormats.fromExtension("html");
        System.out.println("Parsed Textual format is "+ expectedHtml.getName());
    }
}
