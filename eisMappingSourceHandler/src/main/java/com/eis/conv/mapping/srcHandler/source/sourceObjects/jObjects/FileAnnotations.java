package com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects;

import java.util.ArrayList;
import java.util.List;

public class FileAnnotations {
    private String fileName;
    private List<JAnnotation> annotations = new ArrayList<JAnnotation>();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<JAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<JAnnotation> annotations) {
        this.annotations = annotations;
    }
}
