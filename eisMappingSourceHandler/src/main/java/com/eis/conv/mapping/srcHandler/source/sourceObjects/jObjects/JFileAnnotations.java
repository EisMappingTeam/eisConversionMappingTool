package com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects;

import java.util.ArrayList;
import java.util.List;

public class JFileAnnotations {
    private String fileName = "";
    private String project = "";
    private String product = "";
    private String fileExtension = "";
    private String packageValue = "";
    private List<JAnnotation> annotations = new ArrayList<>();

    public JFileAnnotations() {
    }

    public JFileAnnotations(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
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
}
