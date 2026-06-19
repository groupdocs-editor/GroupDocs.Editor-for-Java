package com.groupdocs.editor.examples.advancedusage;

/**
 * PDF example is disabled until groupdocs-editor for Java ships PdfLoadOptions / PdfEditOptions.
 *
 * .NET reference (GroupDocs.Editor-for-.NET WorkingWithPdf.cs):
 *
 * <pre>{@code
 * String inputFilePath = Constants.SAMPLE_PDF;
 * try (InputStream fs = new FileInputStream(inputFilePath)) {
 *     PdfLoadOptions loadOptions = new PdfLoadOptions();
 *     Editor editor = new Editor(fs, loadOptions);
 *     editor.getDocumentInfo(null);
 *
 *     PdfEditOptions editOptions = new PdfEditOptions();
 *     editOptions.setEnablePagination(true);
 *     EditableDocument beforeEdit = editor.edit(editOptions);
 *
 *     String originalContent = beforeEdit.getContent();
 *     List<IHtmlResource> allResources = beforeEdit.getAllResources();
 *     String editedContent = originalContent.replace("document", "edited document");
 *     EditableDocument afterEdit = EditableDocument.fromMarkup(editedContent, allResources);
 *
 *     PdfSaveOptions saveOptions = new PdfSaveOptions();
 *     saveOptions.setPassword("password");
 *     saveOptions.setOptimizeMemoryUsage(true);
 *     try (OutputStream outputStream = new FileOutputStream(outputPath)) {
 *         editor.save(afterEdit, outputStream, saveOptions);
 *     }
 *
 *     beforeEdit.dispose();
 *     afterEdit.dispose();
 *     editor.dispose();
 * }
 * }</pre>
 */
public class WorkingWithPdf {

    public static void run() throws Exception {
        throw new UnsupportedOperationException(
                "PDF editing is not available in groupdocs-editor for Java until PdfEditOptions is shipped. "
                        + "Uncomment the .NET-style implementation above when the API becomes available.");
    }
}
