### GroupDocs Document Editor API

[Product Page](https://products.groupdocs.com/editor/java) | [Docs](https://docs.groupdocs.com/editor/java/) | [Demos](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Demos) | [API Reference](https://apireference.groupdocs.com/java/editor) | [Examples](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Examples) | [Blog](https://blog.groupdocs.com/category/editor/) | [Free Support](https://forum.groupdocs.com/c/editor) | [Temporary License](https://purchase.groupdocs.com/temporary-license)

**GroupDocs.Editor Demos** are applications that demonstrate powerful features of [GroupDocs.Editor for Java](https://products.groupdocs.com/editor/java) distributed as Docker images. Edit **DOCX, XLSX, PPT, PDF, TXT**, and other supported formats as HTML and save back to the original format. Use these images as-is or customize and integrate them into your own project.

**Note:** without a license the application runs in trial mode. [Purchase a license](https://purchase.groupdocs.com/editor/java) or request a [temporary license](https://purchase.groupdocs.com/temporary-license).

## Security Notice

These Docker images ship **sample/demo applications** — not production-ready services.

- Intended for **local development and evaluation** only
- Demo defaults enable upload, browse, and download; **no authentication** is included
- Do **not** expose port `8080` to untrusted networks without authentication, a reverse proxy, path validation, and other controls required by your organization
- For production, use the [GroupDocs.Editor library](https://docs.groupdocs.com/editor/java/) and implement your own secure document storage and API layer

Source code: [GroupDocs.Editor-for-Java](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java)

## Supported Document Formats

| Family | Formats |
| --- | --- |
| Microsoft Word | DOC, DOCM, DOCX, DOT, DOTM, DOTX |
| Microsoft Excel | XLS, XLSB, XLSM, XLSX, XLT, XLTM, XLTX |
| Microsoft PowerPoint | PPT, POT, POTM, POTX, PPS, PPSM, PPSX, PPTM, PPTX |
| OpenDocument Formats | ODT, ODP, ODS, OTT |
| Plain Text | TXT |
| Comma-Separated Values | CSV |
| HyperText Markup Language | HTML, MHT, MHTML |
| Extensible Markup Language | XML, XPS |
| Portable Document Format | PDF |

See the [full list of supported formats](https://docs.groupdocs.com/editor/java/supported-document-formats/) in the documentation.

## Document Editor Demo Features

- Clean, modern and intuitive WYSIWYG editor
- Responsive design and mobile support
- Edit documents as HTML and save back to original format
- Download, upload, and print documents
- File browser for sample documents
- Cross-browser and cross-platform support

## Available Docker Images

Six images are published for each GroupDocs.Editor for Java release: three Spring and three Dropwizard variants.

Tag format: `{version}-java-{jdk}-bullseye-{framework}`

Example: `{{VERSION}}-java-openjdk8-bullseye-dropwizard` — GroupDocs.Editor for Java **{{VERSION}}**, Eclipse Temurin 8, Debian Bullseye, Dropwizard.

| Tag | JDK | Framework |
| --- | --- | --- |
| `{{VERSION}}-java-openjdk8-bullseye-dropwizard` | Eclipse Temurin 8 | Dropwizard |
| `{{VERSION}}-java-openjdk11-bullseye-dropwizard` | Eclipse Temurin 11 | Dropwizard |
| `{{VERSION}}-java-openjdk18-bullseye-dropwizard` | Eclipse Temurin 21 | Dropwizard |
| `{{VERSION}}-java-openjdk8-bullseye-spring` | Eclipse Temurin 8 | Spring |
| `{{VERSION}}-java-openjdk11-bullseye-spring` | Eclipse Temurin 11 | Spring |
| `{{VERSION}}-java-openjdk18-bullseye-spring` | Eclipse Temurin 21 | Spring |

The `latest` tag points to the `{{VERSION}}-java-openjdk18-bullseye-spring` variant when published with the **latest** workflow option.

## How to Run

Pull and run a Dropwizard image:

```shell
docker run -p 8080:8080 --name editor --rm groupdocs/editor:{{VERSION}}-java-openjdk8-bullseye-dropwizard
# Open http://localhost:8080/editor/ in your browser.
```

Bind volumes for sample files and license:

```shell
docker run -p 8080:8080 --name editor --rm \
  -v "C:/DocumentSamples/:/home/groupdocs/app/DocumentSamples" \
  -v "C:/Licenses/:/home/groupdocs/app/Licenses" \
  groupdocs/editor:{{VERSION}}-java-openjdk8-bullseye-dropwizard
```

The sample reads license files from the mounted `Licenses` directory and lists documents from `DocumentSamples`.

## Editor Configuration Options

Environment variables:

| Variable | Description | Default |
| --- | --- | --- |
| `LIC_PATH` | Path to directory with license file | `/home/groupdocs/app/Licenses` |
| `DOWNLOAD_ON` | Enable download button in UI | `true` |
| `UPLOAD_ON` | Enable file upload | `true` |
| `PRINT_ON` | Enable print | `true` |
| `BROWSE_ON` | Enable document browse dialog | `true` |
| `RIGHTCLICK_ON` | Enable right-click context menu | `false` |
| `FILES_DIR` | Directory for uploaded files | `/home/groupdocs/app/DocumentSamples` |
| `HOST_ADDRESS` | Host name or IP for the server instance | (empty) |

## See Also

- [View documents](https://products.groupdocs.com/viewer/java) with GroupDocs.Viewer
- [Compare documents](https://products.groupdocs.com/comparison/java) with GroupDocs.Comparison
- [Sign documents](https://products.groupdocs.com/signature/java) with GroupDocs.Signature

[Product Page](https://products.groupdocs.com/editor/java) | [Docs](https://docs.groupdocs.com/editor/java/) | [Demos](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Demos) | [API Reference](https://apireference.groupdocs.com/java/editor) | [Examples](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Examples) | [Blog](https://blog.groupdocs.com/category/editor/) | [Free Support](https://forum.groupdocs.com/c/editor) | [Temporary License](https://purchase.groupdocs.com/temporary-license)
