package com.groupdocs.examples.editor.advanced_usage.editable_document;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.file.Path;
import java.util.List;

public class EditableDocumentContent {

    public static void getAllEmbeddedHtmlContent(Path inputFile) {
        try {
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    String embeddedHtmlContent = document.getEmbeddedHtml();
                    System.out.println("HTML content of the input document, where all resources are embedded in base64 encoding: \n\t"
                            + embeddedHtmlContent.replace('\n', ' '));
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void getExternalCssContent(Path inputFile) {
        try {
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    List<String> stylesheets = document.getCssContent();
                    System.out.println("There are " + stylesheets.size() + " stylesheets in the input document");
                    for (String css : stylesheets) {
                        System.out.println('\t' + css.replace('\n', ' '));
                    }
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void getExternalCssContentWithPrefix(Path inputFile) {
        try {
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    String externalImagesPrefix = "http://www.mywebsite.com/images/id=";
                    String externalFontsPrefix = "http://www.mywebsite.com/fonts/id=";
                    List<String> stylesheets = document.getCssContent(externalImagesPrefix, externalFontsPrefix);
                    System.out.println("There are " + stylesheets.size() + " stylesheets in the input document");
                    for (String css : stylesheets) {
                        System.out.println('\t' + css.replace('\n', ' '));
                    }
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void getHtmlBodyContent(Path inputFile) {
        try {
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    String bodyContent = document.getBodyContent();
                    System.out.println("Inner content of the HTML->BODY element: \n\t"
                            + bodyContent.replace('\n', ' '));
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void getHtmlBodyContentWithPrefix(Path inputFile) {
        try {
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    String externalImagesPrefix = "http://www.mywebsite.com/images/id=";
                    String prefixedBodyContent = document.getBodyContent(externalImagesPrefix);
                    System.out.println("Inner content of the HTML->BODY element with external images prefix: \n\t"
                            + prefixedBodyContent.replace('\n', ' '));
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void getHtmlContent(Path inputFile) {
        try {
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    String htmlContent = document.getContent();
                    System.out.println("HTML content of the input document (first 200 chars): \n\t"
                            + htmlContent.substring(0, 200).replace('\n', ' '));
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void getHtmlContentWithPrefix(Path inputFile) {
        try {
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    String externalImagesPrefix = "http://www.mywebsite.com/images/id=";
                    String externalCssPrefix = "http://www.mywebsite.com/css/id=";
                    String prefixedHtmlContent = document.getContentString(externalImagesPrefix, externalCssPrefix);
                    System.out.println("HTML content of the input document with custom image and stylesheet prefixes: \n\t"
                            + prefixedHtmlContent.replace('\n', ' '));
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
            System.out.println("\nDocument edited successfully.");
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }
}
