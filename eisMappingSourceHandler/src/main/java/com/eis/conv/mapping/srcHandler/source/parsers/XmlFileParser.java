package com.eis.conv.mapping.srcHandler.source.parsers;

import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.eis.conv.mapping.core.xml.XmlDOMParser;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.files.xml.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.files.types.ContentTypeXML;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.XmlFileParserBeanValidation;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.XmlFileParserConstraintValidation;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.beans.XmlNodesBeans;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.constraints.XmlNodesConstraints;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class XmlFileParser {


    public static SourceXmlFile parse(String fileContent) throws IOException, SAXException, ParserConfigurationException {
        XmlNode root = parseDOM(fileContent);
        ContentTypeXML contentType = getContentType(root);

        if (contentType == ContentTypeXML.CONSTRAINT_VALIDATION_RULES) {
            return XmlFileParserConstraintValidation.parse(root);

        } else if (contentType == ContentTypeXML.BEAN_VALIDATION_RULES) {
            return XmlFileParserBeanValidation.parse(root);

        } else {
            //do nothing
        }

        return new SourceXmlFile();
    }


    private static XmlNode parseDOM(String fileContent) throws ParserConfigurationException, SAXException, IOException {
        XmlDOMParser mp = new XmlDOMParser();
        //Encode to UTF-8
        XmlNode result = mp.parseXml(StringHelper.encodingToUTF8(fileContent));
        return result;
    }


    private static ContentTypeXML getContentType(XmlNode root) {
        if (isConstraintValidations(root)) {
            return ContentTypeXML.CONSTRAINT_VALIDATION_RULES;
        }
        if (isBeanValidations(root)) {
            return ContentTypeXML.BEAN_VALIDATION_RULES;
        }
        return ContentTypeXML.UNKNOWN;
    }

    private static boolean isConstraintValidations(XmlNode root) {
        return isPresentIn2Level(root, XmlNodesConstraints.CONSTRAINT_MAPPINGS.getName(), XmlNodesConstraints.BEAN.getName());
    }

    private static boolean isBeanValidations(XmlNode root) {
        return isPresentIn2Level(root, XmlNodesBeans.BEANS.getName(), XmlNodesBeans.BEAN.getName());
    }

    private static boolean isPresentIn2Level(XmlNode root, String nodeNameLevel1, String nodeNameLevel2) {
        XmlNode node1 = root.getChildByName(nodeNameLevel1);
        if (!node1.getName().equalsIgnoreCase(nodeNameLevel1)) {
            return false;
        }
        XmlNode node2 = node1.getChildByName(nodeNameLevel2);
        if (!node2.getName().equalsIgnoreCase(nodeNameLevel2)) {
            return false;
        }
        return true;
    }

}