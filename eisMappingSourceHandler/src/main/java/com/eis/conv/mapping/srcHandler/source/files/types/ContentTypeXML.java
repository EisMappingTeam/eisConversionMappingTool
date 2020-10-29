package com.eis.conv.mapping.srcHandler.source.files.types;

public enum ContentTypeXML {
    CONSTRAINT_VALIDATION_RULES("constraint_validation_rules"),
    BEAN_VALIDATION_RULES("bean_validation_rules"),
    UNKNOWN("unknown");

    private final String type;

    ContentTypeXML(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
