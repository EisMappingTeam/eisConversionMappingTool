package com.eis.conv.mapping.srcHandler.source.entities.files.types;

public enum ContentTypeProperty {
    MESSAGES("messages"),
    UNKNOWN("unknown");

    private final String type;

    ContentTypeProperty(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
