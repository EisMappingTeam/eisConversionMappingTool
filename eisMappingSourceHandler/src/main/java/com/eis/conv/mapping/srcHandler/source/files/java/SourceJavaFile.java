package com.eis.conv.mapping.srcHandler.source.files.java;

import com.eis.conv.mapping.srcHandler.source.files.SourceFile;
import com.eis.conv.mapping.srcHandler.source.files.java.jObjects.JMethod;
import com.eis.conv.mapping.srcHandler.source.files.types.ContentTypeJava;
import com.eis.conv.mapping.srcHandler.source.files.java.jObjects.JAnnotation;
import com.eis.conv.mapping.srcHandler.source.files.java.jObjects.JVariableDeclaration;

import java.util.ArrayList;
import java.util.List;

public class SourceJavaFile extends SourceFile {

    private final String ANNOTATION_ENTITY = "Entity";

    private ContentTypeJava contentType = ContentTypeJava.UNKNOWN;
    private String packageValue = "";
    private String className = "";
    private List<JAnnotation> annotations = new ArrayList<>();
    private List<JVariableDeclaration> variables = new ArrayList<>();
    private List<JMethod> methods = new ArrayList<>();

    public SourceJavaFile() {
    }

    public ContentTypeJava getContentType() {
        return contentType;
    }

    public void setContentType(ContentTypeJava contentType) {
        this.contentType = contentType;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<JAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<JAnnotation> annotations) {
        this.annotations = annotations;
    }

    public List<JVariableDeclaration> getVariables() {
        return variables;
    }

    public void setVariables(List<JVariableDeclaration> variables) {
        this.variables = variables;
    }

    public JVariableDeclaration getVariableByName(String varName) {
        return variables.stream().filter(item -> item.getVariable().equalsIgnoreCase(varName)).findFirst().orElse(null);
    }

    public List<JMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<JMethod> methods) {
        this.methods = methods;
    }

    public JAnnotation getAnnotationByName(String annotationName) {
        return annotations.stream().filter(item -> item.getAnnotation().equalsIgnoreCase(annotationName)).findFirst().orElse(null);
    }

    public boolean isEntity() {
        long cnt =annotations.stream()
                .filter(item -> item.getAnnotation().equalsIgnoreCase(ANNOTATION_ENTITY))
                .count();
        return cnt>0;
    }
}
