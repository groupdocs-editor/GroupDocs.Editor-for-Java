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
            //1. Load to the Editor class
            Editor editor = new Editor(inputFile.toString());
            try {
                //3. Create XML editing options
                XmlEditOptions editOptions = new XmlEditOptions();
                editOptions.setAttributeValuesQuoteType(QuoteType.DoubleQuote);
                editOptions.setRecognizeEmails(true);
                editOptions.setRecognizeUris(true);
                editOptions.setTrimTrailingWhitespaces(true);

                //4. Create EditableDocument instance
                EditableDocument beforeEdit = editor.edit(editOptions);
                try {

                    //5. Edit is somehow
                    String originalTextContent = beforeEdit.getContent();
                    String updatedTextContent = originalTextContent.replace("John", "Samuel");

                    List<IHtmlResource> allResources = beforeEdit.getAllResources();

                    //6. Create EditableDocument with updated content
                    EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);
                    try {

                        //7. Create WordProcessing save options
                        WordProcessingSaveOptions wordSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

                        //8. Create TXT save options
                        TextSaveOptions txtSaveOptions = new TextSaveOptions();
                        txtSaveOptions.setEncoding(StandardCharsets.UTF_8);

                        //9. Prepare paths for saving resultant DOCX and TXT files
                        final java.nio.file.Path outputDocxPath = makeOutputPath("WorkingWithXml.docx");
                        final java.nio.file.Path outputTxtPath = makeOutputPath("WorkingWithXml.txt");

                        //10. Save
                        editor.save(afterEdit, outputDocxPath.toString(), wordSaveOptions);
                        editor.save(afterEdit, outputTxtPath.toString(), txtSaveOptions);

                        System.out.println("\nDocuments saved successfully.");
                        return Arrays.asList(outputDocxPath, outputTxtPath);
                        //10. Dispose all resources
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
        final java.nio.file.Path outputPath = makeOutputPath("WorkingWithEmail.html");

        Editor editor = new Editor(inputFile.toString());
        try {
            final EditableDocument editableDocument = editor.edit();
            try {
                final String htmlWithEmbeddedResources = editableDocument.getEmbeddedHtml();
                //Send htmlWithEmbeddedResources to WYSIWYG-editor or somewhere else

                editableDocument.save(outputPath.toString());
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
        XmlHighlightOptions highlightOptions = editOptions.getHighlightOptions();

        //Setting XML tags font settings
        highlightOptions.getXmlTagsFontSettings().setSize(FontSize.Large);
        highlightOptions.getXmlTagsFontSettings().setColor(ArgbColors.Olive);

        //Setting attribute names font settings
        highlightOptions.getAttributeNamesFontSettings().setName("Arial");
        highlightOptions.getAttributeNamesFontSettings().setLine(TextDecorationLineType.Underline);
        highlightOptions.getAttributeNamesFontSettings().setWeight(FontWeight.Lighter);

        //Setting attribute values font settings
        highlightOptions.getAttributeValuesFontSettings().setLine(TextDecorationLineType.op_Addition(TextDecorationLineType.Underline, TextDecorationLineType.Overline));
        highlightOptions.getAttributeValuesFontSettings().setStyle(FontStyle.Italic);

        //Setting CDATA sections font settings
        highlightOptions.getCDataFontSettings().setLine(TextDecorationLineType.LineThrough);
        highlightOptions.getCDataFontSettings().setSize(FontSize.Smaller);

        //Setting HTML comments font settings
        highlightOptions.getHtmlCommentsFontSettings().setColor(ArgbColors.Lightgreen);
        highlightOptions.getHtmlCommentsFontSettings().setName("Courier New");

        //Setting text node font settings
        highlightOptions.getInnerTextFontSettings().setWeight(FontWeight.fromNumber(300));
        highlightOptions.getInnerTextFontSettings().setSize(FontSize.XSmall);

        //Checking they are not default
        System.out.println("HighlightOptions isDefault after editing: " + editOptions.getHighlightOptions().isDefault());

        //Resetting to default
        highlightOptions.resetToDefault();

        //Checking they are default again now
        System.out.println("HighlightOptions isDefault after reset: " + editOptions.getHighlightOptions().isDefault());
    }

    public static void formatOptionsDemo() {
        XmlEditOptions editOptions = new XmlEditOptions();

        //Checking that options are default for now
        System.out.println("FormatOptions isDefault: " + editOptions.getFormatOptions().isDefault());
        XmlFormatOptions formatOptions = editOptions.getFormatOptions();

        //Each attribute-value pair must be placed on a new line
        formatOptions.setEachAttributeFromNewline(true);

        //Text nodes (textual content between and inside XML elements) must be placed on a new line
        formatOptions.setLeafTextNodesOnNewline(true);

        //Setting a custom text indent using 'Length' data type, which is composed of value with unit
        formatOptions.setLeftIndent(Length.fromValueWithUnit(20, LengthUnit.Px));

        //Checking that options are not default now
        System.out.println("FormatOptions isDefault after editing: " + editOptions.getFormatOptions().isDefault());

        //Disabling a left indent at all
        formatOptions.setLeftIndent(Length.UnitlessZero);
    }

    public static List<Path> complexEditDemo(Path inputFile) {
        final java.nio.file.Path output1Path = makeOutputPath("WorkingWithEmail.html");
        final java.nio.file.Path output2Path = makeOutputPath("WorkingWithEmail.html");
        try {
            XmlEditOptions editOptions1 = new XmlEditOptions();
            editOptions1.setRecognizeEmails(true);
            editOptions1.setRecognizeUris(true);
            editOptions1.setFixIncorrectStructure(true);
            editOptions1.setAttributeValuesQuoteType(QuoteType.SingleQuote);
            editOptions1.getFormatOptions().setLeftIndent(Length.parse("20px"));
            editOptions1.getHighlightOptions().getXmlTagsFontSettings().setLine(TextDecorationLineType.op_Addition(TextDecorationLineType.Underline, TextDecorationLineType.Overline));
            editOptions1.getHighlightOptions().getXmlTagsFontSettings().setWeight(FontWeight.Bold);

            XmlEditOptions editOptions2 = new XmlEditOptions();
            editOptions2.setTrimTrailingWhitespaces(true);
            editOptions2.setAttributeValuesQuoteType(QuoteType.DoubleQuote);
            editOptions2.getFormatOptions().setLeafTextNodesOnNewline(true);
            editOptions2.getHighlightOptions().getXmlTagsFontSettings().setSize(FontSize.XLarge);
            editOptions2.getHighlightOptions().getHtmlCommentsFontSettings().setLine(TextDecorationLineType.LineThrough);

            Editor editor = new Editor(inputFile.toString());
            try {
                final EditableDocument editableDocument1 = editor.edit(editOptions1);
                try {
                    editableDocument1.save(output1Path.toString());
                } finally {
                    editableDocument1.dispose();
                }

                final EditableDocument editableDocument2 = editor.edit(editOptions2);
                try {
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
            Editor editor = new Editor(inputFile.toString());
            try {
                IDocumentInfo info = editor.getDocumentInfo(null);
                TextualDocumentInfo xmlInfo = (TextualDocumentInfo) info;
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
