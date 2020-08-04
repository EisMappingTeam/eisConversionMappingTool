package com.eis.conv.mapping.srcHandler.source.sourceObjects.files;

import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.types.SourceFileContentTypeProperty;

public class SourcePropertyFile extends SourceFile {
    private SourceFileContentTypeProperty contentType;

    public SourceFileContentTypeProperty getContentType() {
        return contentType;
    }

    public void setContentType(SourceFileContentTypeProperty contentType) {
        this.contentType = contentType;
    }
}