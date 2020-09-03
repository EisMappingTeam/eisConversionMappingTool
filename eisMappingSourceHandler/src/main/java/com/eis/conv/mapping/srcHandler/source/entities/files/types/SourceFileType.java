package com.eis.conv.mapping.srcHandler.source.entities.files.types;

public enum SourceFileType {
    XML("XML"),
    JAVA("java"),
    PROPERTIES("properties"),
    UNKNOWN("unknown");

    private final String type;

    SourceFileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}


