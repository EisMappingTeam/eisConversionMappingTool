package com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects;

public class JAnnotation {
    //TODO: append with detailed annotation parameters
    private String annotation;
    private String rawValue;
    private boolean classLevel;
    private boolean methodLevel;
    private String parameters;

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public boolean isClassLevel() {
        return classLevel;
    }

    public void setClassLevel(boolean classLevel) {
        this.classLevel = classLevel;
        this.methodLevel = !this.classLevel;
    }

    public boolean isMethodLevel() {
        return methodLevel;
    }

    public void setMethodLevel(boolean methodLevel) {
        this.methodLevel = methodLevel;
        this.classLevel = !this.methodLevel;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

}
