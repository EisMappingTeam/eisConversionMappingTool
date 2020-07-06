package com.eis.conv.mapping.srcHandler.source.sourceObjects;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects.JFileAnnotations;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.jParser.JavaFileParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class JFileHandler {

    public static JFileAnnotations loadFromFile(String fileName) throws IOException {
        if (fileName.length() < 1) {
            return new JFileAnnotations();
        }
        String fileContent = FileHelper.getFileAsSting(fileName);
        return loadFromString(fileName, fileContent);
    }

    public static JFileAnnotations loadFromString(String fileName, String fileContent) throws FileNotFoundException {
        JavaFileParser jfp = new JavaFileParser();
        JFileAnnotations jFileAnnotation = jfp.parse(fileContent);
        jFileAnnotation.setFileName(fileName);
        return jFileAnnotation;
    }

    public static List<JFileAnnotations> loadFromFileList(List<String> fileNames) throws IOException {
        List<JFileAnnotations> result = new ArrayList<>();
        for (String item : fileNames) {
            JFileAnnotations jFA = loadFromFile(item);
            result.add(jFA);
        }
        return result;
    }

}
