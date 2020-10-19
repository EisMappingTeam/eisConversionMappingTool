package com.eis.conv.mapping.srcHandler.source.entities;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.xml.SourceXmlConstraintFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.xml.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.XmlFileParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class XmlFileHandler {


    public static SourceXmlFile  loadFromFile(String fileName) throws IOException, ParserConfigurationException, SAXException {
        if (fileName.length() < 1) {
            return new SourceXmlFile();
        }
        String fileContent = FileHelper.getFileAsSting(fileName);

        SourceXmlFile result = loadFromString(fileContent);
        result.setFileName(fileName);
        result.setFileExtension(FileHelper.getFileExtension(fileName));
        return result;
    }

    public static SourceXmlFile  loadFromString(String fileContent) throws IOException, ParserConfigurationException, SAXException {
        SourceXmlFile  xmlFileData = XmlFileParser.parse(fileContent);
        return xmlFileData;
    }



}
