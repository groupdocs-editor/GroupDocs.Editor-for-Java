/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.basicusage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.htmlcss.resources.images.IImageResource;
import com.groupdocs.editor.options.FontExtractionOptions;
import com.groupdocs.editor.options.SpreadsheetEditOptions;
import com.groupdocs.editor.options.SpreadsheetLoadOptions;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingSaveOptions;
import java.util.List;

/**
 *
 * @author AlexT
 */
public class EditDocument {

    public static void run() throws Exception {
        //Load some WordProcessing document somehow, as it is shown in LoadDocument.cs
        String inputFilePath = Constants.SAMPLE_DOCX; //path to some document
        Editor editor1 = new Editor(inputFilePath, new WordProcessingLoadOptions());

        //Edit WordProcessing document with default options
        EditableDocument defaultWordProcessingDoc = editor1.edit();

        //Edit WordProcessing document with specified options with some defined settings
        WordProcessingEditOptions wordProcessingEditOptions1 = new WordProcessingEditOptions();
        wordProcessingEditOptions1.setEnablePagination(false);
        wordProcessingEditOptions1.setEnableLanguageInformation(true);
        wordProcessingEditOptions1.setFontExtraction(FontExtractionOptions.ExtractAllEmbedded);

        EditableDocument version1WordProcessingDoc = editor1.edit(wordProcessingEditOptions1);

        //Edit same WordProcessing document with another configuration of option parameters
        WordProcessingEditOptions wordProcessingEditOptions2 = new WordProcessingEditOptions(true);
        wordProcessingEditOptions2.setFontExtraction(FontExtractionOptions.ExtractAll);

        EditableDocument version2WordProcessingDoc = editor1.edit(wordProcessingEditOptions2);

        //Load another document, Spreadsheet at this time
        Editor editor2 = new Editor(Constants.SAMPLE_XLSX, new SpreadsheetLoadOptions());

        //Edit 1st tab of this Spreadsheet
        SpreadsheetEditOptions sheetTab1EditOptions = new SpreadsheetEditOptions();
        sheetTab1EditOptions.setWorksheetIndex(0);//index is 0-based, so this is 1st tab
        EditableDocument firstTab = editor2.edit(sheetTab1EditOptions);

        //Edit 2nd tab of this Spreadsheet
        SpreadsheetEditOptions sheetTab2EditOptions = new SpreadsheetEditOptions();
        sheetTab2EditOptions.setWorksheetIndex(1);//index is 0-based, so this is 2nd tab
        EditableDocument secondTab = editor2.edit(sheetTab2EditOptions);

        //Obtaining HTML markup from some EditableDocument instance
        String bodyContent = firstTab.getBodyContent();//HTML markup from inside the HTML->BODY element
        String allContent = firstTab.getContent();//Full HTML markup of all document, with HTML->HEAD header and all its content
        List<IImageResource> onlyImages = firstTab.getImages();
        List<IHtmlResource> allResourcesTogether = firstTab.getAllResources();

        //Dispose all Editor and EditableDocument instances
        defaultWordProcessingDoc.dispose();
        version1WordProcessingDoc.dispose();
        version2WordProcessingDoc.dispose();
        firstTab.dispose();
        secondTab.dispose();
        editor1.dispose();
        editor2.dispose();

        System.out.println("EditDocument routine has successfully finished");
    }
}
