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
    
    public static final String LicensePath = "C:\\GroupDocs.Total.Java.lic";
    public static final String SamplesPath = "\\Resources\\";
    public static final String OutputPath = "\\Resources\\Output\\";
    
    public static String SAMPLE_DOCX = getSampleFilePath("SampleDoc1.docx");

    public static String SAMPLE_HTML = getSampleFilePath("SampleDoc1.html");

    public static String SAMPLE_HTML_BODY = getSampleFilePath("PureContentSample.html");

    public static String SAMPLE_HTML_BODY_RESOURCES = getSampleFilePath("PureContentSample_resources");

    public static String SAMPLE_CSV = getSampleFilePath("CarsComma.csv");

    public static String SAMPLE_XLSX = getSampleFilePath("Sample_2SpreadSheet.xlsx");

    public static String SAMPLE_XLS_PROTECTED = getSampleFilePath("Timesheet - excel_password.xls");

    public static String SAMPLE_XML = getSampleFilePath("SampleXmlCorrect.xml");

    public static String SAMPLE_TXT = getSampleFilePath("SamplePlainText1.txt");

    public static String SAMPLE_PPTX = getSampleFilePath("ComplexTest.pptx");

    private static String getSampleFilePath(String fileName) {
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
