---
id: groupdocs-editor-for-java-20-8-release-notes
url: editor/java/groupdocs-editor-for-java-20-8-release-notes
title: GroupDocs.Editor for Java 20.8 Release Notes
weight: 7
description: ""
keywords: 
productName: GroupDocs.Editor for Java
hideChildren: False
---
{{< alert style="info" >}}This page contains release notes for GroupDocs.Editor for Java 20.8{{< /alert >}}

## Major Features

{{< alert style="danger" >}}In this version we're introducing new public API which was designed to be simple and easy to use. For more details about new API please check Public Docs section.{{< /alert >}}

Also this release contains:
*   Full support of Presentation format family, that includes PPT, PPTX, PPTM, POT, PPS, and more Presentation formats. Presentation support came with a set of new load/edit/save option classes, formats, its support was also added to the GetDocumentInfo method.
*   Expanded features in the Format-representing structs, which includes parsing from file extension and enumerating over all formats within given format family.
*   New static factory in EditableDocument class, that allows to open EditableDocument from inner content of HTML->BODY element and corresponding resource folder.

#### New GetDocumentInfo method

Along with new API, GroupDocs.Editor contains new GetDocumentInfo method, that allows to get metainfo about the input document without editing it:

*   Family format and exact document format.
*   Encryption flag.
*   Number of pages/tabs.
*   Size.

#### New text save options

All previous versions of GroupDocs.Editor don't contain specialized options, responsible for saving edited document in plain text format — in order to do this user should use WordProcessing save options, which don't allow to configure parameters while saving into text. This 20.8 version of GroupDocs.Editor contains new `TextSaveOptions` class, that is responsible especially for saving edited document to the plain text format. `TextSaveOptions` class contains the next new settings:

*   Ability to specify whether to add bi-directional marks before each BiDi run when exporting in plain text format.
*   Ability to specify whether the program should attempt to preserve layout of tables when saving in the plain text format.

### Bugs

*   When performing full roundtrip without editing in Presentation document in trial mode, an exception is thrown.
*   In some cases there are undisposed resources in the EditableDocument class even after calling the "Dispose()" method.
*   Bindings for Aspose components are missed in 19.10.
*   Fixed bug with a document, that was incorrectly rendered to HTML in paginal mode and then incorrectly converted back to WordProcessing.

### Improvements

*   Improved XML-comments for the GroupDocs.Editor.Editor main class.
*   Better support of opening HTML document from inner-BODY markup
*   Added support of BUTTON HTML element
*   Added support of MACROBUTTON field in WordProcessing documents
*   Added support of STRIKE HTML element
*   New feature, that allows to set custom class name for all form-fields with corresponding public options.
*   New option, which allows to specify a text direction for the input plain text document (TXT) before its editing.
*   Added support of PDF 1.7 standard while saving edited document to PDF.


All these improvements are "internal", they do not affect public API.

## Other features

Lot of minor and major bug fixes and improvements over all code base.

## Full List of Issues Covering all Changes in this Release

| Key | Summary | Category |
| --- | --- | --- |
| EDITORNET-1286 | New public API | Feature |
| EDITORNET-1287 | Implement GetDocumentInfo method | Feature |
| EDITORNET-1288 | New TextSaveOptions | Feature |
| EDITORNET-1317 | Add Presentations editing support | Feature |
| EDITORNET-1318 | Add Presentation support into GetDocumentInfo method | Feature |
| EDITORNET-1308 | Setup binding redirects for third-party components | Feature |
| EDITORNET-1409 | Add .NET Standard 2.0 support | Feature |
| EDITORNET-1448 | Implement extension parsing for all formats | Feature |
| EDITORNET-1449 | Implement possibility to enumerate over all formats | Feature |
| EDITORNET-1422 | Implement parsing of inner content from HTML BODY element | Feature |
| EDITORNET-1423 | Implement resource fetching and parsing for HTML BODY content | Feature |
| EDITORNET-1467 | Add ability to set custom class name for all form-fields with corresponding public options | Feature |
| EDITORNET-1512 | Add support of text direction in input plain text document | Feature |
| EDITORNET-1320 | Exception during roundtrip in Presentation module in trial mode | Bug |
| EDITORNET-1353 | Undisposed resources in EditableDocument class | Bug |
| EDITORNET-1354 | Missing bindings for Aspose components | Bug |
| EDITORNET-1356 | Error While Getting DOCX document HTML | Bug |
| EDITORNET-1374 | Fix bug with missing binding for Aspose components | Bug |
| EDITORNET-1419 | Bug with duplicated images in EditableDocument | Bug |
| EDITORNET-1418 | Internal error in Bookmark processor | Bug |
| EDITORNET-1414 | Fix bug with locked HTML resources | Bug |
| EDITORNET-1380 | Exception while getting DOCX document HTML | Bug |
| EDITORNET-1430 | Additional style sheet is not saved in embedded version in WordProcessing paginal mode | Bug |
| EDITORNET-1452 | Bug in HTML attribute parsing | Bug |
| EDITORNET-1457 | Exception while opening DOCX with specific field | Bug |
| EDITORNET-1458 | Exception in .NET Standard version of GD.Editor | Bug |
| EDITORNET-1450 | MSI package signature is not applied during release build | Bug |
| EDITORNET-1465 | Fix bug with locked HTML resources | Bug |
| EDITORNET-1504 | WordProcessing document is incorrectly rendered in paginal mode | Bug |
| EDITORNET-1355 | Improve XML-comments in the Editor class | Improvement |
| EDITORNET-1411 | Updates and improvements in XML comments, documentation and NUSPEC description | Improvement |
| EDITORNET-1410 | Generate new ProjectGuid and UpgradeCode for MSI package | Improvement |
| EDITORNET-1420 | Improve formats-representing types | Improvement |
| EDITORNET-1421 | Implement better support of truncated markup | Improvement |
| EDITORNET-1454 | Improve opening EditableDocument from inner-body markup by supporting a root BODY element | Improvement |
| EDITORNET-1460 | Add support of BUTTON element | Improvement |
| EDITORNET-1461 | Implement support of MACROBUTTON field | Improvement |
| EDITORNET-1464 | Fix bug and add complete support of obsolete STRIKE HTML element | Improvement |
| EDITORNET-1505 | Add support of PDf 1.7 | Improvement |


# Public API and Backward Incompatible Changes

{{< alert style="info" >}}This section lists public API changes that were introduced in GroupDocs.Editor for Java 20.8. It includes not only new and obsoleted public methods, but also a description of any changes in the behavior behind the scenes in GroupDocs.Editor which may affect existing code. Any behavior introduced that could be seen as a regression and modifies existing behavior is especially important and is documented here.{{< /alert >}}

## New getDocumentInfo method

The new `Editor` class, which supersedes deprecated `EditorHandler`, contains a new instance method `getDocumentInfo` with the next signature:

```java
public IDocumentInfo getDocumentInfo(String password)
```
  
Once the document was loaded into the `Editor` class, this method can be called to obtain metainfo about the loaded document without actual opening it for editing.

## New TextSaveOptions class

The `Options` namespace now contains a new `TextSaveOptions` class, that is responsible for saving `EditableDocument` with edited document content to the plain text format. It has the next signature:

```java
/// <summary>
/// Allows to specify custom options for generating and saving plain text (TXT) documents
/// </summary>
public final class TextSaveOptions implements ISaveOptions {
    /**
     * Character encoding of the text document, which will be applied for its saving
     */
    public java.nio.charset.Charset getEncoding()

    /**
     * Character encoding of the text document, which will be applied for its saving
     */
    public void setEncoding(java.nio.charset.Charset value)

    /**
     * Specifies whether to add bi-directional marks before each BiDi run when
     * exporting in plain text format
     */
    public boolean getAddBidiMarks()

    /**
     * Specifies whether to add bi-directional marks before each BiDi run when
     * exporting in plain text format
     */
    public void setAddBidiMarks(boolean)

    /**
     * Specifies whether the program should attempt to preserve layout of tables
     * when saving in the plain text format. The default value is false.
     */
    public boolean getPreserveTableLayout()

    /**
     * Specifies whether the program should attempt to preserve layout of tables
     * when saving in the plain text format. The default value is false.
     */
    public void setPreserveTableLayout(boolean )
}
```

## Presentation module - ability to load, open for editing, edit and save Presentation documents. This feature is represented in public API by the next new public types.
    
| Type name | Responsibility |
| --- | --- |
| PresentationLoadOptions | Allows to specify custom options for loading documents of all supportable Presentation formats like PPT(X), PPTM, PPS(X) etc. |
| PresentationEditOptions | Allows to specify custom options for editing documents of all supportable Presentation (PowerPoint-compatible) formats |
| PresentationSaveOptions | Allows to specify custom options for generating and saving Presentation (PowerPoint-compatible) documents |
| PresentationDocumentInfo | Represents metadata of one Presentation document |
| PresentationFormats | Encapsulates all Presentation formats |

## Represented in public API by the next new public types and methods.
    
| Type name | Member name | Responsibility |
| --- | --- | --- |
| WordProcessingFormats.AllEnumerable | N/A | Internal class, that enables enumeration over all formats within WordProcessingFormats |
| SpreadsheetFormats.AllEnumerable | N/A | Internal class, that enables enumeration over all formats within SpreadsheetFormats |
| PresentationFormats.AllEnumerable | N/A | Internal class, that enables enumeration over all formats within PresentationFormats |
| TextualFormats.AllEnumerable | N/A | Internal class, that enables enumeration over all formats within TextualFormats |
| WordProcessingFormats | All | Static readonly field, that returns an WordProcessingFormats.AllEnumerable instance |
| SpreadsheetFormats | All | Static readonly field, that returns an SpreadsheetFormats .AllEnumerable instance |
| PresentationFormats | All | Static readonly field, that returns an PresentationFormats .AllEnumerable instance |
| TextualFormats | All | Static readonly field, that returns an TextualFormats .AllEnumerable instance |
| WordProcessingFormats | FromExtension | Static method, that parses a string and returns appropriate WordProcessing format |
| SpreadsheetFormats | FromExtension | Static method, that parses a string and returns appropriate Spreadsheet format |
| PresentationFormats | FromExtension | Static method, that parses a string and returns appropriate Presentation format |
| TextualFormats | FromExtension | Static method, that parses a string and returns appropriate Textual format |
    
      
## Representing by only one new method in the EditableDocument class:
    
```java
/// <summary>
/// Static factory, that creates an instance of EditableDocument from a specified HTML markup,
/// that doesn't contain an HTML header, but only inner markup of HTML BODY element, and from resources, located in the folder, specified by the full path
/// </summary>
/// <param name="htmlBodyContent">String, that contains raw HTML markup, which is located inside HTML->BODY element (without BODY itself),
/// that should be parsed. Cannot be NULL, empty or invalid.</param>
/// <param name="resourceFolderPath">Mandatory path to the folder with resources. All stylesheets, which are located in this folder, will be used.</param>
/// <returns>New non-null instance of EditableDocument</returns>
public static EditableDocument fromBodyMarkupAndResourceFolder(String htmlBodyContent, String resourceFolderPath)
```
    
## New property in WordProcessingEditOptions

Implementing a EDITORNET-1467 ticket has a result in form of a new public property in the `GroupDocs.Editor.Options.WordProcessingEditOptions` class:

```java
public String getInputControlsClassName() 
public void setInputControlsClassName(String)
```

You can find more info about this feature in the article "[Adding class name to input controls]({{< ref "editor/java/developer-guide/advanced-usage/working-with-wordprocessing-documents/adding-class-name-to-input-controls.md" >}})".

## New enum and property in TextEditOptions

Implementing a EDITORNET-1512 ticket caused a new enum `GroupDocs.Editor.Options.TextDirection`:

```java
public final class TextDirection extends Enum {

    private TextDirection() {
    }
    /**
     * Left-to-Right direction, usual text, default value.
     */
    public static final int LeftToRight = 0;

    /**
     * Right-to-Left direction
     */
    public static final int RightToLeft = 1;

    /**
     * Auto-detect direction. When this option is selected and text contains
     * characters belonging to RTL scripts, the document direction will be set
     * automatically to RTL.
     */
    public static final int Auto = 2;    
}
```

This enum is now used in the `GroupDocs.Editor.Options.TextEditOptions` class:

```java
public int getDirection()
public void setDirection(int)
```

## New enum value in PdfCompliance enum

The EDITORNET-1505 improvement is represented by a new value in the `GroupDocs.Editor.Options.PdfCompliance`:

```java
public final class PdfCompliance extends Enum {
    ...
    public static final int Pdf17 = 1;
    ...
}
```