/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupdocs.editor.examples;

import static org.apache.commons.io.FilenameUtils.indexOfExtension;

/**
 *
 * author AlexT
 */
public class Constants {

    public static String PROJECT_PATH = System.getProperty("user.dir");
    
    public static final String LicensePath = "C:\\GroupDocs.Editor.Java.lic";
    public static final String SamplesPath = "\\Resources\\";
    public static final String OutputPath = "\\Output\\";
    
    public static String SAMPLE_DOCX = getSampleFilePath("sample.docx");

    public static String SAMPLE_DOCX2 = getSampleFilePath("SampleDoc1.docx");

    public static String SAMPLE_HTML = getSampleFilePath("SampleDoc1.html");

    public static String SAMPLE_HTML_BODY = getSampleFilePath("PureContentSample.html");

    public static String SAMPLE_HTML_BODY_RESOURCES = getSampleFilePath("PureContentSample_resources");

    public static String SAMPLE_CSV = getSampleFilePath("CarsComma.csv");

    public static String SAMPLE_XLSX = getSampleFilePath("Sample_2SpreadSheet.xlsx");

    public static String SAMPLE_XLS_PROTECTED = getSampleFilePath("sample.xlsx");

    public static String SAMPLE_XML = getSampleFilePath("SampleXmlCorrect.xml");

    public static String SAMPLE_TXT = getSampleFilePath("SamplePlainText1.txt");

    public static String SAMPLE_PPTX = getSampleFilePath("sample.pptx");

    public static String SAMPLE_MSG = getSampleFilePath("ComplexExample.msg");

    public static String SAMPLE_MD = getSampleFilePath("Markdown\\ComplexImage.md");

    public static String SAMPLE_MD_EDIT = getSampleFilePath("Markdown\\input.md");

    public static String SAMPLE_MD_FOLDER = getSampleFilePath("Markdown\\images");

    public static String SampleLegacyFormFields_docx = getSampleFilePath("SampleLegacyFormFields.docx");

    public static String getSampleFilePath(String fileName) {
        return PROJECT_PATH + SamplesPath + fileName;
    }

    public static String getOutputDirectoryPath(String callerFilePath) {
        return PROJECT_PATH + OutputPath + callerFilePath;
    }   

    public static String getOutputFilePath(String fileName, String fileExtension) {
        return PROJECT_PATH + OutputPath + fileName + "." + fileExtension;
    }
    public static String removeExtension(final String filename) {

        final int index = indexOfExtension(filename); //used the String.lastIndexOf() method
        if (index == 0) {
            return filename;
        } else {
            return filename.substring(0, index);
        }
    }
}
