/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.examples.editor.advanced_usage;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.TextualFormats;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.css.datatypes.ArgbColors;
import com.groupdocs.editor.htmlcss.css.datatypes.Length;
import com.groupdocs.editor.htmlcss.css.datatypes.LengthUnit;
import com.groupdocs.editor.htmlcss.css.properties.FontSize;
import com.groupdocs.editor.htmlcss.css.properties.FontStyle;
import com.groupdocs.editor.htmlcss.css.properties.FontWeight;
import com.groupdocs.editor.htmlcss.css.properties.TextDecorationLineType;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.htmlcss.serialization.QuoteType;
import com.groupdocs.editor.metadata.IDocumentInfo;
import com.groupdocs.editor.metadata.TextualDocumentInfo;
import com.groupdocs.editor.options.*;
import com.groupdocs.examples.editor.utils.FailureRegister;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeOutputPath;

public class WorkingWithXml {

    public static List<Path> editAndSave(Path inputFile) {
        try {
            // Load the file into the Editor class
            Editor editor = new Editor(inputFile.toString());
            try {

                // Create XML editing options with specific configurations
                XmlEditOptions editOptions = new XmlEditOptions();
                editOptions.setAttributeValuesQuoteType(QuoteType.DoubleQuote);
                editOptions.setRecognizeEmails(true);
                editOptions.setRecognizeUris(true);
                editOptions.setTrimTrailingWhitespaces(true);

                // Load the document into an EditableDocument instance for editing
                EditableDocument beforeEdit = editor.edit(editOptions);

                try {
                    // Retrieve the original text content and replace "John" with "Samuel"
                    String originalTextContent = beforeEdit.getContent();
                    String updatedTextContent = originalTextContent.replace("John", "Samuel");

                    // Collect all resources from the document
                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    // Create a new EditableDocument instance with the updated content and resources
                    EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);

                    try {
                        // Define save options for DOCX format
                        WordProcessingSaveOptions wordSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

                        // Define save options for TXT format with UTF-8 encoding
                        TextSaveOptions txtSaveOptions = new TextSaveOptions();
                        txtSaveOptions.setEncoding(StandardCharsets.UTF_8);

                        // Prepare output paths for DOCX and TXT files
                        Path outputDocxPath = makeOutputPath("WorkingWithXml.docx");
                        Path outputTxtPath = makeOutputPath("WorkingWithXml.txt");

                        // Save the edited document in both DOCX and TXT formats
                        editor.save(afterEdit, outputDocxPath.toString(), wordSaveOptions);
                        editor.save(afterEdit, outputTxtPath.toString(), txtSaveOptions);

                        System.out.println("\nDocuments saved successfully.");
                        return Arrays.asList(outputDocxPath, outputTxtPath);
                    } finally {
                        afterEdit.dispose();
                    }
                } finally {
                    beforeEdit.dispose();
                }
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static void loadFromStream(Path inputFile) {

        try (InputStream inputStream = Files.newInputStream(inputFile)) {

            Editor editorFromStream = new Editor(inputStream);
            try {
                // do stuff...
            } finally {
                editorFromStream.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
    }

    public static void loadFromPath(Path inputFile) {
        Editor editorFromPath = new Editor(inputFile.toString());
        try {
            // do stuff...
        } finally {
            editorFromPath.dispose();
        }
    }

    public static void editXmlShort(Path inputFile) {
        final Path outputPath = makeOutputPath("WorkingWithEmail.html");

        // Load the file into the Editor class
        Editor editor = new Editor(inputFile.toString());
        try {
            // Load the document into an EditableDocument instance for editing
            EditableDocument editableDocument = editor.edit();

            try {
                // Retrieve the original text content with embedded resources
                String htmlWithEmbeddedResources = editableDocument.getEmbeddedHtml();

                // Send htmlWithEmbeddedResources to WYSIWYG-editor or somewhere else

                // Create a new EditableDocument instance with the updated content and resources
                EditableDocument afterEdit = EditableDocument.fromMarkup(htmlWithEmbeddedResources, editableDocument.getAllResources());
                try {
                    // Save the edited document to the specified output path
                    afterEdit.save(outputPath.toString());
                } finally {
                    afterEdit.dispose();
                }
            } finally {
                editableDocument.dispose();
            }
        } finally {
            editor.dispose();
        }
    }

    public static void highlightOptionsDemo() {
        XmlEditOptions editOptions = new XmlEditOptions();
        System.out.println("HighlightOptions isDefault: " + editOptions.getHighlightOptions().isDefault());

        // Retrieve the HighlightOptions instance from the XmlEditOptions
        XmlHighlightOptions highlightOptions = editOptions.getHighlightOptions();

        // Set XML tags font settings, e.g. font size, color, name, weight and decoration
        highlightOptions.getXmlTagsFontSettings().setSize(FontSize.Large);
        highlightOptions.getXmlTagsFontSettings().setColor(ArgbColors.Olive);

        // Set attribute names font settings, e.g. font name, size, weight, decoration and style
        highlightOptions.getAttributeNamesFontSettings().setName("Arial");
        highlightOptions.getAttributeNamesFontSettings().setLine(TextDecorationLineType.Underline);
        highlightOptions.getAttributeNamesFontSettings().setWeight(FontWeight.Lighter);

        // Set attribute values font settings, e.g. font size, weight, decoration, style and color
        highlightOptions.getAttributeValuesFontSettings().setLine(TextDecorationLineType.op_Addition(TextDecorationLineType.Underline, TextDecorationLineType.Overline));
        highlightOptions.getAttributeValuesFontSettings().setStyle(FontStyle.Italic);

        // Set CDATA sections font settings, e.g. font size, weight, decoration, style and color
        highlightOptions.getCDataFontSettings().setLine(TextDecorationLineType.LineThrough);
        highlightOptions.getCDataFontSettings().setSize(FontSize.Smaller);

        // Set HTML comments font settings, e.g. font name, size, weight, decoration, style and color
        highlightOptions.getHtmlCommentsFontSettings().setColor(ArgbColors.Lightgreen);
        highlightOptions.getHtmlCommentsFontSettings().setName("Courier New");

        // Set text node font settings, e.g. font weight, size, name, decoration, style and color
        highlightOptions.getInnerTextFontSettings().setWeight(FontWeight.fromNumber(300));
        highlightOptions.getInnerTextFontSettings().setSize(FontSize.XSmall);

        // Check that the HighlightOptions instance is not default
        System.out.println("HighlightOptions isDefault after editing: " + editOptions.getHighlightOptions().isDefault());

        // Reset the HighlightOptions instance to its default state
        highlightOptions.resetToDefault();

        // Check that the HighlightOptions instance is default again
        System.out.println("HighlightOptions isDefault after reset: " + editOptions.getHighlightOptions().isDefault());
    }

    public static void formatOptionsDemo() {
        XmlEditOptions editOptions = new XmlEditOptions();

        //Check that XmlFormatOptions is default for now
        System.out.println("XmlFormatOptions isDefault: " + editOptions.getFormatOptions().isDefault());
        XmlFormatOptions formatOptions = editOptions.getFormatOptions();

        //Set the flag to place each attribute-value pair on a new line
        formatOptions.setEachAttributeFromNewline(true);

        //Set the flag to place text nodes (textual content between and inside XML elements) on a new line
        formatOptions.setLeafTextNodesOnNewline(true);

        //Set a custom text indent using 'Length' data type, which is composed of value with unit
        formatOptions.setLeftIndent(Length.fromValueWithUnit(20, LengthUnit.Px));

        //Check that XmlFormatOptions is not default now
        System.out.println("XmlFormatOptions isDefault after editing: " + editOptions.getFormatOptions().isDefault());

        //Disable a left indent at all
        formatOptions.setLeftIndent(Length.UnitlessZero);
    }

    public static List<Path> complexEditDemo(Path inputFile) {
        final java.nio.file.Path output1Path = makeOutputPath("WorkingWithEmail.html");
        final java.nio.file.Path output2Path = makeOutputPath("WorkingWithEmail.html");
        try {
            // The first set of options
            XmlEditOptions editOptions1 = new XmlEditOptions();
            editOptions1.setRecognizeEmails(true);
            editOptions1.setRecognizeUris(true);
            editOptions1.setFixIncorrectStructure(true);
            editOptions1.setAttributeValuesQuoteType(QuoteType.SingleQuote);
            editOptions1.getFormatOptions().setLeftIndent(Length.parse("20px"));
            editOptions1.getHighlightOptions().getXmlTagsFontSettings().setLine(TextDecorationLineType.op_Addition(TextDecorationLineType.Underline, TextDecorationLineType.Overline));
            editOptions1.getHighlightOptions().getXmlTagsFontSettings().setWeight(FontWeight.Bold);

            // The second set of options
            XmlEditOptions editOptions2 = new XmlEditOptions();
            editOptions2.setTrimTrailingWhitespaces(true);
            editOptions2.setAttributeValuesQuoteType(QuoteType.DoubleQuote);
            editOptions2.getFormatOptions().setLeafTextNodesOnNewline(true);
            editOptions2.getHighlightOptions().getXmlTagsFontSettings().setSize(FontSize.XLarge);
            editOptions2.getHighlightOptions().getHtmlCommentsFontSettings().setLine(TextDecorationLineType.LineThrough);

            Editor editor = new Editor(inputFile.toString());
            try {
                // Edit the document with the first set of options
                final EditableDocument editableDocument1 = editor.edit(editOptions1);
                try {
                    // Save the edited document
                    editableDocument1.save(output1Path.toString());
                } finally {
                    editableDocument1.dispose();
                }

                // Edit the document with the second set of options
                final EditableDocument editableDocument2 = editor.edit(editOptions2);
                try {
                    // Save the edited document
                    editableDocument2.save(output2Path.toString());
                } finally {
                    editableDocument2.dispose();
                }

                return Arrays.asList(output1Path, output2Path);
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }

    public static TextualDocumentInfo getXmlMetaInfo(Path inputFile) {
        try {
            // Initialize the Editor with the input file path
            Editor editor = new Editor(inputFile.toString());
            try {
                // Retrieve document information and cast to TextualDocumentInfo
                IDocumentInfo info = editor.getDocumentInfo(null);
                TextualDocumentInfo xmlInfo = (TextualDocumentInfo) info;

                // Print details about the XML document
                System.out.printf("XML Info charset is '%s', system default is '%s'%n", xmlInfo.getEncoding(), Charset.defaultCharset());
                System.out.printf("XML is encrypted: %s%n", xmlInfo.isEncrypted());
                System.out.printf("XML pages count is: %d%n", xmlInfo.getPageCount());
                System.out.printf("XML format is %s: %s%n", TextualFormats.Xml, xmlInfo.getFormat() == TextualFormats.Xml);

                return xmlInfo;
            } finally {
                editor.dispose();
            }
        } catch (Exception e) {
            FailureRegister.getInstance().registerFailedSample(e);
        }
        return null;
    }
}
