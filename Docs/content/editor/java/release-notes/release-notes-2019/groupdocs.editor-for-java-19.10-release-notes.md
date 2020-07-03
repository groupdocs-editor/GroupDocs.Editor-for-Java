---
id: groupdocs-editor-for-java-19-10-release-notes
url: editor/java/groupdocs-editor-for-java-19-10-release-notes
title: GroupDocs.Editor for Java 19.10 Release Notes
weight: 1
description: ""
keywords: 
productName: GroupDocs.Editor for Java
hideChildren: False
---
{{< alert style="info" >}}This page contains release notes for GroupDocs.Editor for Java 19.10{{< /alert >}}

## Major features

The 19.10 release contains this major features:

*   credits support in Metered license, new paginal mode in WordProcessing module and improved rendering quality of WordProcessing format family.
*   heavily expanded XML processing with new features and options.
*   support of XML format (import and editing only) and extended properties for processing plain text documents.
*   paginal mode for the Words converter.

## Improvements and bug fixes

*   Reworked CSS representation of lists in order to improve list editing in HTML-editors.
*   Reworked HTML paragraphs representation for better compatibility with HTML-editors.
*   Improved space and gap distances between document elements.
*   Improved stability in multi-threaded scenarios.
*   Fixed issue with several input elements and Structure Document Tags (SDT), that causes exception during backward conversion.
*   Fixed issue with improper processing of bookmarks in rare scenarios.
*   Fixed issue with incorrect processing of complex shapes/

## Full List of Issues Covering all Changes in this Release

| Key | Summary | Category |
| --- | --- | --- |
| EDITORNET-1025 | Develop client-side JavaScript plugin for Words paginal mode for CKEditor | New Feature |
| EDITORNET-1026 | Implement support of Words paginal mode in GD.Editor DLL | New Feature |
| EDITORNET-1027 | Develop public API for the Words paginal mode | New Feature |
| EDITORNET-1109 | Develop XML module for opening, viewing and editing XML documents in GroupDocs.Editor | New Feature |
| EDITORNET-1110 | Implement ability to customize font settings and color for every distinct entity in XML structure | New Feature |
| EDITORNET-1111 | Develop option for specifying and applying the proper encoding | New Feature |
| EDITORNET-1112 | Develop mechanism for proper converting textual content into HTML | New Feature |
| EDITORNET-1080 | Implement extended text properties | New Feature |
| EDITORNET-1126 | Develop URI detection mechanism | New Feature |
| EDITORNET-1125 | Implement XML correctness algorithm | New Feature |
| EDITORNET-1124 | Implement quote type option | New Feature |
| EDITORNET-1123 | Implement email and URI validation in XML attributes | New Feature |
| EDITORNET-1189 | Implement various text effects | New Feature |
| EDITORNET-1185 | Implement new Metered support | New Feature |
| EDITORNET-1208 | Develop paginal mode in WordProcessing module | New Feature |
| EDITORNET-1030 | Improve compatibility of lists in HTML-editors for better editing | Improvement |
| EDITORNET-1031 | Improve compatibility of paragraphs with HTML-editors | Improvement |
| EDITORNET-1032 | Increase accuracy of space and gap distances between document elements | Improvement |
| EDITORNET-1033 | Improve stability in multi-threaded environment | Improvement |
| EDITORNET-1028 | Add support of Words paginal mode to the CkEditor.MvcSample sample project | Improvement |
| EDITORNET-1113 | Perform renaming of public API in accordance with new naming convention | Improvement |
| EDITORNET-1150 | Implement double slanted diagonal lines in table cell | Improvement |
| EDITORNET-1034 | Fix exception with SDT during backward conversion | Bug |
| EDITORNET-1035 | Fix bug with improper processing of bookmarks | Bug |
| EDITORNET-1036 | Fix issue with incorrect processing of unusual shapes in ShapeProcessor | Bug |
| EDITORNET-1122 | Fix detected issues in XML module | Bug |
| EDITORJAVA-203 | Cannot open .docx files | Bug |

## Public API and Backward Incompatible Changes

{{< alert style="info" >}}This section lists public API changes that were introduced in GroupDocs.Editor for Java 19.10. It includes not only new and obsoleted public methods, but also a description of any changes in the behavior behind the scenes in GroupDocs.Editor which may affect existing code. Any behavior introduced that could be seen as a regression and modifies existing behavior is especially important and is documented here.{{< /alert >}}

The 19.10 version of GroupDocs.Editor for Java introduces one brand new feature — paginal mode for the Words document processing. This mode allows to view, edit and save all types of Words documents in a "paged" representation, where all document's content is splitted onto separate pages, like it is present in Microsoft Word, Google Docs and other rich document editors.

The paged mode requires a lot of complex calculations and operations on the client-side. For example, when user inserts new content (text lines, images, tables, anything else) in the beginning or in the middle of the document, this new content pushes down all subsequent content. All the things which are below the place where you insert new content — text, footnotes, paragraphs, headings, shapes and images, tables, lists, bookmarks, input elements, — all of this should be shifted down, the document should be expanded from the bottom, with creating the new bottom pages if necessary. In counterpart, when user removes some content from the beginning or from the middle of the document, the empty space should be immediately collapsed and fulfilled by the content, that is located below. All of these operations may cause creating or removing the document pages, recalculating page numbers, footnote area, splitting tables and lists between the pages etc.

Achieving this functionality requires a heavy and complex client-side processing. Due to this from this moment GroupDocs.Editor contains a client-side part along with a server-side DLL. And because the CKEditor is one of the best free HTML-editors, we developed a plugin for it. In the near future we will develop the same plugins for other popular HTML-editors, TinyMCE will be the next. Need to emphasize: CKEditor with our plugin is necessary only for paginal mode for Words documents processing. If you want to use alternative float mode (which is available from the first versions of GroupDocs.Editor), or you're using GroupDocs.Editor for working with Cells documents, you are free to use any HTML-editors, and plugin is not required for you.

So, concluding, in order to use a paginal mode for the Words documents, the customers need to perform two mandatory thing:

1) Turn it on in the public API before opening the document.

2) Use CKEditor and properly install a plugin.

### Turning on paginal mode in public API

The GroupDocs.Editor.Words.WordsToHtml.WordToHtmlOptions class now contains a new property:



```Java
/**
 * <p>
 * Allows to enable or disable pagination in the resultant HTML document.
 * </p>
 */
getEnablePagination()
setEnablePagination(boolean value)
```

Also this parameter is present in several constructor overloads:



```Java
/**
 * <p>
 * Creates and returns a new instance of the WordToHtmlOptions class 
 * with specified pagination, password and {@code T:GroupDocs.Editor.Options.FontExtractionOptions} is default
 * </p>
 * @param enablePagination Enable or disable pagination in the resultant HTML document.
 * @param openingPassword The password, which will be used for opening Words document.
 * {@code <inheritdoc></inheritdoc>}
 */
public WordToHtmlOptions(boolean enablePagination, String openingPassword)
 
/**
 * <p>
 * Creates and returns a new instance of the WordToHtmlOptions class 
 * with specified pagination, password and {@code T:GroupDocs.Editor.Options.FontExtractionOptions}
 * </p>
 * @param enablePagination 
 * @param openingPassword The password, which will be used for opening Words document.
 * @param option 
 * {@code <inheritdoc></inheritdoc>}
 */
public WordToHtmlOptions(boolean enablePagination, String openingPassword, byte option)
```

When this parameter is enabled (via constructor or a property — not necessary), the GroupDocs.Editor generates HTML/CSS markup, which is slightly different compared to those, when float mode is used. In particular, some Words structure are generated in a different way, and markup itself contains much more meta-information about the document, which will be used later by the plugin.

### Installing a plugin in the CKEditor

If you want to add the plugin manually, you will need to:

1.  Downloaded and copy [plugin](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Examples/Plugin) into the plugins folder of your CKEditor installation. Example: /ckeditor/plugins/
    
2.  Enable the plugin by using the [extraPlugins](https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_config.html#cfg-extraPlugins) configuration setting. Example:
    

```JavaScript
CKEDITOR.replace('HtmlContent',{
   extraPlugins: 'gdeditor, gdeditorKey, gdeditorlink, gdeditormutation, gdeditorFootnotes, gdeditorenter',
   removePlugins: 'enterkey'
 });
```

### XML support

From this version GroupDocs.Editor introduces support of XML format — ability to open, view and edit XML documents like any other previously supported. This includes special support and recognition of XML tags, attributes with their values, XML declarations, CDATA sections, DOCTYPE definitions, and some other XML-specific entities. Users are able to customize formatting for every part and entity of XML structure.

In order to open XML document simply pass it to the EditorHadler class as a stream, and also pass an instance of XmlToHtmlOptions class. It has the next structure:



```Java
/**
 * <p>
 * Allows to specify custom options for loading XML (eXtensible Markup Language)
 * documents and converting them to the HTML
 * </p>
 */
public class XmlToHtmlOptions implements IDocumentLoadOptions {
    /**
     * <p>
     * Character encoding of the text document, which will be applied for its
     * opening. By default is null - internal document encoding will be applied.
     * </p>
     */
    public java.nio.charset.Charset getEncoding()
    /**
     * <p>
     * Character encoding of the text document, which will be applied for its
     * opening. By default is null - internal document encoding will be applied.
     * </p>
     */
    public setEncoding(java.nio.charset.Charset)
    /**
     * <p>
     * Allows to enable or disable mechanism for fixing corrupted XML structure.
     * By default is disabled (false).
     * </p><p>
     * <hr>By default only proper valid well-formed XML documents are
     * acceptable. When this option is enabled, GroupDocs.Editor will try to fix
     * corrupted XML structure if possible.</hr></p>
     */
    public boolean getFixIncorrectStructure()
    /**
     * <p>
     * Allows to enable or disable mechanism for fixing corrupted XML structure.
     * By default is disabled (false).
     * </p><p>
     * <hr>By default only proper valid well-formed XML documents are
     * acceptable. When this option is enabled, GroupDocs.Editor will try to fix
     * corrupted XML structure if possible.</hr></p>
     */
    public setFixIncorrectStructure(boolean)
    /**
     * <p>
     * Allows to enable URI recognition algorithm
     * </p>
     */
    public boolean getRecognizeUris()
    /**
     * <p>
     * Allows to enable URI recognition algorithm
     * </p>
     */
    public setRecognizeUris(boolean)
    /**
     * <p>
     * Allows to enable recognition algorithm for email addresses in attribute
     * values
     * </p>
     */
    public boolean getRecognizeEmails()
    /**
     * <p>
     * Allows to enable recognition algorithm for email addresses in attribute
     * values
     * </p>
     */
    public setRecognizeEmails(boolean)
    /**
     * <p>
     * Allows to enable the truncation of trailing whitespaces in the inner-tag
     * text. By default is disabled (false) - trailing whitespaces will be
     * preserved.
     * </p>
     */
    public boolean getTrimTrailingWhitespaces()
    /**
     * <p>
     * Allows to enable the truncation of trailing whitespaces in the inner-tag
     * text. By default is disabled (false) - trailing whitespaces will be
     * preserved.
     * </p>
     */
    public setTrimTrailingWhitespaces(boolean)
    /**
     * <p>
     * Allows to specify quote type (single or double quotes) for attribute
     * values. Double quotes are default.
     * </p>
     */
    public Integer getAttributeValuesQuoteType()
    /**
     * <p>
     * Allows to specify quote type (single or double quotes) for attribute
     * values. Double quotes are default.
     * </p>
     */
    public setAttributeValuesQuoteType(Integer)
    /**
     * <p>
     * Allows to adjust the highlighting, that will be applied to the XML
     * structure, when it is represented in HTML. By default is NULL — default
     * highlighting is applied.
     * </p>
     */
    public XmlHighlightOptions getHighlightOptions()
    /**
     * <p>
     * Allows to adjust the highlighting, that will be applied to the XML
     * structure, when it is represented in HTML. By default is NULL — default
     * highlighting is applied.
     * </p>
     */
    public setHighlightOptions(XmlHighlightOptions)
    /**
     * <p>
     * Allows to enable and adjust the XML formatting, that will be applied to
     * the XML structure, when it is represented in HTML. By default is NULL —
     * XML will be translated to the HTML "as is", without formatting.
     * </p>
     */
    public XmlFormattingOptions getFormattingOptions()
}
```

Lets review all these properties step-by-step:

1.  First property - *Encoding* - allows users to specify the character encoding of XML document as a System.Text.Encoding instance. Being not specified, the default encoding - UTF8 - will be applied.
2.  The *TrimTrailingWhitespaces* property, which is disabled by default, can apply mechanism for truncating the trailing whitespaces in the inner-tag textual content. Trailing spaces are invisible in HTML and in most cases are useless, so with this option you can easily eliminate them.
3.  The *XmlTagsFontSettings* option allows to specify font settings for the opening, closing and self-closed XML tags (including opening/closing brackets). If set to NULL, the default font settings will be applied.
4.  The *XmlTagsFontColor* option allows to specify font color for XML tags. If not set, the default color will be applied.
5.  The *AttributeNamesFontSettings* option allows to specify font settings for the attribute names within XML tags. If set to NULL, the default font settings will be applied.
6.  The **AttributeNamesFon*Color* option allows to specify font color for attribute names. If not set, the default color will be applied.
7.  The *AttributeValuesFontSettings* option allows to specify font settings for the attribute values within XML tags. If set to NULL, the default font settings will be applied.
8.  The **Attribute*Values*Fon*Color* option allows to specify font color for attribute values. If not set, the default color will be applied.
9.  The *InnerTextFontSettings* option allows to specify font settings for the inner text, which is inside and between XML tags. If set to NULL, the default font settings will be applied.
10.  The ***InnerText*Font*Color* option allows to specify font color for inner text. If not set, the default color will be applied.
11.  The *HtmlCommentsFontSettings* option allows to specify font settings for the HTML comments. If set to NULL, the default font settings will be applied.
12.  The ***HtmlComments**FontColor* option allows to specify font color for HTML comments. If not set, the default color will be applied.
13.  The *CDataFontSettings* option allows to specify font settings for the CDATA sections. If set to NULL, the default font settings will be applied.
14.  The **CData*FontColor* option allows to specify font color for CDATA sections. If not set, the default color will be applied.

Need to emphasize, that in this version of GroupDocs.Editor only import of XML documents is supported. You can open, view and edit them, but not save the edited document to the XML format. Alternatively, you can save it to one of WordProcessing formats, PDF, HTML or plain text (TXT). Support of export to XML will be present in future versions of GroupDocs.Editor.

### Enhanced text options

This version contains new advanced options for opening plain text (TXT) documents. The TextToHtmlOptions class now contains 3 new properties:



```Java
/**
 * <p>
 * Allows to specify how numbered list items are recognized when document is
 * imported from plain text format. The default value is true.
 * </p><p>
 * <hr>
 * If this option is set to false, lists recognition algorithm detects list
 * paragraphs, when list numbers ends with either dot, right bracket or
 * bullet symbols (such as "•", "*", "-" or "o"). If this option is set to
 * true, whitespaces are also used as list number delimeters: list
 * recognition algorithm for Arabic style numbering (1., 1.1.2.) uses both
 * whitespaces and dot (".") symbols.
 * </hr></p>
 */
public boolean getDetectNumberingWithWhitespaces()
public setDetectNumberingWithWhitespaces(boolean)
  
/**
 * <p>
 * Gets or sets preferred option of a leading space handling. By default
 * converts leading spaces to the left indent.
 * </p>
 */
public int getLeadingSpacesOptions()
public setLeadingSpacesOptions(int)
  
/**
 * <p>
 * Gets or sets preferred option of a trailing space handling. By default
 * truncates all trailing spaces.
 * </p>
 */
public int getTrailingSpacesOptions()
public setTrailingSpacesOptions(int)
```

1.  *DetectNumberingWithWhitespaces*. By default GroupDocs.Editor tries to recognize lists in the content of the plain text files by several specific templates of list formatting. For example, several line breaks, which start with consecutive numbering or same bullet characters, will be recognized as lists, and output HTML will have the corresponding list structure. However, in several scenarios it is hard to determine whether some text configuration implies list or not. This option enables list recognition, when list item marks are succeeded not only by special bullet marks, but also with whitespaces.
2.  *LeadingSpacesOptions*. This option allows to choose one of three available strategies of processing leading spaces in text lines. By default leading whitespaces are converted to the text indent in the resultant HTML document. Other options are: preserve these spaces without touching them or completely remove this.
3.  *TrailingSpacesOptions*. This option is almost same as previous one with one exception — there are no indents for trailing whitespaces in text lines. So, by default all trailing whitespaces are completely truncated, as they are invisible and almost useless in the resultant HTML. However, there is an ability to preserve them by selecting corresponding value of *TextTrailingSpacesOptions* enum.

### Public API changes

There were two major changes in public API.

First of all, there was a massive renaming of namespaces and types to meet new GroupDocs naming convention. In general, now Words is transformed to WordProcessing, and Cells to Spreadsheet.

Secondly, all options were moved to the GroupDocs.Editor.Options namespace.

1.  GroupDocs.Editor.Words.HtmlToWords.DocumentProtection -> GroupDocs.Editor.Options.DocumentProtection
2.  GroupDocs.Editor.Words.HtmlToWords.WordProcessingSaveOptions -> GroupDocs.Editor.Options.WordProcessingSaveOptions
3.  GroupDocs.Editor.Words.HtmlToWords.DocumentProtectionType -> GroupDocs.Editor.Options.DocumentProtectionType
4.  GroupDocs.Editor.Words.HtmlToWords.WordFormats-> GroupDocs.Editor.Options.WordProcessingFormats
5.  GroupDocs.Editor.Words.WordsToHtml.WordToHtmlOptions-> GroupDocs.Editor.Options.WordProcessingToHtmlOptions
6.  GroupDocs.Editor.Cells.CellsToHtml CellsToHtmlOptions -> GroupDocs.Editor.Options.SpreadsheetToHtmlOptions
7.  GroupDocs.Editor.Cells.HtmlToCells.CellsSaveOptions \-> GroupDocs.Editor.Options.SpreadsheetSaveOptions
8.  GroupDocs.Editor.Cells.HtmlToCells.WorksheetProtection -> GroupDocs.Editor.Options.WorksheetProtection
9.  GroupDocs.Editor.Cells.HtmlToCells.CellFormats -> GroupDocs.Editor.Options.SpreadsheetFormats
10.  GroupDocs.Editor.Cells.HtmlToCells.WorksheetProtectionType -> GroupDocs.Editor.Options.WorksheetProtectionType

### Improved and expanded XML module

New XML module was the main feature of GroupDocs.Editor. Now, with 19.10 version, this module was heavily expanded and improved. We have added 4 new features along with corresponding public options to the public API. They are listed below.

#### XML Correctness Option

XML format has strict rules, and every XML document should follow them. However not all XML documents are well-formed. Some documents may be broken in different way:

*   Missing XML declaration
*   Missing of single root tag
*   Missing opening tags
*   Missing closing tags
*   Overlapping tags
*   Unclosed brackets
*   And much more...

From its first release, the XML converter was able to show any XML document with any kind of errors (unlike most of XML parsers, that throw an exception on first occurred error). However, with 19.10 release, GroupDocs.Editor is able not only to show broken XML document, but also to fix it! It is possible to fix all previously listed errors: close unclosed tags, remove unopened tags, fix overlapped tags and so on.

This feature is represented as an option, which is located in the `XmlToHtmlOptions` class. It has the next form:



```Java
/**
 * <p>
 * Allows to enable or disable mechanism for fixing corrupted XML structure.
 * By default is disabled (false).
 * </p><p>
 * <hr>By default only proper valid well-formed XML documents are
 * acceptable. When this option is enabled, GroupDocs.Editor will try to fix
 * corrupted XML structure if possible.</hr></p>
 */
public boolean getFixIncorrectStructure()
public setFixIncorrectStructure(boolean)
```

By default it is disabled — XML correctness algorithm is not applied.

#### URI recognition and processing

In previous version XML converter processed any URIs as an ordinary text. It was not able to recognize the URI in text block and process it accordingly. So, when converted to HTML, such links were represented as a usual text, and you were not able to edit or follow them. But with 19.10 release we're introducing a new feature: URI recognizer. This mechanism scans every piece of text between XML tags and also every value of every attribute, searching for URIs. If found, links are processed in specific way, and in the resultant HTML they are present inside the A tag, so you can follow them and edit as a link, not as a text.

This feature is represented as an option, which is located in the `XmlToHtmlOptions` class. It has the next form:



```Java
/**
 * <p>
 * Allows to enable URI recognition algorithm
 * </p>
 */
public boolean getRecognizeUris()
public setRecognizeUris(boolean)
```

By default it is disabled — URIs will not be recognized. But take a note, that enabling URI detection will significantly decrease the performance, because the detection algorithm should check every piece of text over all of the document.

#### Email recognition in attribute values

Along with URI recognition, 19.10 release also contains a mechanism for detection and processing of the email addresses. However, unlike the URI detection, it scans only values of XML attributes, which are parts of XML elements. Like with URI detection, once valid email address is found, in the resultant HTML markup it will be present as a usual link (with A tag), which you can follow or edit.

This feature is represented as an option, which is located in the `XmlToHtmlOptions` class. It has the next form:



```Java
/**
 * <p>
 * Allows to enable recognition algorithm for email addresses in attribute
 * values
 * </p>
 */
public final boolean getRecognizeEmails()
public setRecognizeEmails(boolean value)
```

And, as in all previous cases, this option is disabled by default.

#### Quote type selection

XML standard defines two quote types, that can be used for delimiting attribute values from surrounding content: single quote (U+0027 APOSTROPHE character) and double quote (U+0022 QUOTATION MARK character). With 19.10 release it is possible to forcibly apply specific quote type, that will be present in resultant HTML. So it is no matter, which exact quotes are present in the input XML — resultant HTML will contain unified quotes of a single type.

This feature is represented as an option, which is located in the `XmlToHtmlOptions` class. It has the next form:



```Java
/**
 * <p>
 * Allows to specify quote type (single or double quotes) for attribute
 * values. Double quotes are default.
 * </p>
 */
public Integer getAttributeValuesQuoteType()
public setAttributeValuesQuoteType(Integer)
```

By default double quotes will be applied.

### Double slanted lines in table cells

Word Processing format along with MS Word allow to draw slanted diagonal lines in table cells. Previously GroupDocs.Editor supported such lines, but only when there is only one diagonal line in table cell, and was unable to process a situation, when two lines are present in one cell simultaneously. Now the Word Processing module was improved, and double slanted lines are converted to the HTML fully correct. This improvement doesn't require any code change and has no affect on any of options or API.
