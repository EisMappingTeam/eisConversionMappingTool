package com.eis.conv.mapping.srcHandler.source.sourceObjects.files.types;

public enum SourceFileContentTypeJava {
    ENTITY("entity"),
    UNKNOWN("unknown");

    private final String type;

    SourceFileContentTypeJava(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}

