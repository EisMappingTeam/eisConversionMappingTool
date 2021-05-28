package com.eis.conv.mapping.srcHandler.processing.compareXml;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.core.xml.XmlDOMParser;
import com.eis.conv.mapping.core.xml.XmlHandler;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ProductXmlReader {

    public static List<XmlNode> readXmlToList(String fileName) {
        List<XmlNode> result = new ArrayList<>();
        String fileContent = getFileContent(fileName);
        XmlNode root = getRootNode(fileContent);
        return XmlHandler.xmlTransformToList(root);
    }

    private static String getFileContent(String fileName) {
        try {
            return FileHelper.getFileAsSting(fileName);
        } catch (IOException e) {
            return "";
        }
    }

    private static XmlNode getRootNode(String fileContent) {
        try {
            XmlNode node = XmlDOMParser.parseXml(fileContent);
            return node;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            return new XmlNode();
        }
    }
}
