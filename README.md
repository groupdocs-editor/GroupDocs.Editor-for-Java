# Edit Documents as HTML via Java API

GroupDocs.Editor is a one-stop solution for [Document Editing as HTML](https://products.groupdocs.com/editor/java). The edited documents can be saved in original format as well as other formats including Microsoft Word documents (DOC, DOCX), Excel spreadsheets (XLS, XSLSX), PDF & TXT.

<p align="center">
  <a title="Download complete GroupDocs.Editor for Java source code" href="https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/archive/master.zip"> 
    <img src="https://camo.githubusercontent.com/11839cd752a2d367f3149c7bee1742b68e4a4d37/68747470733a2f2f7261772e6769746875622e636f6d2f4173706f73654578616d706c65732f6a6176612d6578616d706c65732d64617368626f6172642f6d61737465722f696d616765732f646f776e6c6f61645a69702d427574746f6e2d4c617267652e706e67" data-canonical-src="https://raw.github.com/AsposeExamples/java-examples-dashboard/master/images/downloadZip-Button-Large.png" style="max-width:100%;">
  </a>
</p>

Directory | Description
--------- | -----------
[Docs](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Docs)  | Product documentation containing the Developer's Guide, Release Notes and more.
[Examples](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Examples)  | Java examples and sample documents for you to get started quickly. 

## Document Editing as HTML

- Convert documents to HTML DOM.
- Convert HTML DOM to [Office & OpenOffice formats](https://docs.groupdocs.com/editor/java/supported-document-formats/).
- Protect resultant documents. 
- Reply comments and statuses.
- Optimize memory usage.
- Paginal mode for the Words document.
- Ability to open, view and edit XML document.

## Get Started with GroupDocs.Editor for Java

GroupDocs.Editor for Java requires J2SE J2SE 6.0 (1.6), 7.0 (1.7), J2SE 8.0 (1.8) or above. Please install Java first if you do not have it already. 

GroupDocs hosts all Java APIs on [GroupDocs Artifact Repository](https://artifact.groupdocs.com/webapp/#/artifacts/browse/tree/General/repo/com/groupdocs/groupdocs-editor), so simply [configure](https://docs.groupdocs.com/editor/java/installation/) your Maven project to fetch the dependencies automatically.

## Convert Document to HTML DOM

```java
InputStream inputStream = new FileInputStream(CommonUtilities.getStoragePath(fileName));
try {
    InputHtmlDocument htmlDoc = EditorHandler.toHtml(inputStream);
    String bodyContent = htmlDoc.getContent();
    System.out.println(bodyContent);
} catch (Exception ex){
     ex.getMessage();
}
```

[Home](https://www.groupdocs.com/) | [Product Page](https://products.groupdocs.com/editor/java) | [Documentation](https://docs.groupdocs.com/editor/java/) | [Demos](https://products.groupdocs.app/editor/family) | [API Reference](https://apireference.groupdocs.com/java/editor) | [Examples](https://github.com/groupdocs-editor/GroupDocs.editor-for-Java/tree/master/Examples) | [Blog](https://blog.groupdocs.com/category/editor/) | [Free Support](https://forum.groupdocs.com/c/editor) | [Temporary License](https://purchase.groupdocs.com/temporary-license)
