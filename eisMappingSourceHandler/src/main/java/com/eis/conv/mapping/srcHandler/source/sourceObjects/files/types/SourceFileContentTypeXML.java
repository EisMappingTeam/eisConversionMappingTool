package com.eis.conv.mapping.srcHandler.source.sourceObjects.files.types;

public enum SourceFileContentTypeXML {
    RULES("rules"),
    UNKNOWN("unknown");

    private final String type;

    SourceFileContentTypeXML(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
