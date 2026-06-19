#!/usr/bin/env bash
#
# Updates GroupDocs.Editor version across all projects, Dockerfiles, and README.
#
# Usage:
#   ./scripts/update-version.sh 26.1
#   ./scripts/update-version.sh          # prints current version

set -euo pipefail

REPO_ROOT="$(cd "$(dirname "$0")/.." && pwd)"

POMS=(
  Examples/pom.xml
  Demos/Spring/pom.xml
  Demos/Dropwizard/pom.xml
)

DOCKERFILES=(
  Demos/Spring/docker/Dockerfile-openjdk8-bullseye
  Demos/Spring/docker/Dockerfile-openjdk11-bullseye
  Demos/Spring/docker/Dockerfile-openjdk18-bullseye
  Demos/Dropwizard/docker/Dockerfile-openjdk8-bullseye
  Demos/Dropwizard/docker/Dockerfile-openjdk11-bullseye
  Demos/Dropwizard/docker/Dockerfile-openjdk18-bullseye
)

extract_pom_version() {
  grep -m1 '<version>[0-9]\+\.[0-9]\+</version>' "$1" | sed 's/.*<version>\([0-9]\+\.[0-9]\+\)<\/version>.*/\1/'
}

extract_groupdocs_editor_version() {
  grep -A1 'groupdocs-editor' "$1" | grep '<version>' | sed 's/.*<version>\([0-9]\+\.[0-9]\+\)<\/version>.*/\1/' | head -1
}

extract_dockerfile_version() {
  grep 'LABEL version="' "$1" | sed 's/.*LABEL version="\([0-9]\+\.[0-9]\+\)".*/\1/'
}

print_current_version() {
  echo "Current versions:"
  echo ""
  echo "  groupdocs-editor dependency:"
  for pom in "${POMS[@]}"; do
    ver=$(extract_groupdocs_editor_version "${REPO_ROOT}/${pom}" || echo "?")
    printf "    %-55s %s\n" "${pom}" "${ver}"
  done
  echo ""
  echo "  Dockerfiles:"
  for df in "${DOCKERFILES[@]}"; do
    ver=$(extract_dockerfile_version "${REPO_ROOT}/${df}" || echo "?")
    printf "    %-55s %s\n" "${df}" "${ver}"
  done
  echo ""
  echo "  README.md:"
  grep -n '| [0-9]\+\.[0-9]\+ |$' "${REPO_ROOT}/README.md" | head -1 | sed 's/^/    /' || true
}

update_version() {
  local VERSION="$1"

  echo "Updating all versions to ${VERSION}..."
  echo ""

  for pom in "${POMS[@]}"; do
    sed -i "s/<artifactId>groupdocs-editor<\/artifactId>\\s*\\n\\s*<version>[0-9]\\+\\.[0-9]\\+<\\/version>/<artifactId>groupdocs-editor<\/artifactId>\\n            <version>${VERSION}<\/version>/" "${REPO_ROOT}/${pom}" 2>/dev/null || \
    sed -i "/groupdocs-editor/{n;s/<version>[0-9]\\+\\.[0-9]\\+<\\/version>/<version>${VERSION}<\\/version>/;}" "${REPO_ROOT}/${pom}"
    echo "  ${pom}"
  done

  for df in "${DOCKERFILES[@]}"; do
    sed -i "s/LABEL version=\"[0-9]\+\.[0-9]\+\"/LABEL version=\"${VERSION}\"/" "${REPO_ROOT}/${df}"
    echo "  ${df}"
  done

  sed -i "s/| [0-9]\+\.[0-9]\+ |$/| ${VERSION} |/" "${REPO_ROOT}/README.md"
  sed -i "s/<version>[0-9]\+\.[0-9]\+<\/version>/<version>${VERSION}<\/version>/" "${REPO_ROOT}/README.md"
  echo "  README.md"

  echo ""
  echo "Done. Verify with: $0"
}

if [[ $# -eq 0 ]]; then
  print_current_version
elif [[ $# -eq 1 ]]; then
  if ! [[ "$1" =~ ^[0-9]+\.[0-9]+$ ]]; then
    echo "Error: version must match pattern XX.YY (e.g. 26.1)" >&2
    exit 1
  fi
  update_version "$1"
else
  echo "Usage: $0 [version]" >&2
  echo "  No arguments  — print current versions" >&2
  echo "  version        — update all versions (e.g. $0 26.1)" >&2
  exit 1
fi
