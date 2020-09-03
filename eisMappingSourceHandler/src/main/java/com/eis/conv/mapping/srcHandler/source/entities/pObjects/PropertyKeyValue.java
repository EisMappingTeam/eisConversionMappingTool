package com.eis.conv.mapping.srcHandler.source.entities.pObjects;

public class PropertyKeyValue {
    private String key = "";
    private String value = "";

    public PropertyKeyValue() {
    }

    public PropertyKeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}