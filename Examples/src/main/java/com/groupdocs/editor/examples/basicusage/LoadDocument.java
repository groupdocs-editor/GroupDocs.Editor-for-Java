/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.basicusage;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.options.SpreadsheetLoadOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author AlexT
 */
public class LoadDocument {

    public static void run() throws Exception {
        String inputPath = Constants.SAMPLE_DOCX;

        //Load document as file via path and without load options
        Editor editor1 = new Editor(inputPath);

        //Load document as file via path and with load options
        WordProcessingLoadOptions wordLoadOptions = new WordProcessingLoadOptions();
        wordLoadOptions.setPassword("some password");
        Editor editor2 = new Editor(inputPath, wordLoadOptions);

        InputStream inputStream = new FileInputStream(Constants.SAMPLE_XLSX);
        InputStream inputStream2 = new FileInputStream(Constants.SAMPLE_XLSX);

        //Load document as content from byte stream and without load options
        Editor editor3 = new Editor(inputStream);

        //Load document as content from byte stream and with load options
        SpreadsheetLoadOptions sheetLoadOptions = new SpreadsheetLoadOptions();
        sheetLoadOptions.setOptimizeMemoryUsage(true);
        Editor editor4 = new Editor(inputStream2, sheetLoadOptions);

        //Dispose all resources
        editor1.dispose();
        editor2.dispose();
        editor3.dispose();
        editor4.dispose();

        System.out.println("LoadDocument routine has successfully finished");
    }
}
