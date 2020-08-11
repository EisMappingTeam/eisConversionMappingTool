package com.eis.conv.mapping.srcHandler.source.obj.files.files;

import com.eis.conv.mapping.srcHandler.source.obj.files.types.SourceFileContentTypeJava;
import com.eis.conv.mapping.srcHandler.source.obj.jObjects.JAnnotation;

import java.util.ArrayList;
import java.util.List;

public class SourceJavaFile extends SourceFile {
    private SourceFileContentTypeJava contentType = SourceFileContentTypeJava.UNKNOWN;
    private String packageValue = "";
    private List<JAnnotation> annotations = new ArrayList<>();

    public SourceJavaFile() {
    }

    public SourceFileContentTypeJava getContentType() {
        return contentType;
    }

    public void setContentType(SourceFileContentTypeJava contentType) {
        this.contentType = contentType;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public List<JAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<JAnnotation> annotations) {
        this.annotations = annotations;
    }

    public JAnnotation getAnnotationByName(String annotationName) {
        return annotations.stream().filter(item -> item.getAnnotation().equalsIgnoreCase(annotationName)).findFirst().orElse(null);
    }

}