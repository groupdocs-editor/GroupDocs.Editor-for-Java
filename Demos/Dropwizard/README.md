![Document editor](https://raw.githubusercontent.com/groupdocs-editor/groupdocs-editor.github.io/master/resources/image/banner.png "Document Editor API for Java Dropwizard")
# GroupDocs.Editor for Java Dropwizard Example
###### version 1.14.16

[![Build Status](https://travis-ci.org/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard.svg?branch=master)](https://travis-ci.org/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard)
[![Maintainability](https://api.codeclimate.com/v1/badges/a0836eb386f80c572f25/maintainability)](https://codeclimate.com/github/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard/maintainability)
[![GitHub license](https://img.shields.io/github/license/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard.svg)](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard/blob/master/LICENSE)


## System Requirements
- Java 8 (JDK 1.8)
- Maven 3


## Document Editor API for Java Dropwizard

[GroupDocs.Editor for Java](https://products.groupdocs.com/editor/java) is a library that allows you to **edit DOCX, ODT, XLS and other supported formats in form of HTML** and save it back to the original format. With GroupDocs.Editor you can easily edit documents on the web with your favorite WYSIWYG editor without external dependencies.

This web application demonstrates the powerful capabilities of GroupDocs.Editor with a built-in **WYSIWYG document editor** and can be used as a standalone application or can be easily integrated into your project.

**Note:** Without a license application will run in trial mode, purchase [GroupDocs.Editor for Java license](https://purchase.groupdocs.com/order-online-step-1-of-8.aspx) or request [GroupDocs.Editor for Java temporary license](https://purchase.groupdocs.com/temporary-license).

## Supported document Formats

| Family                      | Formats                                                                                                                            |
| --------------------------- |:---------------------------------------------------------------------------------------------------------------------------------- |
| Microsoft Word              | `DOC`, `DOCM` , `DOCX`, `DOT`, `DOTM`, `DOTX`                                                                                      |
| Microsoft Excel             | `XLS`, `XLSB`, `XLSM`, `XLSX`, `XLT`, `XLTM`, `XLTX`                                                                               |
| OpenDocument Formats        | `ODT`, `ODP`, `ODS`, `OTT`                                                                                                         |
| Plain Text File             | `TXT`                                                                                                                              |
| Comma-Separated Values      | `CSV`                                                                                                                              |
| HyperText Markup Language   | `HTML`, `MHT`, `MHTML`, `SVG`                                                                                                      |
| Extensible Markup Language  | `XML`,`XML`, `XPS`                                                                                                                 |

## Demo Video

<p align="center">
  <a title="Document editor for JAVA " href="https://youtu.be/pcqg4y87S8I"> 
    <img src="https://raw.githubusercontent.com/groupdocs-editor/groupdocs-editor.github.io/master/resources/image/editor.gif" width="100%" style="width:100%;">
  </a>
</p>

## Features
- Responsive design
- Cross-browser support (Safari, Chrome, Opera, Firefox)
- Cross-platform support (Windows, Linux, MacOS)
- Clean, modern and intuitive design
- Edit, format documents
- Mobile support (open application on any mobile device)
- Support over 50 documents and image formats including **DOCX**, **ODT**, **XLS**
- Fully customizable navigation panel
- Open password protected documents
- Download documents
- Upload documents
- Print document


## How to run

You can run this sample by one of following methods

#### Build from source

Download [source code](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard/archive/master.zip) from github or clone this repository.

```bash
git clone https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard
cd GroupDocs.Editor-for-Java-Dropwizard
mvn clean compile exec:java
## Open http://localhost:8080/editor/ in your favorite browser.
```

#### Binary release (with all dependencies)

Download [latest release](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard/releases/latest) from [releases page](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard/releases). 

**Note**: This method is **recommended** for running this sample behind firewall.

```bash
curl -J -L -o release.tar.gz https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java-Dropwizard/releases/download/1.14.16/release.tar.gz
tar -xvzf release.tar.gz
cd release
java -jar editor-1.0.7.jar configuration.yaml
## Open http://localhost:8080/editor/ in your favorite browser.
```

#### Docker image
Use [docker](https://www.docker.com/) image.

```bash
mkdir DocumentSamples
mkdir Licenses
docker run -p 8080:8080 --env application.hostAddress=localhost -v `pwd`/DocumentSamples:/home/groupdocs/app/DocumentSamples -v `pwd`/Licenses:/home/groupdocs/app/Licenses groupdocs/editor
## Open http://localhost:8080/editor/ in your favorite browser.
```
### Configuration
For all methods above you can adjust settings in `configuration.yml`. By default in this sample will lookup for license file in `./Licenses` folder, so you can simply put your license file in that folder or specify relative/absolute path by setting `licensePath` value in `configuration.yml`. 

#### Editor configuration options

| Option                 | Type    |   Default value   | Description                                                                                                                                  |
| ---------------------- | ------- |:-----------------:|:-------------------------------------------------------------------------------------------------------------------------------------------- |
| **`filesDirectory`**   | String  | `DocumentSamples` | Files directory path. Indicates where uploaded and predefined files are stored. It can be absolute or relative path                          |
| **`fontsDirectory`**   | String  |                   | Path to custom fonts directory.                                                                                                              |
| **`defaultDocument`**  | String  |                   | Absolute path to default document that will be loaded automatically.                                                                          |
| **`createNewFile`**    | String  |                   | Enable / disable new document creation.                                                                                                      |


## License
The MIT License (MIT). 

Please have a look at the LICENSE.md for more details

## GroupDocs Document Editor on other platforms/frameworks

- JAVA Sprint Boot [Document Editor](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java-Spring) 
- .NET MVC [Document editor](https://github.com/groupdocs-editor/GroupDocs.Editor-for-.NET-MVC)
- .NET WebForms [Document editor](https://github.com/groupdocs-editor/GroupDocs.Editor-for-.NET-WebForms)

[Home](https://www.groupdocs.com/) | [Product Page](https://products.groupdocs.com/editor/java) | [Documentation](https://docs.groupdocs.com/editor/java/) | [Demos](https://products.groupdocs.app/editor/family) | [API Reference](https://apireference.groupdocs.com/editor/java) | [Examples](https://github.com/groupdocs-editor/GroupDocs.editor-for-Java/tree/master/Examples) | [Blog](https://blog.groupdocs.com/category/editor/) | [Free Support](https://forum.groupdocs.com/c/editor) | [Temporary License](https://purchase.groupdocs.com/temporary-license)
