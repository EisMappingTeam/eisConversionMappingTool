package com.eis.conv.mapping.srcHandler.source.files.xml.xmlObjects;

public class XmlBeanValidation {
    private String fieldName = "";
    private String lookupName = "";
    private boolean mandatory = false;
    private String min = "";
    private String max = "";
    private String minSize = "";
    private String maxSize = "";
    private String regExPattern = "";
    private String description = "";

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getLookupName() {
        return lookupName;
    }

    public void setLookupName(String lookupName) {
        this.lookupName = lookupName;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMinSize() {
        return minSize;
    }

    public void setMinSize(String minSize) {
        this.minSize = minSize;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getRegExPattern() {
        return regExPattern;
    }

    public void setRegExPattern(String regExPattern) {
        this.regExPattern = regExPattern;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
