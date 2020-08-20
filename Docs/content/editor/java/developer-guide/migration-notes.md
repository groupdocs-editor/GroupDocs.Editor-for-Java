---
id: migration-notes
url: editor/java/migration-notes
title: Migration Notes
weight: 3
description: ""
keywords: 
productName: GroupDocs.Editor for Java
hideChildren: False
---
### Why To Migrate?

Here are the key reasons to use the new updated API provided by GroupDocs.Editor for Java since version 20.8:

*   **Editor** class introduced as a **single entry point** to manage the document editing process to any supported file format (instead of **EditorHander** class from previous versions).     
*   Product architecture was redesigned from scratch in order to **decreased memory usage** (from 10% to 400% approx. depending on document type).    
*   Document **editing** and **saving options simplified** so it’s easy to instantiate proper options class and control over document editing and saving processes.  
    

### How To Migrate?

Here is a brief comparison of how to edit document in HTML form using old and new API.  

**Old coding style**

```java
InputStream sourceStream = new FileInputStream("C:\\sample.docx");

try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(sourceStream);
    // Obtain HTML document content
    String htmlContent = htmlDoc.getContent();
    // Edit html in WYSIWYG-editor...
    // ...
                
    // Save edited html to original document format
    OutputHtmlDocument editedHtmlDoc = OutputHtmlDocument.fromMarkup(htmlContent, CommonUtilities.sourcePath + CommonUtilities.sourceResourcesFolder);
    
    WordsSaveOptions saveOptions = new WordsSaveOptions();
    saveOptions.setOutputFormat(WordFormats.Rtf);
    
    OutputStream outputStream = new FileOutputStream("C:\\output\\edited.docx");
    
    EditorHandler.toDocument(editedHtmlDoc, outputStream, saveOptions);
    
    outputStream.close();
} catch (Exception ex){
    ex.getMessage();
}

// close stream object to release file for other methods.
inputStream.close();
```

**New coding style**

```java
String documentPath = "C://sample.docx"; 
try {
    Editor editor = new Editor(documentPath);

    // Obtain editable document from original DOCX document
    EditableDocument editableDocument = editor.edit();
    String htmlContent = editableDocument.getEmbeddedHtml();
    // Pass htmlContent to WYSIWYG editor and edit there...
    // ...

    // Save edited EditableDocument object to some WordProcessing format - DOC for example
    WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(WordProcessingFormats.Docx);
    editor.save(editableDocument, "C:\\output\\edited.docx", saveOptions);

} catch (Exception ex) {
    ex.getMessage();
}
```

For more code examples and specific use cases please refer to our [Developer Guide]({{< ref "editor/java/developer-guide/_index.md" >}}) documentation or [GitHub](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java) samples and showcases.
