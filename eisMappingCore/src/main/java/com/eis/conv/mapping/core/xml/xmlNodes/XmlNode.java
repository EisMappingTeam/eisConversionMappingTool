package com.eis.conv.mapping.core.xml.xmlNodes;

import com.eis.conv.mapping.core.stringsSupport.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class XmlNode {
    private final String PATH_DELIMITER = "/";

    private XmlNode parent = null;
    private String name = "";
    private String value = "";
    private List<XmlAttribute> attributes = new ArrayList<>();
    private List<XmlNode> children = new ArrayList<>();

    public XmlNode() {
    }

    public XmlNode(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public XmlNode(String name, String value, XmlNode parent) {
        this.parent = parent;
        this.name = name;
        this.value = value;
    }

    public XmlNode getParent() {
        return parent;
    }

    public void setParent(XmlNode parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<XmlAttribute> getAttributes() {
        return attributes;
    }


    public List<XmlNode> getChildren() {
        return children;
    }


    public void addChild(XmlNode child) {
        child.setParent(this);
        children.add(child);
    }


    public String getPath() {
        String path = this.name;
        if (parent != null) {
            path = StringHelper.joinWithDelimiter(PATH_DELIMITER, parent.getPath(), path);
        }
        return path;
    }

    public String getPathDelimiter() {
        return PATH_DELIMITER;
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
