package com.eis.conv.mapping.srcHandler.source.sourceParsers.xmlParsers.tags;

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

