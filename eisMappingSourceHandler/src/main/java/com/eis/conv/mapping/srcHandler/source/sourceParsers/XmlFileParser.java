package com.eis.conv.mapping.srcHandler.source.sourceParsers;

import com.eis.conv.mapping.core.xml.XmlDOMParser;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.SourceXmlConstraintFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.types.ContentTypeXML;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.xmlParsers.XmlFileParserConstraintValidation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlFileParser {
    private final String NODE_CONSTRAINTMAPPINGS = "constraint-mappings"; //Note: duplicated into sub-parsers
    private final String NODE_CONSTRAINT_BEAN = "bean";    //Note: duplicated into sub-parsers

    private final String NODE_BEANS_BEANS = "beans";
    private final String NODE_BEANS_BEAN = "bean";
    private final String NODE_BEAN_VALIDATION_CLASS = "class";  //Note: duplicated into sub-parsers


    public SourceXmlConstraintFile parse(String fileContent) throws IOException, SAXException, ParserConfigurationException {
        ContentTypeXML contentType;
        SourceXmlConstraintFile result = new SourceXmlConstraintFile();

        XmlNode root = parseDOM(fileContent);
        contentType =getContentType(root);
        if (contentType == ContentTypeXML.CONSTRAINT_VALIDATION_RULES) {
            result = XmlFileParserConstraintValidation.parse(root);
        } else if (contentType == ContentTypeXML.BEAN_VALIDATION_RULES ) {
            System.out.println("BEAN-VALIDATION-RULES");
        } else {
            //do nothing
        }

        return result;
    }


    private XmlNode parseDOM(String fileContent) throws ParserConfigurationException, SAXException, IOException {
        XmlDOMParser mp = new XmlDOMParser();
        XmlNode result = mp.parseXml(fileContent);
        return result;
    }


    private ContentTypeXML getContentType(XmlNode root) {
        if (isConstraintValidations(root)) {
            return ContentTypeXML.CONSTRAINT_VALIDATION_RULES;
        }
        if (isBeanValidations(root)) {
            return ContentTypeXML.BEAN_VALIDATION_RULES;
        }
        return ContentTypeXML.UNKNOWN;
    }

    private boolean isConstraintValidations(XmlNode root) {
        return isPresentIn2Level(root, NODE_CONSTRAINTMAPPINGS, NODE_CONSTRAINT_BEAN);
    }

    private boolean isBeanValidations(XmlNode root) {
        return isPresentIn2Level(root, NODE_BEANS_BEANS, NODE_BEANS_BEAN);
    }

    private boolean isPresentIn2Level(XmlNode root, String nodeNameLevel1, String nodeNameLevel2) {
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