package com.eis.conv.mapping.core.xml;

import com.eis.conv.mapping.core.xml.xmlNodes.XmlAttribute;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XmlDOMParser {
    //http://zetcode.com/java/dom/
    public static XmlNode parseXml(String xml) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder loader = factory.newDocumentBuilder();

        InputStream intStream = new ByteArrayInputStream(xml.getBytes());
        Document document = loader.parse(intStream);
        DocumentTraversal traversal = (DocumentTraversal) document;

        TreeWalker walker = traversal.createTreeWalker(
                document.getDocumentElement(),
                NodeFilter.SHOW_ELEMENT | NodeFilter.SHOW_TEXT, null, true);

        XmlNode rootNode = new XmlNode();
        traverseLevel(walker, rootNode, "");
        return rootNode;
    }


    private static void traverseLevel(TreeWalker walker, XmlNode parentXmlNode, String indent) {

        Node node = walker.getCurrentNode();
        XmlNode currentXmlNode = new XmlNode();
        readAttributes(node,currentXmlNode);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            currentXmlNode.setName(node.getNodeName());
            parentXmlNode.addChild(currentXmlNode);
            //System.out.println(indent + node.getNodeName());
        }

        if (node.getNodeType() == Node.TEXT_NODE) {
            //have no children
            String content_trimmed = node.getTextContent().trim();
            parentXmlNode.setValue(content_trimmed);

            //if (content_trimmed.length() > 0 ) {
            //System.out.print(indent);
            //System.out.printf("'%s'%n", content_trimmed);
            //}
        }

        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            traverseLevel(walker, currentXmlNode, indent + "  ");
        }

        walker.setCurrentNode(node);
    }

    private static void readAttributes(Node node, XmlNode xmlNode) {
        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attr = (Attr) attributes.item(i);
                if (attr != null) {
                    XmlAttribute xmlAttribute = new XmlAttribute(attr.getName(), attr.getValue());
                    xmlNode.getAttributes().add(xmlAttribute);
                }
            }

        }
    }
}