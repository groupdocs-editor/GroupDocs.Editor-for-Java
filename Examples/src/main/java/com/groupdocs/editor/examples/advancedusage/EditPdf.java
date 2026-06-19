package com.groupdocs.editor.examples.advancedusage;

/**
 * PDF example is disabled until groupdocs-editor for Java ships PdfLoadOptions / PdfEditOptions.
 *
 * .NET reference (GroupDocs.Editor-for-.NET EditPdf.cs):
 *
 * <pre>{@code
 * String inputFilePath = Constants.SAMPLE_PDF;
 * Editor editor = new Editor(inputFilePath);
 * editor.getDocumentInfo(null);
 *
 * PdfEditOptions editOptions = new PdfEditOptions();
 * editOptions.setEnablePagination(enablePagination);
 * EditableDocument editable = editor.edit(editOptions);
 * editable.save(outputPath);
 *
 * editable.dispose();
 * editor.dispose();
 * }</pre>
 */
public class EditPdf {

    public static void run(boolean enablePagination) throws Exception {
        throw new UnsupportedOperationException(
                "PDF editing is not available in groupdocs-editor for Java until PdfEditOptions is shipped. "
                        + "Uncomment the .NET-style implementation above when the API becomes available.");
    }
}
