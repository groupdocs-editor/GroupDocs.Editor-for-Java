/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.IncorrectPasswordException;
import com.groupdocs.editor.PasswordRequiredException;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.metadata.IDocumentInfo;
import com.groupdocs.editor.metadata.SpreadsheetDocumentInfo;
import com.groupdocs.editor.metadata.TextualDocumentInfo;
import com.groupdocs.editor.metadata.WordProcessingDocumentInfo;

/**
 *
 * @author AlexT
 */
public class ExtractingDocumentInfo {

    public static void run() throws Exception {
        System.out.println("****************************************");
        System.out.println("Starting 'ExtractingDocumentInfo' routine");

        //1. Let's check some WordProcessing document. Get its path or stream
        String docxInputFilePath = Constants.SAMPLE_DOCX;

        //2. Instantiate Editor class, LoadOptions are not necessary, as we suppose that we know nothing about this file, especially don't know its format
        Editor editorDocx = new Editor(docxInputFilePath);

        //3. Check it
        IDocumentInfo infoDocx = editorDocx.getDocumentInfo(null);

        //4. Maybe it is Spreadsheet?
        String isSpreadsheet = "no";
        if (infoDocx instanceof SpreadsheetDocumentInfo) {
            isSpreadsheet = "yes";
        }
        System.out.println("Is '" + docxInputFilePath + "' a Spreadsheet: " + isSpreadsheet);

        //5. Or text?
        String isText = "no";
        if (infoDocx instanceof TextualDocumentInfo) {
            isText = "yes";
        }
        System.out.println("Is '" + docxInputFilePath + "' a Textual document: " + isText);

        //6. Or maybe WordProcessing?
        String isWordProcessing = "no";
        if (infoDocx instanceof WordProcessingDocumentInfo) {
            isWordProcessing = "yes";
        }
        System.out.println("Is '" + docxInputFilePath + "' a WordProcessing document: " + isWordProcessing);

        //7. If yes - get detailed info about it
        if ("yes".equals(isWordProcessing)) {
            WordProcessingDocumentInfo casted = (WordProcessingDocumentInfo) infoDocx;
            System.out.println(String.format("Format is: "+casted.getFormat().getName()+"; extension is: "+casted.getFormat().getExtension()+"; Page count: "+casted.getPageCount()
                            +"; Size: "+casted.getSize()+" bytes; Is encrypted: "+casted.isEncrypted()));
        }

        //8. Now let's check 2-tab Spreadsheet
        String xlsxInputFilePath = Constants.SAMPLE_XLSX;
        Editor editorXlsx = new Editor(xlsxInputFilePath);
        IDocumentInfo infoXlsx = editorXlsx.getDocumentInfo(null);

        //9. Check and display its type
        System.out.println(String.format("It is:\r\nWordProcessing: {0}\r\nSpreadsheet: {1}\r\nTextual: {2}",
                 infoXlsx instanceof WordProcessingDocumentInfo, infoXlsx instanceof SpreadsheetDocumentInfo, infoXlsx instanceof TextualDocumentInfo
        ));

        //10. Print detailed info
        {
            SpreadsheetDocumentInfo casted = (SpreadsheetDocumentInfo) infoXlsx;
            System.out.println(String.format("Format is: "+casted.getFormat().getName()+"; extension is: "+casted.getFormat().getExtension()+"; Tabs count: "
                            +casted.getPageCount()+"; Size: "+casted.getSize()+" bytes; Is encrypted: "+casted.isEncrypted()));
        }

        //11. Now let's try to open a password-protected document
        String xlsInputFilePath = Constants.SAMPLE_XLS_PROTECTED;
        Editor editorXls = new Editor(xlsInputFilePath);

        // 12.First of all, try to check it without password
        IDocumentInfo infoXls;
        try {
            infoXls = editorXls.getDocumentInfo(null);
        } catch (PasswordRequiredException ex) {
            System.out.println("Oops! We tried to open a password-protected document without password");
        }

        //13. With invalid password at this time
        try {
            infoXls = editorXls.getDocumentInfo("I don't know the password...");
        } catch (IncorrectPasswordException ex) {
            System.out.println("Oops! We specified password at this time, but it is incorrect");
        }

        //14. Finally, let's try with valid password
        infoXls = editorXls.getDocumentInfo("excel_password");

        //15. Check it
        System.out.println(String.format("Password-protected document actually is:\r\nWordProcessing: {0}\r\nSpreadsheet: {1}\r\nTextual: {2}",
                infoXls instanceof WordProcessingDocumentInfo, infoXls instanceof SpreadsheetDocumentInfo, infoXls instanceof TextualDocumentInfo
        ));

        //16. Print detailed info
        SpreadsheetDocumentInfo casted = (SpreadsheetDocumentInfo) infoXls;
        System.out.println(String.format("Format is: "+casted.getFormat().getName()+"; extension is: "+casted.getFormat().getExtension()
                        +"; Tabs count: "+casted.getPageCount()+"; Size: "+casted.getSize()+" bytes; Is encrypted: "+casted.isEncrypted()));

        //17. Now let's try to play with text-based documents
        String xmlInputFilePath = Constants.SAMPLE_XML;
        Editor editorXml = new Editor(xmlInputFilePath);

        //18. Grab data and check it
        IDocumentInfo infoXml = editorXml.getDocumentInfo(null);
        System.out.println(String.format("XML document is:\r\nWordProcessing: {0}\r\nSpreadsheet: {1}\r\nTextual: {2}",
                infoXml instanceof WordProcessingDocumentInfo, infoXml instanceof SpreadsheetDocumentInfo, infoXml instanceof TextualDocumentInfo
        ));

        //19. Print detailed info
        TextualDocumentInfo casted1 = (TextualDocumentInfo) infoXml;
        System.out.println(String.format("Format is: "+casted1.getFormat().getName()+"; extension is: "+casted1.getFormat().getExtension()+
                        "; Encoding: "+casted1.getEncoding()+"; Size: "+casted1.getSize()+" bytes"));

        //20. Plain text at this time
        String txtInputFilePath = Constants.SAMPLE_TXT;
        Editor editorTxt = new Editor(txtInputFilePath);

        //21. Grab data and check it
        IDocumentInfo infoTxt = editorTxt.getDocumentInfo(null);
        System.out.println(String.format("Text document is:\r\nWordProcessing: {0}\r\nSpreadsheet: {1}\r\nTextual: {2}",
                infoTxt instanceof WordProcessingDocumentInfo, infoTxt instanceof SpreadsheetDocumentInfo, infoTxt instanceof TextualDocumentInfo
        ));

        //22. Print detailed info
        TextualDocumentInfo casted2 = (TextualDocumentInfo) infoTxt;
        System.out.println(String.format("Format is: "+casted2.getFormat().getName()+"; extension is: "+casted2.getFormat().getExtension()+
                        "; Encoding: "+casted2.getEncoding()+"; Size: "+casted2.getSize()+" bytes"));

        //23. Don't forget to dispose all resources
        editorDocx.dispose();
        editorXlsx.dispose();
        editorXls.dispose();
        editorXml.dispose();
        editorTxt.dispose();

        System.out.println("ExtractingDocumentInfo routine has successfully finished");
    }
}