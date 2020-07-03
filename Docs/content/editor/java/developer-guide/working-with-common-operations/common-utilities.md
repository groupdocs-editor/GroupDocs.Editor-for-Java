---
id: common-utilities
url: editor/java/common-utilities
title: Common Utilities
weight: 1
description: ""
keywords: 
productName: GroupDocs.Editor for Java
hideChildren: False
---
{{< alert style="info" >}}Following are common properties, methods and Operations used in all GroupDocs.Editor API examples.{{< /alert >}}

## Common Properties

Following are some common properties used in all examples.

*   String type **storagePath** property to set input files /directory.
*   String type **resourceFolder** property to set source resources directory.
*   String type **outputPath** property to set output files /directory.
*   String type **outputResourceFolder** property to set output resources directory.
*   **Editor** Class that enable all Editor and licensing operations.

### The Code

```Java
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
```

### Apply License

**setLicense** is a method of **Editor** class to setup the **GroupDocs.Editor API License** by providing **License File**.

```Java
public static void applyLicense() throws IOException {
  // Path to license file
  License license = new License();

  // Set license
  license.setLicense(licensePath);
}

public static void applyLicenseFromStream() throws IOException {
  // Obtain license stream
  InputStream licenseStream = new FileInputStream(licensePath);
 
  // Instantiate GroupDocs.Signature handler
  License license = new License();

  // setup license
  license.setLicense(licenseStream);
}
```

### Complete Common Class

**Common Class** consists of all common methods and properties used in the this solution and examples.

```Java
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
		// Path to license file
		License license = new License();
		// Set license
		license.setLicense(licensePath);
	}

	public static void applyLicenseFromStream() throws IOException {

		// Obtain license stream
		InputStream licenseStream = new FileInputStream(licensePath);
		// Instantiate GroupDocs.Signature handler
		License license = new License();
		// setup license
		license.setLicense(licenseStream);
	}
}
```
