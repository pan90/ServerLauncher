package cn.paper_card;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;

public class CustomClassLoader extends URLClassLoader {

    public CustomClassLoader() throws MalformedURLException {
        super(searchJarFiles(new File(".")), getSystemClassLoader());
    }

    private static @NotNull URL[] searchJarFiles(@NotNull File file) {
        final LinkedList<URL> jars = new LinkedList<>();

        final File[] files = file.listFiles();

        if (files == null) return jars.toArray(new URL[0]);

        for (final File listFile : files) {
            if (isJarFile(listFile)) {
                final URL url;
                try {
                    url = listFile.toURI().toURL();
                } catch (MalformedURLException e) {
                    continue;
                }
                jars.add(url);
            }
        }

        return jars.toArray(new URL[0]);
    }


    private static boolean isJarFile(File file) {
        return file != null && file.exists() && file.isFile() && file.getName().endsWith(".jar");
    }
}
