/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage;

import java.io.FileInputStream;
import java.util.List;
import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.TextualFormats;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.css.datatypes.ArgbColor;
import com.groupdocs.editor.htmlcss.css.datatypes.ArgbColors;
import com.groupdocs.editor.htmlcss.css.datatypes.Length;
import com.groupdocs.editor.htmlcss.css.datatypes.LengthUnit;
import com.groupdocs.editor.htmlcss.css.properties.FontSize;
import com.groupdocs.editor.htmlcss.css.properties.FontStyle;
import com.groupdocs.editor.htmlcss.css.properties.FontWeight;
import com.groupdocs.editor.htmlcss.css.properties.TextDecorationLineType;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.htmlcss.serialization.QuoteType;
import com.groupdocs.editor.internal.c.a.ms.System.IO.Path;
import com.groupdocs.editor.metadata.IDocumentInfo;
import com.groupdocs.editor.metadata.TextualDocumentInfo;
import com.groupdocs.editor.options.*;
import org.testng.Assert;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author AlexT
 */
public class WorkingWithXml {

    public static void run() throws Exception {
//1. Get a path to the input XML file (or stream with file content)
        String inputFilePath = Constants.SAMPLE_XML;

        //2. Create Editor instance (not load options required)
        Editor editor = new Editor(inputFilePath);

        //3. Create XML editing options
        XmlEditOptions editOptions = new XmlEditOptions();
        editOptions.setAttributeValuesQuoteType(QuoteType.DoubleQuote);
        editOptions.setRecognizeEmails(true);
        editOptions.setRecognizeUris(true);
        editOptions.setTrimTrailingWhitespaces(true);

        //4. Create EditableDocument instance
        EditableDocument beforeEdit = editor.edit(editOptions);

        //5. Edit is somehow
        String originalTextContent = beforeEdit.getContent();
        String updatedTextContent = originalTextContent.replace("John", "Samuel");

        List<IHtmlResource> allResources = beforeEdit.getAllResources();

        //6. Create EditableDocument with updated content
        EditableDocument afterEdit = EditableDocument.fromMarkup(updatedTextContent, allResources);

        //7. Create WordProcessing save options
        WordProcessingSaveOptions wordSaveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);

        //8. Create TXT save options
        TextSaveOptions txtSaveOptions = new TextSaveOptions();
        txtSaveOptions.setEncoding(StandardCharsets.UTF_8);

        //9. Prepare paths for saving resultant DOCX and TXT files
        String outputWordPath = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(inputFilePath)), "docx");

        String outputTxtPath = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(inputFilePath)), "txt");

        //10. Save
        editor.save(afterEdit, outputWordPath, wordSaveOptions);
        editor.save(afterEdit, outputTxtPath, txtSaveOptions);
        System.out.println("WorkingWithXml routine has successfully finished");
    }

    public static void loadXml() throws Exception
    {
        String xmlInputPath = Constants.SAMPLE_XML;

        final FileInputStream xmlStream = new FileInputStream(xmlInputPath) ;

        Editor editorFromPath = new Editor(xmlInputPath);
        try
        {//from the path
            Editor editorFromStream = new Editor(new FileInputStream(xmlInputPath));
            try /*JAVA: was using*///from the stream
            {
                //Here two Editor instances can separately work with one file
            }
            finally {
                (editorFromStream).dispose();
            }
        }
        finally {
            (editorFromPath).dispose();
        }

    }

    public static void editXmlShort()
    {
        String xmlInputPath = Constants.SAMPLE_XML;
        String outputPath = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(xmlInputPath)), "html");

        Editor editor = new Editor(xmlInputPath);
        try /*JAVA: was using*/
        {
            final EditableDocument edited = editor.edit();
            try /*JAVA: was using*/
            {
                //Send to WYSIWYG-editor or somewhere else
                edited.save(outputPath);
            }
            finally {
                (edited).dispose();
            }
        }
        finally {
            (editor).dispose();
        }
    }

    public static void highlightOptionsDemo()
    {
        XmlEditOptions editOptions = new XmlEditOptions();
        Assert.assertTrue(editOptions.getHighlightOptions().isDefault());
        XmlHighlightOptions highlightOptions = editOptions.getHighlightOptions();

        //Setting XML tags font settings
        highlightOptions.getXmlTagsFontSettings().setSize(FontSize.Large);
        highlightOptions.getXmlTagsFontSettings().setColor(ArgbColors.Olive);

        //Setting attribute names font settings
        highlightOptions.getAttributeNamesFontSettings().setName("Arial");
        highlightOptions.getAttributeNamesFontSettings().setLine(TextDecorationLineType.Underline);
        highlightOptions.getAttributeNamesFontSettings().setWeight(FontWeight.Lighter);

        //Setting attribute values font settings
        highlightOptions.getAttributeValuesFontSettings().setLine(TextDecorationLineType.op_Addition(TextDecorationLineType.Underline,TextDecorationLineType.Overline));
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
        Assert.assertFalse(editOptions.getHighlightOptions().isDefault());

        //Resetting to default
        highlightOptions.resetToDefault();

        //Checking they are default again now
        Assert.assertTrue(editOptions.getHighlightOptions().isDefault());
    }

    public static void formatOptionsDemo()
    {
        XmlEditOptions editOptions = new XmlEditOptions();

        //Checking that options are default for now
        Assert.assertTrue(editOptions.getFormatOptions().isDefault());
        XmlFormatOptions formatOptions = editOptions.getFormatOptions();

        //Each attribute-value pair must be placed on a new line
        formatOptions.setEachAttributeFromNewline(true);

        //Text nodes (textual content between and inside XML elements) must be placed on a new line
        formatOptions.setLeafTextNodesOnNewline(true);

        //Setting a custom text indent using 'Length' data type, which is composed from value with unit
        formatOptions.setLeftIndent(Length.fromValueWithUnit(20, LengthUnit.Px));

        //Checking that options are not default now
        Assert.assertFalse(editOptions.getFormatOptions().isDefault());

        //Disabling a left indent at all
        formatOptions.setLeftIndent(Length.UnitlessZero);
    }

    public static void complexEditDemo()
    {
        String xmlInputPath = Constants.SAMPLE_XML;

        String outputPath1 = Constants.getOutputFilePath("1--"+Constants.removeExtension(Path.getFileName(xmlInputPath)), "html");
        String outputPath2 = Constants.getOutputFilePath("2--"+Constants.removeExtension(Path.getFileName(xmlInputPath)), "html");

        XmlEditOptions editOptions1 = new XmlEditOptions();
        editOptions1.setRecognizeEmails(true);
        editOptions1.setRecognizeUris(true);
        editOptions1.setFixIncorrectStructure(true);
        editOptions1.setAttributeValuesQuoteType(QuoteType.SingleQuote);
        editOptions1.getFormatOptions().setLeftIndent(Length.parse("20px"));
        editOptions1.getHighlightOptions().getXmlTagsFontSettings().setLine(TextDecorationLineType.op_Addition(TextDecorationLineType.Underline,TextDecorationLineType.Overline));
        editOptions1.getHighlightOptions().getXmlTagsFontSettings().setWeight(FontWeight.Bold);

        XmlEditOptions editOptions2 = new XmlEditOptions();
        editOptions2.setTrimTrailingWhitespaces(true);
        editOptions2.setAttributeValuesQuoteType(QuoteType.DoubleQuote);
        editOptions2.getFormatOptions().setLeafTextNodesOnNewline(true);
        editOptions2.getHighlightOptions().getXmlTagsFontSettings().setSize(FontSize.XLarge);
        editOptions2.getHighlightOptions().getHtmlCommentsFontSettings().setLine(TextDecorationLineType.LineThrough);

        Editor editor = new Editor(xmlInputPath);
        try /*JAVA: was using*/
        {
            final EditableDocument edited1 = editor.edit(editOptions1);
            try /*JAVA: was using*/
            {
                final EditableDocument edited2 = editor.edit(editOptions2);
                try /*JAVA: was using*/
                {
                    edited1.save(outputPath1);
                    edited2.save(outputPath2);
                }
                finally {
                    (edited2).dispose();
                }
            }
            finally {
                (edited1).dispose();
            }
        }
        finally {
            (editor).dispose();
        }
    }

    public static void getXmlMetainfo()
    {
        String xmlInputPath = Constants.SAMPLE_XML;

        Editor editor = new Editor(xmlInputPath);
        try /*JAVA: was using*/
        {
            IDocumentInfo info = editor.getDocumentInfo(null);
            TextualDocumentInfo xmlInfo = (TextualDocumentInfo)info;
            Assert.assertEquals(java.nio.charset.Charset.defaultCharset(), xmlInfo.getEncoding());
            Assert.assertFalse(xmlInfo.isEncrypted());
            Assert.assertEquals(1, xmlInfo.getPageCount());
            Assert.assertEquals(TextualFormats.Xml, xmlInfo.getFormat());
            //msAssert.Greater(xmlInfo.Size, 0);
        }
        finally {
            (editor).dispose();
        }
    }
}
