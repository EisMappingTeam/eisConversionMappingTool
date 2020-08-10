package com.eis.conv.mapping.srcHandler.source.sourceObjects.xmlObjects;

public class XmlConstraintValidation {

    private String context = "";
    private String applyedTo = "";
    //private String priority = "";
    private String code = "";
    private String errorMessage = "";
    //private String assertExpression = "";
    //private String description = "";
    //private String condition = "";
    //private String defaultValue = "";
    //private String usageType = "";
    private String maximumLength = "";
    //private String overrideNonEmpty = "";
    //private String accessibilityValue = "";
    //private String visibility = "";
    private String regExpExpression = "";
    private String dataObject = "";


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getApplyedTo() {
        return applyedTo;
    }

    public void setApplyedTo(String applyedTo) {
        this.applyedTo = applyedTo;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getMaximumLength() {
        return maximumLength;
    }

    public void setMaximumLength(String maximumLength) {
        this.maximumLength = maximumLength;
    }


    public String getRegExpExpression() {
        return regExpExpression;
    }

    public void setRegExpExpression(String regExpExpression) {
        this.regExpExpression = regExpExpression;
    }

    public String getDataObject() {
        return dataObject;
    }

    public void setDataObject(String dataObject) {
        this.dataObject = dataObject;
    }
}
