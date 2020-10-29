package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.constraints;

public enum XmlAttributesConstraints {

    CLASS("class"),
    NAME("name"),
    ANNOTATION("annotation");


    private final String attributeName;

    XmlAttributesConstraints(String name) {
        this.attributeName = name;
    }

    public String getName() {
        return attributeName;
    }

}

