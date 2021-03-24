package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers;

import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.files.xml.SourceXmlBeanFile;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.beans.XmlNodesBeans;

import java.util.List;

public final class XmlFileParserBeanValidation {

    public static SourceXmlBeanFile parse(XmlNode root) {
        SourceXmlBeanFile result = new SourceXmlBeanFile();
        XmlNode beans = root.getChildren().get(0); //XML structure must be checked before call 'parse'
        result.setRoot(beans);

        return result;
    }

    //TODO
    public static XmlNode getBeanByAttribute(XmlNode rootBeans, String attributeName, String attributeValue) {
        return getChildByNameAndAttribute(rootBeans, XmlNodesBeans.BEAN.getName(), attributeName, attributeValue);
    }


    private static List<XmlNode> getAllChild(XmlNode rootBeans) {
        return rootBeans.getChildren();
    }

    private static XmlNode getChildByNameAndAttribute(XmlNode rootBeans, String nodeName, String attributeName, String attributeValue) {
        return getAllChild(rootBeans).stream().filter(item ->
                item.getName().equals(nodeName) &
                        isNodeHasAttribute(item, attributeName) &
                        item.getAttributeByName(attributeName).getValue().equals(attributeValue) &
                        attributeValue.length() > 0)
                .findFirst()
                .orElse(new XmlNode());

    }

    private static boolean isNodeHasAttribute(XmlNode node, String attributeName) {
        return node.getAttributeByName(attributeName).getName().equals(attributeName) & attributeName.length() > 0;
    }


}
