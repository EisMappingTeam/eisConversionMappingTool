package com.eis.conv.mapping.srcHandler.source.entities;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.srcHandler.source.entities.files.files.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.JavaFileParser;

import java.io.IOException;

public final class JFileHandler {


    public static SourceJavaFile loadFromFile(String fileName) throws IOException {
        SourceJavaFile result;
        if (fileName.length() < 1) {
            return new SourceJavaFile();
        }
        String fileContent = FileHelper.getFileAsSting(fileName);

        result = loadFromString(fileContent);
        result.setFileName(fileName);
        result.setClassName(FileHelper.getFileNameWithoutExtension(fileName));  //instead of code analysis. missed in 'loadFromString'
        result.setFileExtension(FileHelper.getFileExtension(fileName));
        return result;
    }

    public static SourceJavaFile loadFromString(String fileContent) {
        JavaFileParser jfp = new JavaFileParser();
        SourceJavaFile jFileAnnotation = jfp.parse(fileContent);
        return jFileAnnotation;
    }



}
