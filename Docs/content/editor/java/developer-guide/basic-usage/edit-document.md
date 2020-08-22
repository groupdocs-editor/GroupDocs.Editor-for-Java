---
id: edit-document
url: editor/java/edit-document
title: Edit document
weight: 3
description: "Follow this guide and learn how to edit text documents, spreadsheets and presentations using GroupDocs.Editor for Java API features."
keywords: Edit document, edit presentation, edit spreadsheet, GroupDocs.Editor
productName: GroupDocs.Editor for Java
hideChildren: False
---
This article describes how to open for editing a previously loaded document, which options should be applied, and how to send document content to the WYSIWYG HTML-editor or any other editing application.

When document is loaded into the instance of the [Editor](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editor) class, it is possible to open it for editing. In terms of [**GroupDocs.Editor**](https://products.groupdocs.com/editor/java), open a document for edit implies creating an instance of [EditableDocument](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editabledocument) class by calling an [Editor](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editor).[Edit()](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editor#edit()) instance method. There are two overloads of the [Edit](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editor#edit()) method. First one obtains a single parameter — inheritor of [IEditOptions](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor.options/ieditoptions) interface.  
Each format family has its own implementation of [IEditOptions](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor.options/ieditoptions) interface. They are listed in the table below.

| Format family | Example formats | Edit options class |
| --- | --- | --- |
| WordProcessing | DOC, DOCX, DOCM, DOT, ODT | [WordProcessingEditOptions](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor.options/wordprocessingeditoptions) |
| Spreadsheet | XLS, XLSX, XLSM, XLSB | [SpreadsheetEditOptions](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor.options/spreadsheeteditoptions) |
| Delimiter-Separated Values (DSV) | CSV, TSV | [DelimitedTextEditOptions](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor.options/delimitedtexteditoptions) |
| Presentation | PPT, PPTX, PPS, POT | [PresentationEditOptions](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor.options/presentationeditoptions) |
| Plain Text documents | TXT | [TextEditOptions](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor.options/texteditoptions) |
| XML | Any XML-based format like CSPROJ, SVG, and so on | [XmlEditOptions](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor.options/xmleditoptions) |

Second overload is parameterless — it chooses the most appropriate default edit options based on input document format.

[EditableDocument](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editabledocument) instance holds a version of input document, converted to internal intermediate format according to edit options. When it is ready, it can emit HTML, CSS and other appropriate content, that can be passed directly to the WYSIWYG-editor. This is demonstrated below.

```java
String inputFilePath = "C:\\input_path\\document.docx"; //path to some document
Editor editor = new Editor(inputFilePath, new WordProcessingLoadOptions());
EditableDocument openedDocument = editor.edit();//with default edit options

WordProcessingEditOptions editOptions = new WordProcessingEditOptions();
editOptions.setEnableLanguageInformation(true);
editOptions.setEnablePagination(true);

EditableDocument anotherOpenedDocument = editor.edit(editOptions);
```

There can be generated several [EditableDocument](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editabledocument) instances from a single [Editor](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editor) instance with different edit options. For example, for WordProcessing document, first time user can call [Edit()](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editor#edit()) method with disabled paged mode, and for the second time — with enabled. In other words, there can be generated several different [EditableDocument](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editabledocument) representations of the single original document. Other example — there can be multiple [EditableDocument](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editabledocument) instances for a single input Spreadsheet document, where each instance represents different tab of the Spreadsheet document. Such example is shown below.

```java
String inputXlsxPath = "C://input/spreadsheet.xlsx";
Editor editor = new Editor(inputXlsxPath, new SpreadsheetLoadOptions());

SpreadsheetEditOptions editOptions1 = new SpreadsheetEditOptions();
editOptions1.setWorksheetIndex(0);//index is 0-based, so this is 1st tab
editOptions1.setExcludeHiddenWorksheets(true);

SpreadsheetEditOptions editOptions2 = new SpreadsheetEditOptions();
editOptions2.setWorksheetIndex(1);//index is 0-based, so this is 2nd tab
editOptions2.setExcludeHiddenWorksheets(false);

EditableDocument firstTab = editor.edit(editOptions1);
EditableDocument secondTab = editor.edit(editOptions2);
```

When [EditableDocument](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editabledocument) instance is ready, it can emit HTML-markup, CSS-markup and other resources in different forms for passing them to the client-side WYSIWYG HTML-editor or any other application, that can edit HTML documents. It is briefly shown in the example below.

```java
EditableDocument document = editor.edit();
String bodyContent = document.getBodyContent();
List<IImageResource> onlyImages = document.getImages();
List<IHtmlResource> allResourcesTogether = document.getAllResources();
```

For more information about obtaining HTML markup and resources from [EditableDocument](https://apireference.groupdocs.com/editor/java/com.groupdocs.editor/editabledocument) please visit [Get HTML markup in different forms]({{< ref "editor/java/developer-guide/advanced-usage/working-with-editabledocument/get-html-markup-in-different-forms.md" >}}), [Working with resources]({{< ref "editor/java/developer-guide/advanced-usage/working-with-editabledocument/working-with-resources.md" >}}), and [Save HTML to folder]({{< ref "editor/java/developer-guide/advanced-usage/working-with-editabledocument/save-html-to-folder.md" >}}) articles.

## More resources
### Advanced Usage Topics
To learn more about document viewing features, please refer to the [advanced usage section]({{< ref "editor/java/developer-guide/advanced-usage/_index.md" >}}).

### GitHub Examples
You may easily run the code above and see the feature in action in our GitHub examples:
*   [GroupDocs.Editor for .NET examples, plugins, and showcase](https://github.com/groupdocs-editor/GroupDocs.Editor-for-.NET)    
*   [GroupDocs.Editor for Java examples, plugins, and showcase](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java)    
*   [Document Editor for .NET MVC UI Example](https://github.com/groupdocs-editor/GroupDocs.Editor-for-.NET-MVC)    
*   [Document Editor for .NET App WebForms UI Modern Example](https://github.com/groupdocs-editor/GroupDocs.Editor-for-.NET-WebForms)    
*   [Document Editor for Java App Dropwizard UI Modern Example](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard)    
*   [Document Editor for Java Spring UI Example](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java-Spring)    

### Free Online App
Along with full-featured Java library we provide simple, but powerful free Apps.  
You are welcome to edit your Microsoft Word (DOC, DOCX, RTF etc.), Microsoft Excel (XLS, XLSX, CSV etc.), Open Document (ODT, OTT, ODS) and other documents with free to use online **[GroupDocs Editor App](https://products.groupdocs.app/editor)**.
