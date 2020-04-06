package main.java.ir.loghme.model.util;

import java.io.*;
import java.net.URL;

public class FileManipulator {
    // get file from classpath, resources folder
    public File openFileFromResources(String fileName) throws IllegalArgumentException {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) throw new IllegalArgumentException("file couldn't be found!");
        else {
            return new File(resource.getFile());
        }

    }

    public String readFile(File file) throws IOException {

        if (file == null) return null;

        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        return new String(data, "UTF-8");
    }
}
