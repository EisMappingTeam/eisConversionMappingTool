package com.eis.conv.mapping.srcHandler.source.entities;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.SourceXmlConstraintFile;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.XmlFileParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class XmlFileHandler {


    public static SourceXmlConstraintFile loadFromFile(String fileName) throws IOException, ParserConfigurationException, SAXException {
        SourceXmlConstraintFile result;
        if (fileName.length() < 1) {
            return new SourceXmlConstraintFile();
        }
        String fileContent = FileHelper.getFileAsSting(fileName);

        result = loadFromString(fileContent);
        result.setFileName(fileName);
        result.setFileExtension(FileHelper.getFileExtension(fileName));
        return result;
    }

    public static SourceXmlConstraintFile loadFromString(String fileContent) throws IOException, ParserConfigurationException, SAXException {
        SourceXmlConstraintFile xmlFileData = XmlFileParser.parse(fileContent);
        return xmlFileData;
    }


}
