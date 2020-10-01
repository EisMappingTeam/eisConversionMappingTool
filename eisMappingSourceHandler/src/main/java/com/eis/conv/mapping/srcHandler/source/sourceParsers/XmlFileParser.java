package com.eis.conv.mapping.srcHandler.source.sourceParsers;

import com.eis.conv.mapping.core.xml.XmlDOMParser;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.entities.files.files.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.types.SourceFileContentTypeXML;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.xmlParsers.XmlFileParserConstraintValidation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlFileParser {
    private final String NODE_CONSTRAINTMAPPINGS = "constraint-mappings"; //Note: duplicated into sub-parsers
    private final String NODE_BEAN = "bean";    //Note: duplicated into sub-parsers
    private final String NODE_BEAN_VALIDATION_CLASS = "class";  //Note: duplicated into sub-parsers


    public SourceXmlFile parse(String fileContent) throws IOException, SAXException, ParserConfigurationException {
        SourceXmlFile result = new SourceXmlFile();

        XmlNode root = parseDOM(fileContent);

        if (getContentType(root) == SourceFileContentTypeXML.CONSTRAINT_VALIDATION_RULES) {
            result = XmlFileParserConstraintValidation.parse(root);
        } else {
            //
        }
        return result;
    }


    private XmlNode parseDOM(String fileContent) throws ParserConfigurationException, SAXException, IOException {
        XmlDOMParser mp = new XmlDOMParser();
        XmlNode result = mp.parseXml(fileContent);
        return result;
    }


    private SourceFileContentTypeXML getContentType(XmlNode root) {
        XmlNode cm = root.getChildByName(NODE_CONSTRAINTMAPPINGS);
        if (!cm.getName().equalsIgnoreCase(NODE_CONSTRAINTMAPPINGS)) {
            return SourceFileContentTypeXML.UNKNOWN;
        }

        XmlNode bn = cm.getChildByName(NODE_BEAN);
        if (!bn.getName().equalsIgnoreCase(NODE_BEAN)) {
            return SourceFileContentTypeXML.UNKNOWN;
        }
        return SourceFileContentTypeXML.CONSTRAINT_VALIDATION_RULES;
    }


}