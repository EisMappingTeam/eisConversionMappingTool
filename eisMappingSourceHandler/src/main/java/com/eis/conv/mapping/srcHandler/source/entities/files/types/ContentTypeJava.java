package com.eis.conv.mapping.srcHandler.source.entities.files.types;

public enum ContentTypeJava {
    ENTITY("entity"),
    UNKNOWN("unknown");

    private final String type;

    ContentTypeJava(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}

