package com.eis.conv.mapping.srcHandler.source.obj.files.files;

import com.eis.conv.mapping.srcHandler.source.obj.files.types.SourceFileContentTypeJava;
import com.eis.conv.mapping.srcHandler.source.obj.jObjects.JAnnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SourceJavaFile extends SourceFile {

    private final String ANNOTATION_ENTITY = "Entity";

    private SourceFileContentTypeJava contentType = SourceFileContentTypeJava.UNKNOWN;
    private String packageValue = "";
    private String className = "";
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
