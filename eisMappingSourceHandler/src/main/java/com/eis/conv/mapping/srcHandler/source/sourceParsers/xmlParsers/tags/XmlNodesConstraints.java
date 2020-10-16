package com.eis.conv.mapping.srcHandler.source.sourceParsers.xmlParsers.tags;

public enum XmlNodesConstraints {

    CONSTRAINT_MAPPINGS("constraint-mappings"),
    BEAN("bean"),
    FIELD("field"),
    GETTER("getter"),
    CONSTRAINT("constraint"),
    VALIDATION_MESSAGE("message"),
    VALIDATION_GROUPS("groups"),
    VALIDATION_VALUE("value"),
    VALIDATION_ELEMENT("element");


    private final String name;

    XmlNodesConstraints(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

