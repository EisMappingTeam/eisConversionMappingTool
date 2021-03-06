package com.eis.conv.mapping.srcHandler.source.files.properties;

import com.eis.conv.mapping.srcHandler.source.files.SourceFile;
import com.eis.conv.mapping.srcHandler.source.files.types.ContentTypeProperty;
import com.eis.conv.mapping.srcHandler.source.files.properties.pObjects.PropertyKeyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SourcePropertyFile extends SourceFile {
    private ContentTypeProperty contentType = ContentTypeProperty.UNKNOWN;
    private List<PropertyKeyValue> propertyKeyValues = new ArrayList<>();

    public ContentTypeProperty getContentType() {
        return contentType;
    }

    public void setContentType(ContentTypeProperty contentType) {
        this.contentType = contentType;
    }

    public List<PropertyKeyValue> getPropertyKeyValues() {
        return propertyKeyValues;
    }

    public void setPropertyKeyValues(List<PropertyKeyValue> propertyKeyValues) {
        this.propertyKeyValues = propertyKeyValues;
    }

    public boolean isKeyPresent(String key) {
        Optional<PropertyKeyValue> s=  propertyKeyValues.stream().filter(item -> item.getKey().trim().equalsIgnoreCase(key.trim()) ).findFirst();
        return s.isPresent();
    }

    public PropertyKeyValue getProperty(String key) {
        PropertyKeyValue s=  propertyKeyValues.stream().filter(item -> item.getKey().trim().equalsIgnoreCase(key.trim()) ).findFirst().orElse(new PropertyKeyValue());
        return s;
    }
}
