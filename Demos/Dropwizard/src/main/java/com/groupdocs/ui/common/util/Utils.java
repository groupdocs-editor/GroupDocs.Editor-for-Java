package com.groupdocs.ui.common.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Utils {

    private Utils() {
    }

    public static String normalizePathToGuid(String filesDirectory, String path) {
        final Path relativePath = Paths.get(filesDirectory).relativize(Paths.get(path));
        return relativePath.toString().replace(File.separatorChar, '/');
    }

    public static String normalizeGuidToPath(String path) {
        return path.replace('/', File.separatorChar);
    }
}
