package com.groupdocs.examples.editor.advanced_usage.editable_document;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.nio.file.Path;
import java.util.List;

public class EditableDocumentContent {

    public static String getAllEmbeddedHtmlContent(Path inputFile) {
        try {
            // Initialize an Editor object with the input file path and WordProcessingLoadOptions.
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // Create an EditableDocument from the initialized Editor object.
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    // Retrieve the embedded HTML content from the document.
                    String embeddedHtmlContent = document.getEmbeddedHtml();

                    System.out.println("HTML content of the input document, where all resources are embedded in base64 encoding: \n\t"
                            + embeddedHtmlContent.replace('\n', ' '));

                    System.out.println("..sample finished successfully.");
                    return embeddedHtmlContent;
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static List<String> getExternalCssContent(Path inputFile) {
        try {
            // Create an Editor instance with the input file's path
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // Begin editing the document using specific word processing edit options
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    // Extract the CSS content from the document
                    List<String> stylesheets = document.getCssContent();

                    System.out.println("There are " + stylesheets.size() + " stylesheets in the input document");
                    for (String css : stylesheets) {
                        System.out.println('\t' + css.replace('\n', ' '));
                    }

                    System.out.println("..sample finished successfully.");
                    return stylesheets;
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static List<String> getExternalCssContentWithPrefix(Path inputFile) {
        try {
            // Initialize an Editor with the input file's path
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // EditableDocument allows modifications to the document and retrieving CSS content
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    // Define prefixes for external image and font URLs
                    String externalImagesPrefix = "http://www.mywebsite.com/images/id=";
                    String externalFontsPrefix = "http://www.mywebsite.com/fonts/id=";

                    // Retrieve CSS content, using the defined prefixes
                    List<String> stylesheets = document.getCssContent(externalImagesPrefix, externalFontsPrefix);

                    System.out.println("There are " + stylesheets.size() + " stylesheets in the input document");
                    for (String css : stylesheets) {
                        System.out.println('\t' + css.replace('\n', ' '));
                    }

                    System.out.println("..sample finished successfully.");
                    return stylesheets;
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static String getHtmlBodyContent(Path inputFile) {
        try {
            // Initialize the editor with the given file path
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // Edit the document for modifications
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    // Retrieve the BODY content from the document.
                    String bodyContent = document.getBodyContent();

                    System.out.println("Inner content of the HTML->BODY element: \n\t"
                            + bodyContent.replace('\n', ' '));

                    System.out.println("..sample finished successfully.");
                    return bodyContent;
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static String getHtmlBodyContentWithPrefix(Path inputFile) {
        try {
            // Initialize the editor with the given file path
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // Edit the document for modifications
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    // Define the prefix for external images
                    String externalImagesPrefix = "http://www.mywebsite.com/images/id=";

                    // Get the content of BODY with the external image prefix
                    String prefixedBodyContent = document.getBodyContent(externalImagesPrefix);

                    System.out.println("Content of HTML->BODY element with external images prefix: \n\t" +
                            prefixedBodyContent.replace('\n', ' '));

                    System.out.println("Document edited and processed successfully.");
                    return prefixedBodyContent;
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static String getHtmlContent(Path inputFile) {
        try {
            // Create new Editor with input file path and load options
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());
            try {
                // Open the document for editing using specified options
                EditableDocument document = editor.edit(new WordProcessingEditOptions());
                try {
                    // Get HTML content of the document
                    String htmlContent = document.getContent();

                    System.out.println("HTML content of the input document (first 200 chars): \n\t"
                            + htmlContent.substring(0, Math.min(200, htmlContent.length())).replace('\n', ' '));

                    System.out.println("..sample finished successfully.");
                    return htmlContent;
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static String getHtmlContentWithPrefix(Path inputFile) {
        try {
            // Create new Editor with input file path and load options
            Editor editor = new Editor(inputFile.toString(), new WordProcessingLoadOptions());

            try {
                // Open the document for editing using specified options
                EditableDocument document = editor.edit(new WordProcessingEditOptions());

                try {
                    String externalImagesPrefix = "http://www.mywebsite.com/images/id=";
                    String externalCssPrefix = "http://www.mywebsite.com/css/id=";
                    // Getting the HTML content with custom image and stylesheet prefixes
                    String prefixedHtmlContent = document.getContentString(externalImagesPrefix, externalCssPrefix);

                    System.out.println("HTML content of the input document: \n\t" + prefixedHtmlContent.replace('\n', ' '));

                    System.out.println("..sample finished successfully.");
                    return prefixedHtmlContent;
                } finally {
                    document.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }
}
