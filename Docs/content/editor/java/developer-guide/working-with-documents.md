---
id: working-with-documents
url: editor/java/working-with-documents
title: Working with Documents
weight: 2
description: ""
keywords: 
productName: GroupDocs.Editor for Java
hideChildren: False
---
## Convert HTML DOM to Word document

In this area code samples are placed to show how to convert HTML DOM to Word document.

### Save to Words RTF document

Following code example shows how to save to Word RTF document.



```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));

try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    // Initialize with HTML markup of the edited document
    String htmlContent = htmlDoc.getContent();
    OutputHtmlDocument editedHtmlDoc = OutputHtmlDocument.fromMarkup(htmlContent, CommonUtilities.getStoragePathWithResource(""));
    WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions();
    saveOptions.setOutputFormat(WordProcessingFormats.Rtf);
    OutputStream out = new FileOutputStream(CommonUtilities.getOutputPath("output.rtf"));
    EditorHandler.toDocument(editedHtmlDoc, out, saveOptions);    

} catch (Exception ex){
    ex.getMessage();
}
```

### Save to Word document with options

Following code example shows how to save to Word document with options.



```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName)); 

try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    // Initialize with HTML markup of the edited document
    String htmlContent = htmlDoc.getContent();
    OutputHtmlDocument editedHtmlDoc = OutputHtmlDocument.fromMarkup(htmlContent, CommonUtilities.getStoragePathWithResource(""));
    WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx, "12345678");
    OutputStream out = new FileOutputStream(CommonUtilities.getOutputPath("output.docx"));
    saveOptions.setLocale(Locale.getDefault());
    saveOptions.setLocaleBi(Locale.getDefault());
    saveOptions.setLocaleFarEast(Locale.getDefault());
    EditorHandler.toDocument(editedHtmlDoc, out, saveOptions);  
} catch (Exception ex){
    ex.getMessage();
}
```
