---
id: evaluation-limitations-and-licensing-of-groupdocs-editor
url: editor/java/evaluation-limitations-and-licensing-of-groupdocs-editor
title: Evaluation Limitations and Licensing of GroupDocs.Editor
weight: 5
description: ""
keywords: 
productName: GroupDocs.Editor for Java
hideChildren: False
---
{{< alert style="info" >}}You can use GroupDocs.Editor without the license. The usage and functionalities are pretty much same as the licensed one but you will face few limitations while using the non-licensed API.{{< /alert >}}

## Evaluation Limitations

### Requirements for license

1.  GroupDocs.Editor uses xml license file like any GroupDocs product.
2.  GroupDocs.Editor may work without license file in trial mode.
3.  Trial mode effects for all API.

### Trial Limits

1.  Only up to 2 first pages are processed.
2.  Trial badges are placed in the document on the top of each page.

## Licensing

The license file contains details such as the product name, number of developers it is licensed to, subscription expiry date and so on. It contains digital signature, so don't modify the file. Even inadvertent addition of an extra line break into the file will invalidate it. You need to set a license before utilizing **GroupDocs.Editor API** if you want to avoid its evaluation limitations.

### Applying License from File and Stream

Code below will explain how to apply product license using File Path and File Stream.

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

  

### Applying Metered License

You can also set Metered license as an alternative to license file. It is a new licensing mechanism that will be used along with existing licensing method. It is useful for the customers who want to be billed based on the usage of the API features. For more details, please refer to [Metered Licensing FAQ](https://purchase.groupdocs.com/faqs/licensing/metered) section.

Following is the sample code demonstrating how to set metered public and private keys.

```Java
String PublicKey = ""; // Your public license key
String PrivateKey = ""; // Your private license key
Metered metered = new Metered();
metered.setMeteredKey(PublicKey, PrivateKey);
```
