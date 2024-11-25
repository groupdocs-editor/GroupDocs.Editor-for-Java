/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.Editor;
import com.groupdocs.editor.IncorrectPasswordException;
import com.groupdocs.editor.PasswordRequiredException;
import com.groupdocs.editor.metadata.IDocumentInfo;
import com.groupdocs.editor.metadata.SpreadsheetDocumentInfo;
import com.groupdocs.editor.metadata.TextualDocumentInfo;
import com.groupdocs.editor.metadata.WordProcessingDocumentInfo;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.file.Path;

/**
 * @author AlexT
 */
public class ExtractingDocumentInfo {

    public static IDocumentInfo fromDocx(Path inputFile) {
        try {
            //2. Instantiate Editor class, LoadOptions are not necessary, as we suppose that we know nothing about this file, especially don't know its format
            final Editor editor = new Editor(inputFile.toString());
            try {

                //3. Check it
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);

                //4. Maybe it is Spreadsheet?
                String isSpreadsheet = "no";
                if (documentInfo instanceof SpreadsheetDocumentInfo) {
                    isSpreadsheet = "yes";
                }
                System.out.println("Is '" + inputFile.getFileName() + "' a Spreadsheet: " + isSpreadsheet);

                //5. Or text?
                String isText = "no";
                if (documentInfo instanceof TextualDocumentInfo) {
                    isText = "yes";
                }
                System.out.println("Is '" + inputFile.getFileName() + "' a Textual document: " + isText);

                //6. Or maybe WordProcessing?
                String isWordProcessing = "no";
                if (documentInfo instanceof WordProcessingDocumentInfo) {
                    isWordProcessing = "yes";
                }
                System.out.println("Is '" + inputFile.getFileName() + "' a WordProcessing document: " + isWordProcessing);

                //7. If yes - get detailed info about it
                if ("yes".equals(isWordProcessing)) {
                    WordProcessingDocumentInfo casted = (WordProcessingDocumentInfo) documentInfo;
                    System.out.printf("Format is: " + casted.getFormat().getName() + "; extension is: " + casted.getFormat().getExtension() + "; Page count: " + casted.getPageCount() + "; Size: " + casted.getSize() + " bytes; Is encrypted: " + casted.isEncrypted() + "%n");
                }

                System.out.println("\nDocument info extracted successfully.");
                return documentInfo;
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static IDocumentInfo fromXlsx(Path inputFile) {
        try {
            //8. Now let's check 2-tab Spreadsheet
            Editor editor = new Editor(inputFile.toString());
            try {
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);

                //9. Check and display its type
                System.out.printf("It is:\r\nWordProcessing: " + (documentInfo instanceof WordProcessingDocumentInfo) + "\r\nSpreadsheet: " + (documentInfo instanceof SpreadsheetDocumentInfo) + "\r\nTextual: " + (documentInfo instanceof TextualDocumentInfo) + "%n");

                //10. Print detailed info

                if (documentInfo instanceof SpreadsheetDocumentInfo) {
                    SpreadsheetDocumentInfo casted = (SpreadsheetDocumentInfo) documentInfo;
                    System.out.printf("Format is: " + casted.getFormat().getName() + "; extension is: " + casted.getFormat().getExtension() + "; Tabs count: " + casted.getPageCount() + "; Size: " + casted.getSize() + " bytes; Is encrypted: " + casted.isEncrypted() + "%n");

                    return documentInfo;
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument info extracted successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static IDocumentInfo fromProtectedXlsx(Path inputFile) {
        try {
            //11. Now let's try to open a password-protected document
            Editor editor = new Editor(inputFile.toString());

            try {
                // 12.First of all, try to check it without password
                try {
                    editor.getDocumentInfo(null);
                } catch (PasswordRequiredException ex) {
                    System.out.println("Oops! We tried to open a password-protected document without password");
                }

                //13. With invalid password at this time
                try {
                    editor.getDocumentInfo("I don't know the password...");
                } catch (IncorrectPasswordException ex) {
                    System.out.println("Oops! We specified password at this time, but it is incorrect");
                }

                //14. Finally, let's try with valid password
                IDocumentInfo documentInfo = editor.getDocumentInfo("excel_password");

                //15. Check it
                System.out.printf("Password-protected document actually is:\r\nWordProcessing: %s\r\nSpreadsheet: %s\r\nTextual: %s%n", documentInfo instanceof WordProcessingDocumentInfo, documentInfo instanceof SpreadsheetDocumentInfo, documentInfo instanceof TextualDocumentInfo);

                //16. Print detailed info
                if (documentInfo instanceof SpreadsheetDocumentInfo) {
                    SpreadsheetDocumentInfo casted = (SpreadsheetDocumentInfo) documentInfo;
                    System.out.printf("Format is: " + casted.getFormat().getName() + "; extension is: " + casted.getFormat().getExtension() + "; Tabs count: " + casted.getPageCount() + "; Size: " + casted.getSize() + " bytes; Is encrypted: " + casted.isEncrypted() + "%n");

                    return documentInfo;
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument info extracted successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static IDocumentInfo fromXml(Path inputFile) {
        try {
            //17. Now let's try to play with text-based documents
            Editor editor = new Editor(inputFile.toString());

            try {
                //18. Grab data and check it
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);
                System.out.printf("XML document is:\r\nWordProcessing: %s\r\nSpreadsheet: %s\r\nTextual: %s%n", documentInfo instanceof WordProcessingDocumentInfo, documentInfo instanceof SpreadsheetDocumentInfo, documentInfo instanceof TextualDocumentInfo);

                //19. Print detailed info
                if (documentInfo instanceof TextualDocumentInfo) {
                    TextualDocumentInfo casted1 = (TextualDocumentInfo) documentInfo;
                    System.out.printf("Format is: " + casted1.getFormat().getName() + "; extension is: " + casted1.getFormat().getExtension() + "; Encoding: " + casted1.getEncoding() + "; Size: " + casted1.getSize() + " bytes%n");

                    return documentInfo;
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument info extracted successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static IDocumentInfo fromTxt(Path inputFile) {
        try {
            //20. Plain text at this time
            Editor editor = new Editor(inputFile.toString());

            try {
                //21. Grab data and check it
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);
                System.out.printf("Text document is:\r\nWordProcessing: %s\r\nSpreadsheet: %s\r\nTextual: %s%n", documentInfo instanceof WordProcessingDocumentInfo, documentInfo instanceof SpreadsheetDocumentInfo, documentInfo instanceof TextualDocumentInfo);

                //22. Print detailed info
                if (documentInfo instanceof TextualDocumentInfo) {
                    TextualDocumentInfo casted2 = (TextualDocumentInfo) documentInfo;
                    System.out.printf("Format is: " + casted2.getFormat().getName() + "; extension is: " + casted2.getFormat().getExtension() + "; Encoding: " + casted2.getEncoding() + "; Size: " + casted2.getSize() + " bytes%n");

                    //23. Don't forget to dispose all resources

                    return documentInfo;
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument info extracted successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }
}