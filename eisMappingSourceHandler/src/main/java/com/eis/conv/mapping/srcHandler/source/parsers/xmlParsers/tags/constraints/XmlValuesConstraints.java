package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.constraints;

public enum XmlValuesConstraints {
    MAX("max"),
    REGEXP("regexp");


    private final String name;

    XmlValuesConstraints(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

