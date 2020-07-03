---
id: convert-html-dom-to-document
url: editor/java/convert-html-dom-to-document
title: Convert HTML DOM to Document
weight: 3
description: ""
keywords: 
productName: GroupDocs.Editor for Java
hideChildren: False
---
In this area code samples are placed to show how to code samples to show how to convert HTML DOM to document.

### Get HTML DOM from string content with resources and save to document

Following code example shows how to Get HTML DOM from string content with resources and save to document.

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.storagePath + File.separator + fileName);

try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    String htmlContent = htmlDoc.getContent();
    OutputHtmlDocument editedHtmlDoc = OutputHtmlDocument.fromMarkup(htmlContent, CommonUtilities.getStoragePathWithResource(""));
    WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions();
    OutputStream out = new FileOutputStream(CommonUtilities.getOutputPath("output.docx"));
    EditorHandler.toDocument(editedHtmlDoc, out, saveOptions);

} catch (Exception ex){
     ex.getMessage();
}
```

### Get HTML DOM from file with resource folder and save to document

Following code example shows how to Get HTML DOM from file with resource folder and save to document.

```Java
try {
    OutputHtmlDocument editedHtmlDoc = OutputHtmlDocument.fromFile(CommonUtilities.getStoragePath(fileName), CommonUtilities.getStoragePathWithResource(""));
    WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions();
    OutputStream out = new FileOutputStream(CommonUtilities.getOutputPath("output.docx"));
    EditorHandler.toDocument(editedHtmlDoc, out, saveOptions);
} catch (Exception ex){
     ex.getMessage();
}
```
