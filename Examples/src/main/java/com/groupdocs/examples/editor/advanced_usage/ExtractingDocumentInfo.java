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

public class ExtractingDocumentInfo {

    public static IDocumentInfo fromDocx(Path inputFile) {
        try {
            // Instantiate the Editor class to open the document
            final Editor editor = new Editor(inputFile.toString());
            try {
                // Retrieve document information
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);

                // Check if the document is a Spreadsheet
                String isSpreadsheet = "no";
                if (documentInfo instanceof SpreadsheetDocumentInfo) {
                    isSpreadsheet = "yes";
                }
                System.out.println("Is '" + inputFile.getFileName() + "' a Spreadsheet: " + isSpreadsheet);

                // Check if the document is a Textual document
                String isText = "no";
                if (documentInfo instanceof TextualDocumentInfo) {
                    isText = "yes";
                }
                System.out.println("Is '" + inputFile.getFileName() + "' a Textual document: " + isText);

                // Check if the document is for Word Processing
                String isWordProcessing = "no";
                if (documentInfo instanceof WordProcessingDocumentInfo) {
                    isWordProcessing = "yes";
                }
                System.out.println("Is '" + inputFile.getFileName() + "' a WordProcessing document: " + isWordProcessing);

                // If it's a Word Processing document, get detailed info
                if ("yes".equals(isWordProcessing)) {
                    WordProcessingDocumentInfo casted = (WordProcessingDocumentInfo) documentInfo;
                    System.out.printf("Format is: " + casted.getFormat().getName() + "; extension is: " + casted.getFormat().getExtension() + "; Page count: " + casted.getPageCount() + "; Size: " + casted.getSize() + " bytes; Is encrypted: " + casted.isEncrypted() + "%n");
                }

                System.out.println("..sample finished successfully.");
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
            // Initialize editor with the input file path
            Editor editor = new Editor(inputFile.toString());
            try {
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);

                // Log the type of document identified
                System.out.printf("It is:\r\nWordProcessing: " + (documentInfo instanceof WordProcessingDocumentInfo) + "\r\nSpreadsheet: " + (documentInfo instanceof SpreadsheetDocumentInfo) + "\r\nTextual: " + (documentInfo instanceof TextualDocumentInfo) + "%n");

                // If the document is a spreadsheet, retrieve detailed information
                if (documentInfo instanceof SpreadsheetDocumentInfo) {
                    SpreadsheetDocumentInfo casted = (SpreadsheetDocumentInfo) documentInfo;
                    System.out.printf("Format is: " + casted.getFormat().getName() + "; extension is: " + casted.getFormat().getExtension() + "; Tabs count: " + casted.getPageCount() + "; Size: " + casted.getSize() + " bytes; Is encrypted: " + casted.isEncrypted() + "%n");

                    return documentInfo;
                }
            } finally {
                editor.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static IDocumentInfo fromProtectedXlsx(Path inputFile) {
        try {
            // Attempt to open a password-protected document
            Editor editor = new Editor(inputFile.toString());

            try {
                // First, check if we can access the document without a password
                try {
                    editor.getDocumentInfo(null);
                } catch (PasswordRequiredException ex) {
                    System.out.println("Oops! We tried to open a password-protected document without a password");
                }

                // Attempt to access the document with an invalid password
                try {
                    editor.getDocumentInfo("I don't know the password...");
                } catch (IncorrectPasswordException ex) {
                    System.out.println("Oops! We specified a password, but it is incorrect");
                }

                // Finally, attempt to access the document with the correct password
                IDocumentInfo documentInfo = editor.getDocumentInfo("excel_password");

                System.out.printf("Password-protected document actually is:\r\nWordProcessing: %s\r\nSpreadsheet: %s\r\nTextual: %s%n", documentInfo instanceof WordProcessingDocumentInfo, documentInfo instanceof SpreadsheetDocumentInfo, documentInfo instanceof TextualDocumentInfo);

                // If the document is a spreadsheet, print detailed information about it
                if (documentInfo instanceof SpreadsheetDocumentInfo) {
                    SpreadsheetDocumentInfo casted = (SpreadsheetDocumentInfo) documentInfo;
                    System.out.printf("Format is: " + casted.getFormat().getName() + "; extension is: " + casted.getFormat().getExtension() + "; Tabs count: " + casted.getPageCount() + "; Size: " + casted.getSize() + " bytes; Is encrypted: " + casted.isEncrypted() + "%n");

                    return documentInfo;
                }
            } finally {
                editor.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static IDocumentInfo fromXml(Path inputFile) {
        try {
            // Create an Editor instance to handle the document
            Editor editor = new Editor(inputFile.toString());

            try {
                // Retrieve and validate document information
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);
                System.out.printf("XML document is:\r\nWordProcessing: %s\r\nSpreadsheet: %s\r\nTextual: %s%n", documentInfo instanceof WordProcessingDocumentInfo, documentInfo instanceof SpreadsheetDocumentInfo, documentInfo instanceof TextualDocumentInfo);

                // If the document is of type TextualDocumentInfo, print detailed information
                if (documentInfo instanceof TextualDocumentInfo) {
                    TextualDocumentInfo casted1 = (TextualDocumentInfo) documentInfo;
                    System.out.printf("Format is: " + casted1.getFormat().getName() + "; extension is: " + casted1.getFormat().getExtension() + "; Encoding: " + casted1.getEncoding() + "; Size: " + casted1.getSize() + " bytes%n");

                    return documentInfo;
                }
            } finally {
                editor.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static IDocumentInfo fromTxt(Path inputFile) {
        try {
            // Initialize Editor with input file path
            Editor editor = new Editor(inputFile.toString());

            try {
                // Retrieve document info and check its type
                IDocumentInfo documentInfo = editor.getDocumentInfo(null);
                System.out.printf("Text document is:\r\nWordProcessing: %s\r\nSpreadsheet: %s\r\nTextual: %s%n", documentInfo instanceof WordProcessingDocumentInfo, documentInfo instanceof SpreadsheetDocumentInfo, documentInfo instanceof TextualDocumentInfo);

                // If the document is textual, print its detailed format information
                if (documentInfo instanceof TextualDocumentInfo) {
                    TextualDocumentInfo casted2 = (TextualDocumentInfo) documentInfo;

                    System.out.printf("Format is: " + casted2.getFormat().getName() + "; extension is: " + casted2.getFormat().getExtension() + "; Encoding: " + casted2.getEncoding() + "; Size: " + casted2.getSize() + " bytes%n");

                    return documentInfo;
                }
            } finally {
                editor.dispose();
            }
            System.out.println("..sample finished successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }
}