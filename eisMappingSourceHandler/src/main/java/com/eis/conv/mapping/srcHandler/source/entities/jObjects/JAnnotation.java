package com.eis.conv.mapping.srcHandler.source.entities.jObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JAnnotation {
    //TODO: append with detailed annotation parameters
    private final String PARAMETER_KEY_VALUE_SPLITTER = "=";

    private String variable = "";
    private String method = "";
    private String annotation = "";
    private String rawValue = "";
    private boolean classLevel;
    private boolean methodLevel;
    private List<String> parameters = new ArrayList();

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

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

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String getParameterValues(String key) {
        if (key.trim().length() < 1) {
            return "";
        }
        return getParameterValuesByKey(key.trim());
    }


    private String getParameterValuesByKey(String key) {
        List<String> values = new ArrayList<>();
        parameters.stream().forEach(item -> {
            String str[] = item.split(PARAMETER_KEY_VALUE_SPLITTER);
            if (str.length > 1) {
                str[0] = str[0].trim();
                str[1] = str[1].trim();
                if (str[0].equalsIgnoreCase(key)) {
                    values.add(str[1].replace("\"", ""));
                }
            }
        });
        return "" + values.stream().collect(Collectors.joining(", "));  //not null
    }
}
