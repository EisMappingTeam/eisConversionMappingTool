package com.eis.conv.mapping.srcHandler.source.sourceObjects;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.jParser.JavaFileParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class JFileHandler {

    public static List<SourceJavaFile> loadFromFileList(List<String> fileNames) throws IOException {
        List<SourceJavaFile> result = new ArrayList<>();
        for (String item : fileNames) {
            SourceJavaFile jFA = loadFromFile(item);
            result.add(jFA);
        }
        return result;
    }

    public static SourceJavaFile loadFromFile(String fileName) throws IOException {
        SourceJavaFile result;
        if (fileName.length() < 1) {
            return new SourceJavaFile();
        }
        String fileContent = FileHelper.getFileAsSting(fileName);

        result = loadFromString(fileName, fileContent);
        result.setFileName(fileName);
        result.setFileExtension(FileHelper.getFileExtension(fileName));
        return result;
    }

    public static SourceJavaFile loadFromString(String fileName, String fileContent) throws FileNotFoundException {
        JavaFileParser jfp = new JavaFileParser();
        SourceJavaFile jFileAnnotation = jfp.parse(fileContent);
        return jFileAnnotation;
    }


}
