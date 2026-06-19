# GroupDocs.Editor for Java — Demo Applications

These projects are **sample applications** that demonstrate [GroupDocs.Editor for Java](https://products.groupdocs.com/editor/java). They are not intended for production deployment without additional security hardening.

## Security Notice

- Intended for **local development and evaluation** only
- Demo defaults enable upload, browse, and download; **no authentication** is included
- Path validation is applied via `PathSecurityUtils` to constrain file operations to the configured `DocumentSamples` directory
- Do **not** expose port `8080` to untrusted networks without authentication, a reverse proxy, and other controls

## Available Demos

| Demo | Path | URL |
|------|------|-----|
| Spring | [Spring](Spring) | http://localhost:8080/editor/ |
| Dropwizard | [Dropwizard](Dropwizard) | http://localhost:8080/editor/ |

## Docker

See the root [README](../README.md#docker) and [`docs/docker-hub-overview.md`](../docs/docker-hub-overview.md) for Docker image tags and run instructions.
