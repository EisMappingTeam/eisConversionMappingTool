package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.beans;


public enum XmlNodeBeanAttributes {
    ID("id"),
    PARENT("parent"),
    NAME("name"),
    MERGE("merge"),
    ABSTRACT("abstract"),
    VALUE("value"),
    BEAN("bean");

    private final String name;

    XmlNodeBeanAttributes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}




