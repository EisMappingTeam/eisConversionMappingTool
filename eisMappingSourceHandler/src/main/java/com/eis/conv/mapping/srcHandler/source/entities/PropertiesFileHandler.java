package com.eis.conv.mapping.srcHandler.source.entities;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.properties.SourcePropertyFile;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.PropertiesFileParser;

import java.io.IOException;

public class PropertiesFileHandler {

    public static SourcePropertyFile loadFromFile(String fileName) throws IOException {
        SourcePropertyFile result;
        if (fileName.length() < 1) {
            return new SourcePropertyFile();
        }
        String fileContent = FileHelper.getFileAsSting(fileName);

        result = loadFromString(fileContent);
        result.setFileName(fileName);
        result.setFileExtension(FileHelper.getFileExtension(fileName));
        return result;
    }

    public static SourcePropertyFile loadFromString(String fileContent) throws IOException {
        PropertiesFileParser pfp = new PropertiesFileParser();
        SourcePropertyFile pFile = pfp.parse(fileContent);
        return pFile;
    }
}
