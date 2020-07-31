package com.eis.conv.mapping.core.xml.xmlNodes;

import java.util.ArrayList;
import java.util.List;

public class XmlNode {
    private String name;
    private String date;
    private List<XmlAttribute> attributes = new ArrayList<>();
    private List<XmlNode> children = new ArrayList<>();

    public XmlNode() {
    }

    public XmlNode(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<XmlAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<XmlAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<XmlNode> getChildren() {
        return children;
    }

    public void setChildren(List<XmlNode> children) {
        this.children = children;
    }

    public XmlNode getChildByName(String nodeName) {
        XmlNode result = children.stream().filter(item -> item.getName().equalsIgnoreCase(nodeName))
                .findFirst()
                .orElse(new XmlNode());
        return result;
    }

    public XmlAttribute getAttributeByName(String attributeName) {
        XmlAttribute result = attributes.stream().filter(item -> item.getName().equalsIgnoreCase(attributeName))
                .findFirst()
                .orElse(new XmlAttribute());
        return result;
    }
}
