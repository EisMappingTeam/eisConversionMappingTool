package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.beans;

public enum XmlNodesBeans {
    BEANS("beans"),
    BEAN("bean"),
    CLASS("class");

    private final String name;

    XmlNodesBeans(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

