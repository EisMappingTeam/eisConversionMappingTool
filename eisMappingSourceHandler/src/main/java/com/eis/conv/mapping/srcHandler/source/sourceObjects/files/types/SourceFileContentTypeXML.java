package com.eis.conv.mapping.srcHandler.source.sourceObjects.files.types;

public enum SourceFileContentTypeXML {
    CONSTRAINT_VALIDATION_RULES("constraint_validation_rules"),
    UNKNOWN("unknown");

    private final String type;

    SourceFileContentTypeXML(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
