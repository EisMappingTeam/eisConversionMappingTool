package com.eis.conv.mapping.srcHandler.source.files.handlers;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.srcHandler.source.files.properties.SourcePropertyFile;
import com.eis.conv.mapping.srcHandler.source.parsers.PropertiesFileParser;

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
        SourcePropertyFile pFile = PropertiesFileParser.parse(fileContent);
        return pFile;
    }
}
