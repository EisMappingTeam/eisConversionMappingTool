package com.eis.conv.mapping.srcHandler.source.sourceObjects;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.XmlFileParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class XmlFileHandler {

    public static List<SourceXmlFile> loadFromFileList(List<String> fileNames) throws IOException {
        List<SourceXmlFile> result = new ArrayList<>();
        for (String item : fileNames) {
            SourceXmlFile xmlFA = loadFromFile(item);
            result.add(xmlFA);
        }
        return result;
    }

    public static SourceXmlFile loadFromFile(String fileName) throws IOException {
        SourceXmlFile result;
        if (fileName.length() < 1) {
            return new SourceXmlFile();
        }
        String fileContent = FileHelper.getFileAsSting(fileName);

        result = loadFromString(fileName, fileContent);
        result.setFileName(fileName);
        result.setFileExtension(FileHelper.getFileExtension(fileName));
        return result;
    }

    public static SourceXmlFile loadFromString(String fileName, String fileContent) throws FileNotFoundException {
        XmlFileParser xmlFp = new XmlFileParser();
        SourceXmlFile xmlFileData = xmlFp.parse(fileContent);
        return xmlFileData;
    }


}
