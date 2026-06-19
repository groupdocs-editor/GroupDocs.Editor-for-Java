package com.groupdocs.ui.editor.resources;

import com.groupdocs.ui.common.config.GlobalConfiguration;
import com.groupdocs.ui.common.entity.web.FileDescriptionEntity;
import com.groupdocs.ui.common.entity.web.LoadDocumentEntity;
import com.groupdocs.ui.common.entity.web.UploadedDocumentEntity;
import com.groupdocs.ui.common.entity.web.request.FileTreeRequest;
import com.groupdocs.ui.common.entity.web.request.LoadDocumentRequest;
import com.groupdocs.ui.common.exception.TotalGroupDocsException;
import com.groupdocs.ui.common.resources.Resources;
import com.groupdocs.ui.common.util.Utils;
import com.groupdocs.ui.editor.model.EditDocumentRequest;
import com.groupdocs.ui.editor.model.EditorConfigurationModel;
import com.groupdocs.ui.editor.service.EditorService;
import com.groupdocs.ui.editor.service.EditorServiceImpl;
import com.groupdocs.ui.editor.views.Editor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static javax.ws.rs.core.MediaType.*;

@Path(value = "/editor")
public class EditorResources extends Resources {

    private static final Logger logger = LoggerFactory.getLogger(EditorResources.class);

    private GlobalConfiguration globalConfiguration;

    private EditorService editorService;

    @Override
    protected String getStoragePath(Map<String, Object> params) {
        return globalConfiguration.getEditor().getFilesDirectory();
    }

    /**
     * Constructor
     * @param globalConfiguration global configuration object
     * @throws UnknownHostException
     */
    public EditorResources(GlobalConfiguration globalConfiguration) throws UnknownHostException {
        super(globalConfiguration);

        this.globalConfiguration = globalConfiguration;
        this.editorService = new EditorServiceImpl(globalConfiguration);
    }

    /**
     * Get editor page
     *
     * @return index page
     */
    @GET
    public Editor getView() {
        // initiate index page
        return new Editor(globalConfiguration, DEFAULT_CHARSET);
    }

    /**
     * Get files and directories
     *
     * @return files and directories list
     */
    @POST
    @Path(value = "/loadFileTree")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public List<FileDescriptionEntity> loadFileTree(FileTreeRequest fileTreeRequest) {
        return editorService.getFileList(fileTreeRequest.getPath());
    }

    @GET
    @Path(value = "/loadConfig")
    @Produces(APPLICATION_JSON)
    public EditorConfigurationModel loadConfig() {
        return EditorConfigurationModel.createEditorConfiguration(globalConfiguration.getEditor(), globalConfiguration.getCommon());
    }

    /**
     * Get document description
     *
     * @return document description
     */
    @POST
    @Path(value = "/loadDocumentDescription")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public LoadDocumentEntity loadDocumentDescription(LoadDocumentRequest loadDocumentRequest) {
        return editorService.loadDocument(loadDocumentRequest);
    }

    /**
     * Get supported formats
     *
     * @return
     */
    @GET
    @Path(value = "/loadFormats")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Set<String> loadFormats() {
        return editorService.getSupportedFormats();
    }

    @POST
    @Path(value = "/saveFile")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public LoadDocumentEntity saveFile(EditDocumentRequest loadDocumentEntity) {
        return editorService.saveDoc(loadDocumentEntity);
    }

    /**
     * Upload document
     *
     * @return uploaded document object (the object contains uploaded document guid)
     */
    @POST
    @Path(value = "/uploadDocument")
    @Produces(APPLICATION_JSON)
    @Consumes(MULTIPART_FORM_DATA)
    public UploadedDocumentEntity uploadDocument(@FormDataParam("file") InputStream inputStream,
                                                 @FormDataParam("file") FormDataContentDisposition fileDetail,
                                                 @FormDataParam("url") String documentUrl,
                                                 @FormDataParam("rewrite") Boolean rewrite) {
        if (StringUtils.isEmpty(documentUrl)) {
            editorService.validateSupportedFormat(fileDetail != null ? fileDetail.getFileName() : null);
        } else {
            editorService.validateSupportedFormat(FilenameUtils.getName(documentUrl));
        }
        String pathname = uploadFile(documentUrl, inputStream, fileDetail, rewrite, null);
        // create response
        UploadedDocumentEntity uploadedDocument = new UploadedDocumentEntity();
        uploadedDocument.setGuid(Utils.normalizePathToGuid(
                editorService.getEditorConfiguration().getFilesDirectory(), pathname));
        return uploadedDocument;
    }

    @GET
    @Path(value = "/downloadDocument")
    @Produces(APPLICATION_OCTET_STREAM)
    public void downloadDocument(@QueryParam("path") String documentGuid, @Context HttpServletResponse response) {
        long fileSize = editorService.getDownloadDocumentSize(documentGuid);
        String fileName = FilenameUtils.getName(documentGuid);
        try (InputStream inputStream = editorService.downloadDocument(documentGuid);
             java.io.OutputStream outputStream = response.getOutputStream()) {
            addFileDownloadHeaders(response, fileName, fileSize);
            IOUtils.copyLarge(inputStream, outputStream);
        } catch (Exception ex) {
            logger.error("Exception in downloading document", ex);
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
    }
}
