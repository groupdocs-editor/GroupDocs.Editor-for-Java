# GroupDocs.Editor for Java - Examples and Demo Projects

[GroupDocs.Editor for Java](https://products.groupdocs.com/editor/java) is a document editing API that lets you load documents, edit them as HTML, and save back to the original or other supported formats including Microsoft Word, Excel, PowerPoint, PDF, and plain text.

## Important: Demo Applications Only

The projects in the [Demos](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Demos) folder and the Docker images published as [`groupdocs/editor`](https://hub.docker.com/r/groupdocs/editor) are **sample applications** intended to demonstrate [GroupDocs.Editor for Java](https://products.groupdocs.com/editor/java) features.

They are **not** production-ready services and must **not** be exposed to the public internet without additional hardening.

Before using a demo in any shared or production-like environment:

- Run it on `localhost` or a trusted private network only
- Do not publish Docker containers directly to the internet without authentication, a reverse proxy, and network restrictions
- Treat file upload, browse, and download features as untrusted input — validate and sandbox file paths in your own integration
- Add authentication, authorization, rate limiting, and logging appropriate for your security requirements
- Keep GroupDocs.Editor and all dependencies up to date

For production integrations, use the library ([Examples](Examples), [documentation](https://docs.groupdocs.com/editor/java/)) and implement your own secure document storage and API layer instead of deploying these demos as-is.

<p align="center">
  <a title="Download complete GroupDocs.Editor for Java source code" href="https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/archive/master.zip"> 
    <img src="https://camo.githubusercontent.com/11839cd752a2d367f3149c7bee1742b68e4a4d37/68747470733a2f2f7261772e6769746875622e636f6d2f4173706f73654578616d706c65732f6a6176612d6578616d706c65732d64617368626f6172642f6d61737465722f696d616765732f646f776e6c6f61645a69702d427574746f6e2d4c617267652e706e67" data-canonical-src="https://raw.github.com/AsposeExamples/java-examples-dashboard/master/images/downloadZip-Button-Large.png" style="max-width:100%;">
  </a>
</p>

## Repository Structure

Directory | Description
--------- | -----------
[Demos](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Demos) | Demo projects for Spring and Dropwizard with WYSIWYG web UI.
[Examples](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Examples) | Java API usage examples with sample documents.

## Demos

Both web demos run on `http://localhost:8080/editor/` and provide document editing with download, upload, and print support.

| Demo | Framework | Build | GroupDocs.Editor |
|------|-----------|-------|------------------|
| [Spring](Demos/Spring) | Spring Boot 2.0 | `mvn clean spring-boot:run` | 26.1 |
| [Dropwizard](Demos/Dropwizard) | Dropwizard 1.3 | `mvn clean compile exec:java` | 26.1 |

## Docker

Pre-built Docker images are available on [Docker Hub](https://hub.docker.com/r/groupdocs/editor).

```bash
docker pull groupdocs/editor:latest
docker run -p 8080:8080 groupdocs/editor:latest
```

**Security notice:** Docker images ship with demo defaults (e.g. upload and browse enabled, no authentication). Use them for local evaluation only. Do not expose port `8080` to untrusted networks without adding authentication, path validation, and other security controls required by your organization.

Available image tags follow the pattern `{version}-java-{jdk}-bullseye-{framework}`:

| Tag | JDK | Framework |
|-----|-----|-----------|
| `{ver}-java-openjdk8-bullseye-spring` | Eclipse Temurin 8 | Spring |
| `{ver}-java-openjdk11-bullseye-spring` | Eclipse Temurin 11 | Spring |
| `{ver}-java-openjdk18-bullseye-spring` | Eclipse Temurin 21 | Spring |
| `{ver}-java-openjdk8-bullseye-dropwizard` | Eclipse Temurin 8 | Dropwizard |
| `{ver}-java-openjdk11-bullseye-dropwizard` | Eclipse Temurin 11 | Dropwizard |
| `{ver}-java-openjdk18-bullseye-dropwizard` | Eclipse Temurin 21 | Dropwizard |

The `latest` tag points to the `openjdk18-bullseye-spring` variant.

The [Docker Hub repository overview](https://hub.docker.com/r/groupdocs/editor) is generated from [`docs/docker-hub-overview.md`](docs/docker-hub-overview.md) when the [Publish Docker Images](.github/workflows/docker-publish.yml) workflow runs with **Push** enabled.

## Document Editing as HTML

- Convert documents to HTML DOM.
- Convert HTML DOM to [Office & OpenOffice formats](https://docs.groupdocs.com/editor/java/supported-document-formats/).
- Protect resultant documents.
- Paginal mode for Word documents.
- Open, view and edit XML documents.

## Getting Started

GroupDocs.Editor for Java requires J2SE 8.0 (1.8) or above.

Add the [GroupDocs repository](https://releases.groupdocs.com/java/repo/) to your Maven project:

```xml
<repository>
    <id>GroupDocs Artifact Repository</id>
    <url>https://releases.groupdocs.com/java/repo/</url>
</repository>
```

Then add the dependency:

```xml
<dependency>
    <groupId>com.groupdocs</groupId>
    <artifactId>groupdocs-editor</artifactId>
    <version>26.1</version>
</dependency>
```

See the [installation guide](https://docs.groupdocs.com/editor/java/installation/) for details.

## Code Example

```java
try (Editor editor = new Editor("sample.docx")) {
    EditableDocument doc = editor.edit();
    String html = doc.getEmbeddedHtml();
    // modify html...
    EditableDocument edited = EditableDocument.fromMarkup(html, null);
    editor.save(edited, "output.docx");
}
```

[Home](https://www.groupdocs.com/) | [Product Page](https://products.groupdocs.com/editor/java) | [Documentation](https://docs.groupdocs.com/editor/java/) | [Demos](https://products.groupdocs.app/editor/family) | [API Reference](https://apireference.groupdocs.com/editor/java) | [Examples](https://github.com/groupdocs-editor/GroupDocs.Editor-for-Java/tree/master/Examples) | [Blog](https://blog.groupdocs.com/category/editor/) | [Free Support](https://forum.groupdocs.com/c/editor) | [Temporary License](https://purchase.groupdocs.com/temporary-license)
