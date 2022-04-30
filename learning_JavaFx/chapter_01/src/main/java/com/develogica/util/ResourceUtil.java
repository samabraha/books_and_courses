package com.develogica.util;

import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

public class ResourceUtil {
    private final static String RSRC_PATH_FROM_CURRRENT_DIR = "src/main";

    public static URL getResourceURL(String inResourcesPath) {
        var fStr = (RSRC_PATH_FROM_CURRRENT_DIR + "/resources/" +
                inResourcesPath).replace("/", File.separator);

        try {
            return new File(fStr).getCanonicalFile().toURI().toURL();
        } catch (IOException exception) {
            System.err.printf("Cannot fetch URL for '%s'", inResourcesPath);
            System.err.println("""
                    If the path is correct, try to adapt the RSRC_PATH_FROM_CURRENT_DIR
                    constant in class ResourceUtil""");
            return null;
        }
    }

    public static String getResourceURLString(String inResourcesPath) {
        return getResourceURL(inResourcesPath).toString();
    }
    public static String getResourcePath(String inResourcesPath) {
        return Path.of(RSRC_PATH_FROM_CURRRENT_DIR, "resources", inResourcesPath).toAbsolutePath().toString();
    }

    public static String gerRandomColorString() {
        var r = Integer.toHexString(ThreadLocalRandom.current().nextInt(16));
        var g = Integer.toHexString(ThreadLocalRandom.current().nextInt(16));
        var b = Integer.toHexString(ThreadLocalRandom.current().nextInt(16));
        return String.format("#%s%s%s", r, g, b);
    }
}

