package com.eis.conv.mapping.srcHandler.source.sourceObjects.files.types;

public enum SourceFileType {
    XML("XML"),
    JAVA("java"),
    PROPERTY("property");

    private final String type;

    SourceFileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}


