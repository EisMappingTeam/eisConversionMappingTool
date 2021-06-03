package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.beans;

public enum XmlNodeBeanAttributeValues {
    RULES_ET("ruleSet"),
    FIELD_NAME("fieldName"),
    MAX_SIZE("maxSize"),
    MANDATORY("mandatory");

    private final String name;

    XmlNodeBeanAttributeValues(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
