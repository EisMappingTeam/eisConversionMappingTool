package com.eis.conv.mapping.srcHandler.source.obj.files.files;

import com.eis.conv.mapping.srcHandler.source.obj.files.types.SourceFileContentTypeProperty;

public class SourcePropertyFile extends SourceFile {
    private SourceFileContentTypeProperty contentType = SourceFileContentTypeProperty.UNKNOWN;

    public SourceFileContentTypeProperty getContentType() {
        return contentType;
    }

    public void setContentType(SourceFileContentTypeProperty contentType) {
        this.contentType = contentType;
    }
}
