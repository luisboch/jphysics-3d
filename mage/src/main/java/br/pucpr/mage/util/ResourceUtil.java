package br.pucpr.mage.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luis
 */
public class ResourceUtil {

    public static InputStream loadStream(String stream) {
        return ResourceUtil.class.getResourceAsStream(stream);
    }

    public static File loadFile(String file) {
        final URL url = ResourceUtil.class.getResource("/" + file);

        if (url == null) {
            throw new IllegalStateException("can't locate resource: " + file);
        }

        return new File(url.getPath());
    }

    public static File loadFile(String group, String file) {
        return loadFile(group + "/" + file);
    }

    public static Path getPath(String resource) {
        final URL url = ResourceUtil.class.getResource("/" + resource);

        if (url == null) {
            throw new IllegalStateException("can't locate resource: " + resource);
        }

        return Paths.get(url.getPath());
    }

}
