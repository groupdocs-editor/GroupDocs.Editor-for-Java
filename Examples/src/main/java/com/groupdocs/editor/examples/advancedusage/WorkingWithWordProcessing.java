/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples.advancedusage;

import java.io.*;
import java.util.List;
import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.examples.Constants;
import com.groupdocs.editor.formats.WordProcessingFormats;
import com.groupdocs.editor.htmlcss.resources.IHtmlResource;
import com.groupdocs.editor.internal.c.a.ms.System.IO.Path;
import com.groupdocs.editor.options.FontExtractionOptions;
import com.groupdocs.editor.options.WordProcessingEditOptions;
import com.groupdocs.editor.options.WordProcessingLoadOptions;
import com.groupdocs.editor.options.WordProcessingProtection;
import com.groupdocs.editor.options.WordProcessingProtectionType;
import com.groupdocs.editor.options.WordProcessingSaveOptions;

import java.util.Locale;

/**
 *
 * @author AlexT
 */
public class WorkingWithWordProcessing {

    public static void run() throws Exception {
        //1. Get a path to the input file (or stream with file content). In this case it is sample DOCX with complex content and formatting
        String inputFilePath = Constants.SAMPLE_DOCX;

        //2. Create stream from this path
        InputStream fs = new FileInputStream(inputFilePath);
        //3. Create load options for this document
        WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();

        //3.1. In case if input document is password-protected, we can specify password for its opening...
        loadOptions.setPassword("some_password_to_open_a_document");
        //3.2. ...but, because document is unprotected, this password will be ignored

        //4. Load document with options to the Editor instance
        Editor editor = new Editor(fs, loadOptions);
        //5. Create editing options
        WordProcessingEditOptions editOptions = new WordProcessingEditOptions();

        //5.1. Enable font extraction from original document to intermediate EditableDocument
        editOptions.setFontExtraction(FontExtractionOptions.ExtractEmbeddedWithoutSystem);

        //5.2. Enable extracting language information for better subsequent spell-checking on client side
        editOptions.setEnableLanguageInformation(true);

        //5.3. Switch to pagination mode instead of default float mode
        editOptions.setEnablePagination(true);

        //6. Create intermediate EditableDocument
        EditableDocument beforeEdit = editor.edit(editOptions);
        //7. Extract textual content as HTML markup with all resources
        String originalContent = beforeEdit.getContent();
        List<IHtmlResource> allResources = beforeEdit.getAllResources();

        //8. Modify content somehow on client side
        String editedContent = originalContent.replace("document", "edited document");

        //9. Create new EditableDocument instance with edited content
        EditableDocument afterEdit = EditableDocument.fromMarkup(editedContent, allResources);
        //10. Create document save options
        WordProcessingFormats docmFormat = WordProcessingFormats.Docm;
        WordProcessingSaveOptions saveOptions = new WordProcessingSaveOptions(docmFormat);

        //10.1 Encrypt output document by setting non-null and non-empty opening password
        saveOptions.setPassword("password");

        //10.2. Because pagination was previously enabled in WordProcessingEditOptions (editOptions variable),
        //for better output result it is highly recommended to enable it in WordProcessingSaveOptions (saveOptions variable)
        saveOptions.setEnablePagination(true);

        //10.3. You can set locale for output WordProcessing document manually
        saveOptions.setLocale(Locale.US);

        //10.4. If document is really huge and causes OutOfMemoryException, you can set memory optimization option
        saveOptions.setOptimizeMemoryUsage(true);

        //10.5. You can protect document from writing (make it read-only) with password
        saveOptions.setProtection(new WordProcessingProtection(WordProcessingProtectionType.ReadOnly, "write_password"));

        //11. Save it
        //11.1. Prepare saving filename and path
        String outputPath = Constants.getOutputFilePath(Constants.removeExtension(Path.getFileName(inputFilePath)), docmFormat.getExtension());

        //11.2. Prepare stream for saving
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //FileStream outputStream1 = File.create(outputPath)

        //11.3. Save
        editor.save(afterEdit, outputStream, saveOptions);

        try(OutputStream outputFile = new FileOutputStream(outputPath)) {
            outputStream.writeTo(outputFile);
        }
        System.out.println("WorkingWithWordProcessing routine has successfully finished");
    }
}
