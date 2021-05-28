package com.eis.conv.mapping.srcHandler.source.files.xml;

import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.files.xml.xmlObjects.beanValidations.XmlBeanValidation;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.beans.XmlNodesBeans;

import java.util.ArrayList;
import java.util.List;

public class SourceXmlBeanFile extends SourceXmlFile {
    private List<XmlBeanValidation> beanValidations = new ArrayList();
    private XmlNode root;


    public List<XmlBeanValidation> getBeanValidations() {
        return beanValidations;
    }

    public void setBeanValidations(List<XmlBeanValidation> beanValidations) {
        this.beanValidations = beanValidations;
    }


    public XmlNode getRoot() {
        return root;
    }

    public void setRoot(XmlNode root) {
        this.root = root;
    }

    public XmlNode getBean(String attributeName, String attributeValue) {
        return getChildByNameAndAttribute(XmlNodesBeans.BEAN.getName(), attributeName, attributeValue);
    }


    private XmlNode getChildByNameAndAttribute(String nodeName, String attributeName, String attributeValue) {
        return root.getChildren().stream().filter(item ->
                item.getName().equals(nodeName) &
                        nodeHasAttribute(item, attributeName) &
                        item.getAttributeByName(attributeName).getValue().equals(attributeValue) &
                        attributeValue.length() > 0)
                .findFirst()
                .orElse(new XmlNode());

    }

    private boolean nodeHasAttribute(XmlNode node, String attributeName) {
        return node.getAttributeByName(attributeName).getName().equals(attributeName) & attributeName.length() > 0;
    }
}
