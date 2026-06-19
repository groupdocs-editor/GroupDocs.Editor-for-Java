package com.groupdocs.ui.editor.service;

import com.groupdocs.editor.EditableDocument;
import com.groupdocs.editor.Editor;
import com.groupdocs.editor.formats.*;
import com.groupdocs.editor.formats.abstraction.IDocumentFormat;
import com.groupdocs.editor.license.License;
import com.groupdocs.editor.options.*;
import com.groupdocs.ui.config.GlobalConfiguration;
import com.groupdocs.ui.util.PathSecurityUtils;
import com.groupdocs.ui.util.Utils;
import com.groupdocs.ui.editor.model.EditDocumentRequest;
import com.groupdocs.ui.editor.model.EditorConfiguration;
import com.groupdocs.ui.exception.TotalGroupDocsException;
import com.groupdocs.ui.model.request.LoadDocumentRequest;
import com.groupdocs.ui.model.response.FileDescriptionEntity;
import com.groupdocs.ui.model.response.LoadDocumentEntity;
import com.groupdocs.ui.model.response.PageDescriptionEntity;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class EditorServiceImpl implements EditorService {
    private static final Logger logger = LoggerFactory.getLogger(EditorServiceImpl.class);

    public static class Format {
        IDocumentFormat format;
        String type;

        public Format(IDocumentFormat format, String type) {
            this.format = format;
            this.type = type;
        }
    }

    public static final String WORD = "Word";
    public static final String CELL = "Cell";
    public static final String TXT = "Txt";
    public static final String PPT = "Presentation";

    /*
     * PDF support is disabled until groupdocs-editor for Java ships PdfLoadOptions / PdfEditOptions
     * (available in .NET as com.groupdocs.editor.options.PdfLoadOptions, PdfEditOptions, PdfSaveOptions).
     *
     * .NET reference (GroupDocs.Editor-for-.NET WorkingWithPdf.cs):
     *   PdfLoadOptions loadOptions = new PdfLoadOptions();
     *   loadOptions.setPassword(password);
     *   Editor editor = new Editor(inputStream, loadOptions);
     *   PdfEditOptions editOptions = new PdfEditOptions();
     *   editOptions.setEnablePagination(true);
     *   EditableDocument document = editor.edit(editOptions);
     *   PdfSaveOptions saveOptions = new PdfSaveOptions();
     *   editor.save(document, outputStream, saveOptions);
     *
     * When PdfEditOptions is available, uncomment:
     *   public static final String PDF = "Pdf";
     *   formats.put("pdf", new Format(FixedLayoutFormats.Pdf, PDF));
     */
    // public static final String PDF = "Pdf";

    public static final Map<String, Format> formats = new HashMap<>();

    static {
        formats.put("doc", new Format(WordProcessingFormats.Doc, WORD));
        formats.put("dot", new Format(WordProcessingFormats.Dot, WORD));
        formats.put("docx", new Format(WordProcessingFormats.Docx, WORD));
        formats.put("docm", new Format(WordProcessingFormats.Docm, WORD));
        formats.put("dotx", new Format(WordProcessingFormats.Dotx, WORD));
        formats.put("dotm", new Format(WordProcessingFormats.Dotm, WORD));
        formats.put("flatOpc", new Format(WordProcessingFormats.FlatOpc, WORD));
        formats.put("rtf", new Format(WordProcessingFormats.Rtf, WORD));
        formats.put("odt", new Format(WordProcessingFormats.Odt, WORD));
        formats.put("ott", new Format(WordProcessingFormats.Ott, WORD));
        formats.put("txt", new Format(TextualFormats.Txt, TXT));
        formats.put("html", new Format(TextualFormats.Html, TXT));
        formats.put("xml", new Format(TextualFormats.Xml, TXT));
        formats.put("mhtml", new Format(TextualFormats.Mhtml, TXT));
        formats.put("wordML", new Format(WordProcessingFormats.WordML, WORD));
        formats.put("csv", new Format(SpreadsheetFormats.Csv, CELL));
        formats.put("ods", new Format(SpreadsheetFormats.Ods, CELL));
        formats.put("cellML", new Format(SpreadsheetFormats.SpreadsheetML, CELL));
        formats.put("xls", new Format(SpreadsheetFormats.Xls, CELL));
        formats.put("xlsb", new Format(SpreadsheetFormats.Xlsb, CELL));
        formats.put("xlsm", new Format(SpreadsheetFormats.Xlsm, CELL));
        formats.put("xlsx", new Format(SpreadsheetFormats.Xlsx, CELL));
        formats.put("xltm", new Format(SpreadsheetFormats.Xltm, CELL));
        formats.put("xltx", new Format(SpreadsheetFormats.Xltx, CELL));
        formats.put("ppt95", new Format(PresentationFormats.Ppt95, PPT));
        formats.put("ppt", new Format(PresentationFormats.Ppt, PPT));
        formats.put("pptx", new Format(PresentationFormats.Pptx, PPT));
        formats.put("pptm", new Format(PresentationFormats.Pptm, PPT));
        formats.put("pps", new Format(PresentationFormats.Pps, PPT));
        formats.put("ppsx", new Format(PresentationFormats.Ppsx, PPT));
        formats.put("ppsm", new Format(PresentationFormats.Ppsm, PPT));
        formats.put("pot", new Format(PresentationFormats.Pot, PPT));
        formats.put("potx", new Format(PresentationFormats.Potx, PPT));
        formats.put("potm", new Format(PresentationFormats.Potm, PPT));
        formats.put("odp", new Format(PresentationFormats.Odp, PPT));
        formats.put("otp", new Format(PresentationFormats.Otp, PPT));
        // formats.put("pdf", new Format(FixedLayoutFormats.Pdf, PDF)); // disabled: no PdfEditOptions in Java API yet
    }

    static String unsupportedFormatMessage(String extension) {
        String ext = StringUtils.isEmpty(extension) ? "(none)" : extension.toLowerCase();
        return String.format("File format '%s' is not supported", ext);
    }

    private static boolean isSupportedExtension(String extension) {
        return !StringUtils.isEmpty(extension) && formats.containsKey(extension.toLowerCase());
    }

    private void requireSupportedFormat(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        if (!isSupportedExtension(extension)) {
            throw new TotalGroupDocsException(unsupportedFormatMessage(extension));
        }
    }

    @Override
    public void validateSupportedFormat(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            throw new TotalGroupDocsException("File name is required");
        }
        requireSupportedFormat(fileName);
    }

    @Autowired
    private GlobalConfiguration globalConfiguration;
    @Autowired
    private EditorConfiguration editorConfiguration;

    /**
     * Initializing fields after creating configuration objects
     */
    @PostConstruct
    public void init() {
        setLicense();
    }

    private void setLicense() {
        try {
            // set GroupDocs license
            License license = new License();
            String path = globalConfiguration.getApplication().getLicensePath();
            license.setLicense(path);
        } catch (Throwable throwable) {
            logger.error("Can not verify Editor license!");
        }
    }

    @Override
    public List<FileDescriptionEntity> getFileList(String path) {
        final String filesDirectory = editorConfiguration.getFilesDirectory();
        final Path filesSubDirectoryPath = PathSecurityUtils.resolveInsideBaseDirectoryOrRoot(filesDirectory, path);
        final File filesSubDirectory = filesSubDirectoryPath.toFile();

        List<FileDescriptionEntity> fileList = new ArrayList<>();
        try {
            if (!filesSubDirectory.isDirectory()) {
                throw new TotalGroupDocsException(PathSecurityUtils.ACCESS_DENIED);
            }
            final File[] files = filesSubDirectory.listFiles();
            if (files == null) {
                throw new TotalGroupDocsException("Can't list files");
            }

            for (File file : files) {
                if (!file.getName().startsWith(".") && !file.isHidden()) {
                    String guid = file.getCanonicalFile().getAbsolutePath();
                    String extension = FilenameUtils.getExtension(guid);
                    if (file.isDirectory() || isSupportedExtension(extension)) {
                        FileDescriptionEntity fileDescription = new FileDescriptionEntity();
                        fileDescription.setGuid(Utils.normalizePathToGuid(filesDirectory, guid));
                        fileDescription.setName(file.getName());
                        fileDescription.setDirectory(file.isDirectory());
                        fileDescription.setSize(file.length());
                        fileList.add(fileDescription);
                    }
                }
            }

            Collections.sort(fileList, new Comparator<FileDescriptionEntity>() {
                @Override
                public int compare(FileDescriptionEntity o1, FileDescriptionEntity o2) {
                    if (o1.isDirectory() && !o2.isDirectory()) {
                        return -1;
                    }
                    if (!o1.isDirectory() && o2.isDirectory()) {
                        return 1;
                    }
                    return o1.getName().compareTo(o2.getName());
                }
            });
        } catch (IOException ex) {
            logger.error("Exception in getting file list", ex);
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
        return fileList;
    }

    @Override
    public EditorConfiguration getEditorConfiguration() {
        return editorConfiguration;
    }

    @Override
    public LoadDocumentEntity loadDocument(LoadDocumentRequest loadDocumentRequest) {
        return loadDocumentEntity(loadDocumentRequest.getGuid(),loadDocumentRequest.getPassword());
    }

    private LoadDocumentEntity loadDocumentEntity(String guid, String password) {
        LoadDocumentEntity doc = new LoadDocumentEntity();
        String filesDirectory = editorConfiguration.getFilesDirectory();
        String documentPath = PathSecurityUtils.resolveInsideBaseDirectoryAsString(filesDirectory, guid);
        try {
            ILoadOptions options = getLoadOptions(documentPath, password);
            Editor editor = options != null
                    ? new Editor(new FileInputStream(documentPath), options)
                    : new Editor(documentPath);
            IEditOptions editOptions = getEditOptions(documentPath);
            EditableDocument editableDocument = editor.edit(editOptions);
            PageDescriptionEntity page = new PageDescriptionEntity();
            page.setData(editableDocument.getEmbeddedHtml());
            page.setNumber(0);
            List<PageDescriptionEntity> pages = new ArrayList<>();
            pages.add(page);
            doc.setPages(pages);
            doc.setGuid(Utils.normalizePathToGuid(filesDirectory, documentPath));
        } catch (Exception ex) {
            logger.error("Exception in loading document");
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
        return doc;
    }

    @Override
    public Set<String> getSupportedFormats() {
        Set<String> supported = new HashSet<>();
        for (String extension : formats.keySet()) {
            supported.add(extension.toUpperCase());
        }
        return supported;
    }

    @Override
    public LoadDocumentEntity saveDoc(EditDocumentRequest editDocumentRequest) {
        String filePath = PathSecurityUtils.resolveInsideBaseDirectoryAsString(
                editorConfiguration.getFilesDirectory(), editDocumentRequest.getGuid());

        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            Editor editor = new Editor(filePath);
            EditableDocument outputDocument = EditableDocument.fromMarkup(editDocumentRequest.getContent(), null);
            ISaveOptions options = getSaveOptions(filePath);
            editor.save(outputDocument, outputStream, options);
        } catch (Exception ex) {
            logger.error("Exception occurred while creating the file");
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
        return loadDocumentEntity(
                Utils.normalizePathToGuid(editorConfiguration.getFilesDirectory(), filePath),
                editDocumentRequest.getPassword());
    }

    @Override
    public InputStream downloadDocument(String documentGuid) {
        try {
            return new BufferedInputStream(Files.newInputStream(resolveDownloadDocumentPath(documentGuid)));
        } catch (IOException e) {
            logger.error("Exception in downloading document", e);
            throw new TotalGroupDocsException(e.getMessage(), e);
        }
    }

    @Override
    public long getDownloadDocumentSize(String documentGuid) {
        try {
            return Files.size(resolveDownloadDocumentPath(documentGuid));
        } catch (IOException e) {
            logger.error("Exception in downloading document", e);
            throw new TotalGroupDocsException(e.getMessage(), e);
        }
    }

    private Path resolveDownloadDocumentPath(String documentGuid) throws IOException {
        Path documentPath = PathSecurityUtils.resolveInsideBaseDirectory(
                editorConfiguration.getFilesDirectory(), documentGuid);
        if (!Files.isRegularFile(documentPath)) {
            throw new TotalGroupDocsException(PathSecurityUtils.ACCESS_DENIED);
        }
        return documentPath;
    }

    private ISaveOptions getSaveOptions(String fileName) {
        ISaveOptions options;
        requireSupportedFormat(fileName);
        String extension = FilenameUtils.getExtension(fileName);
        Format format = formats.get(extension.toLowerCase());
        switch (format.type) {
            case WORD:
                options = new WordProcessingSaveOptions((WordProcessingFormats)format.format);
                break;
            case PPT:
                options = new PresentationSaveOptions((PresentationFormats) format.format);
                break;
            case CELL:
                options = new SpreadsheetSaveOptions((SpreadsheetFormats)format.format);
                break;
            // case PDF:
            //     options = new PdfSaveOptions();
            //     break;
            default:
                throw new TotalGroupDocsException(unsupportedFormatMessage(extension));
        }

        return options;
    }

    private ILoadOptions getLoadOptions(String fileName, String password) {
        ILoadOptions options;
        requireSupportedFormat(fileName);
        String extension = FilenameUtils.getExtension(fileName);
        Format format = formats.get(extension.toLowerCase());
        switch (format.type) {
            case WORD:
                options = new WordProcessingLoadOptions();
                break;
            case CELL:
                options = new SpreadsheetLoadOptions();
                break;
            case TXT:
                options = null;
                break;
            // case PDF:
            //     options = null; // or: new PdfLoadOptions(); loadOptions.setPassword(password);
            //     break;
            case PPT:
                options = new PresentationLoadOptions();
                break;
            default:
                throw new TotalGroupDocsException(unsupportedFormatMessage(extension));
        }
        if (options != null){
            options.setPassword(password);
        }
        return options;
    }

    private IEditOptions getEditOptions(String fileName) {
        requireSupportedFormat(fileName);
        String extension = FilenameUtils.getExtension(fileName);

        Format format = formats.get(extension.toLowerCase());
        switch (format.type) {
            case WORD:
                return new WordProcessingEditOptions();
            case CELL:
                return new SpreadsheetEditOptions();
            case TXT:
                return null;
            // case PDF:
            //     PdfEditOptions editOptions = new PdfEditOptions();
            //     editOptions.setEnablePagination(true);
            //     return editOptions;
            case PPT:
                return new PresentationEditOptions();
            default:
                throw new TotalGroupDocsException(unsupportedFormatMessage(extension));
        }

    }
}