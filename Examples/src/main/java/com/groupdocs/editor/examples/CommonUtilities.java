package com.groupdocs.editor.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.groupdocs.editor.license.License;


public class CommonUtilities {
	// Storage path
	public static final String storagePath = (System.getProperty("user.dir") + "\\Data\\Storage\\");
	// Output path
	public static final String outputPath = (System.getProperty("user.dir") + "\\Data\\Output\\");
	//Resource Folder
	public static final String resourceFolder = "Resources\\";
	//Output Resource Folder
	public static final String outputResourceFolder = "output_resources\\";
	// License path
	public static final String licensePath = "E:\\GroupDocs.Total.Java.lic";
	
	

	public static String getStoragePath(String fileName) {
		return storagePath + fileName;
	}
	
	public static String getStoragePathWithResource(String fileName) {
		return storagePath + resourceFolder + fileName;
	}
	
	public static String getOutputPath(String fileName) {
		return outputPath + fileName;
	}
	
	public static String getOutputPathWithResource(String fileName) {
		return outputPath + outputResourceFolder + fileName;
	}
	
	public static void applyLicense() throws IOException {
		// ExStart:applyLicense		
		// Path to license file
		License license = new License();
		// Set license
		license.setLicense(licensePath);
		// ExEnd:applyLicense
	}

	public static void applyLicenseFromStream() throws IOException {
		// ExStart:applyLicenseFromStream
		// Obtain license stream
		InputStream licenseStream = new FileInputStream(licensePath);
		// Instantiate GroupDocs.Signature handler
		License license = new License();
		// setup license
		license.setLicense(licenseStream);
		// ExEnd:applyLicenseFromStream
	}
}
