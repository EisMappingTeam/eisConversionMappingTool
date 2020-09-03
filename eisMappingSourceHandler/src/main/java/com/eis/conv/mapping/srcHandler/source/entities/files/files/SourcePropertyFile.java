package com.eis.conv.mapping.srcHandler.source.entities.files.files;

import com.eis.conv.mapping.srcHandler.source.entities.files.types.SourceFileContentTypeProperty;
import com.eis.conv.mapping.srcHandler.source.entities.pObjects.PropertyKeyValue;

import java.util.ArrayList;
import java.util.List;

public class SourcePropertyFile extends SourceFile {
    private SourceFileContentTypeProperty contentType = SourceFileContentTypeProperty.UNKNOWN;
    private List<PropertyKeyValue> propertyKeyValues = new ArrayList<>();

    public SourceFileContentTypeProperty getContentType() {
        return contentType;
    }

    public void setContentType(SourceFileContentTypeProperty contentType) {
        this.contentType = contentType;
    }

    public List<PropertyKeyValue> getPropertyKeyValues() {
        return propertyKeyValues;
    }

    public void setPropertyKeyValues(List<PropertyKeyValue> propertyKeyValues) {
        this.propertyKeyValues = propertyKeyValues;
    }

    public boolean isKeyPresent(String key) {
        return propertyKeyValues.stream().findFirst().filter(item -> item.getKey().trim().equals(key.trim())).isPresent();
    }

    public PropertyKeyValue getProperty(String key) {
        return propertyKeyValues.stream().findFirst().filter(item -> item.getKey().equals(key)).orElse(new PropertyKeyValue());
    }
}
