package com.groupdocs.examples.editor;

import java.nio.file.Path;

import static com.groupdocs.examples.editor.utils.FilesUtils.makeFilesPath;

public interface SampleFiles {
    Path SAMPLE_DOCX = makeFilesPath("sample.docx");
    Path SAMPLE_XLSX = makeFilesPath("sample.xlsx");
    Path SAMPLE_PPTX = makeFilesPath("sample.pptx");
    Path SAMPLE_HTML = makeFilesPath("SampleDoc1.html");
    Path PURE_CONTENT_HTML = makeFilesPath("PureContentSample.html");
    Path PURE_CONTENT_HTML_RESOURCES = makeFilesPath("PureContentSample_resources");
    Path SAMPLE2_DOCX = makeFilesPath("SampleDoc1.docx");
    Path LEGACY_FORM_FIELDS_DOCX = makeFilesPath("SampleLegacyFormFields.docx");
    Path SAMPLE_MD = makeFilesPath("Markdown/input.md");
    Path SAMPLE_MD_FOLDER = makeFilesPath("Markdown/images");
    Path SAMPLE_XLS_PROTECTED = makeFilesPath("sampleProtected.xlsx");
    Path SAMPLE_XML = makeFilesPath("sample.xml");
    Path SAMPLE_TXT = makeFilesPath("sample.txt");
    Path SAMPLE_CSV = makeFilesPath("sample.csv");
    Path SAMPLE_MSG = makeFilesPath("sample.msg");
    Path SAMPLE_PPTX_FORMATTING = makeFilesPath("sampleFormatting.pptx");
}
