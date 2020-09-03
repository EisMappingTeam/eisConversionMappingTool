package com.eis.conv.mapping.srcHandler.source.entities.files.types;

public enum SourceFileContentTypeProperty {
    MESSAGES("messages"),
    UNKNOWN("unknown");

    private final String type;

    SourceFileContentTypeProperty(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}