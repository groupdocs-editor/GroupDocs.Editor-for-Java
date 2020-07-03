---
id: convert-document-to-html-dom
url: editor/java/convert-document-to-html-dom
title: Convert Document to HTML DOM
weight: 2
description: ""
keywords: 
productName: GroupDocs.Editor for Java
hideChildren: False
---
In this area code samples are placed to show how to convert document from source format to its HTML DOM.

### Get HTML document content

Following code example shows how to get HTML document content form stream

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    String bodyContent = htmlDoc.getContent();
    System.out.println(bodyContent);
} catch (Exception ex){
     ex.getMessage();
}
```

### Get HTML Contents With External Resources

Following code example shows how to get HTML Contents With External Resources form stream

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    String externalResourcePrefix = "GetResource?htmlDocumentFolderName=" + CommonUtilities.resourceFolder + "&resourceFilename=Picture 3.png&";

    // Obtain HTML document content
    String bodyContent = htmlDoc.getContent(externalResourcePrefix);
    System.out.println(bodyContent);
} catch (Exception ex){
     ex.getMessage();
}
```

### Get HTML document with embedded resources

Following code example shows how to get HTML document with embedded resources.

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
   // Obtain HTML document content
    String cssContent = htmlDoc.getEmbeddedHtml();
    System.out.println(cssContent);
} 
catch (Exception ex){
     ex.getMessage();
}
```

### Get HTML document BODY tag content

Following code example shows how to get HTML document BODY tag content.

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
	// Obtain HTML document  body content
    String bodyContent = htmlDoc.getBodyContent();
    System.out.println(bodyContent);
} catch (Exception ex){
     ex.getMessage();
}
```

### Get HTML document BODY tag content with external resource prefix

Following code example shows how to get HTML document BODY tag content with external resource prefix.

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    String externalResourcePrefix = "GetResource?htmlDocumentFolderName=" + CommonUtilities.resourceFolder + "&resourceFilename=Picture 3.png";
    // Obtain HTML document  body content
    String bodyContent = htmlDoc.getBodyContent(externalResourcePrefix);
    System.out.println(bodyContent);
} catch (Exception ex){
     ex.getMessage();
}
```

### Get HTML document external CSS content

Following code example shows how to get HTML document external CSS content.

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    // Obtain CSS  content
    String cssContent = htmlDoc.getCssContent();
    System.out.println(cssContent);
} catch (Exception ex){
     ex.getMessage();
}
```

### Get HTML document external CSS content with external resource prefix

Following code example shows how to get HTML document external CSS content with external resource prefix.

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    String externalResourcePrefix = "GetResource?htmlDocumentFolderName=" + CommonUtilities.resourceFolder + "&resourceFilename=Picture 3.png";
    // Obtain CSS document content
    String bodyContent = htmlDoc.getCssContent(externalResourcePrefix);
    System.out.println(bodyContent);
} catch (Exception ex){
     ex.getMessage();
}
```

### Save HTML document

Following code example shows how to Save HTML document.

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    // Obtain HTML document content
    htmlDoc.save(CommonUtilities.getOutputPath("output.html"));
} catch (Exception ex){
     ex.getMessage();
}
```

### Save HTML document specifying resource folder name

Following code example shows how to Save HTML document specifying resource folder name.

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    // Obtain HTML document content
    htmlDoc.save(CommonUtilities.getOutputPath("output.html"), CommonUtilities.getOutputPathWithResource(""));
} catch (Exception ex){
     ex.getMessage();
}
```

### Working with HTML resources and CSS

Following code example shows how to save HTML document and resources by specifying resource folder name.

```Java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);

    for (FontResourceBase fontResource : htmlDoc.getFontResources()){
        System.out.println(fontResource.getFilenameWithExtension());
        System.out.println(fontResource.getName());
        System.out.println(fontResource.getByteContent()); //returns a java.io.InputStream instance with actual content of the image as a byte stream
        System.out.println(fontResource.getTextContent()); //returns a java.lang.String instance with actual content of the font as a text in base-64 encoding
        String pathToResource =  CommonUtilities.getOutputPathWithResource(fontResource.getFilenameWithExtension());
        fontResource.save(pathToResource);
    }

    for(CssText css : htmlDoc.getCss()){
        System.out.println(css.getFilenameWithExtension());
        System.out.println(css.getByteContent()); //returns a java.io.InputStream instance with actual content of the stylesheet as a byte stream (UTF-8 is a default encoding)
        System.out.println(css.getName());
        System.out.println(css.getTextContent()); //returns a java.lang.String instance with actual content of the stylesheet as a simple text
        System.out.println(css.getEncoding());
        String pathToCss = CommonUtilities.getOutputPathWithResource(css.getFilenameWithExtension());
        css.save(pathToCss);
    }		 

    // saving output html file.
    htmlDoc.save(CommonUtilities.getOutputPath("output.html"),CommonUtilities.getOutputPathWithResource(""));
} catch (Exception ex){
    ex.getMessage();
}
```
