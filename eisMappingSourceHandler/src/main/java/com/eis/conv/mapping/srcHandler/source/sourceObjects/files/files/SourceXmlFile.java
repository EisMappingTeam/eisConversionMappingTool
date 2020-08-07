package com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files;

import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files.SourceFile;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.types.SourceFileContentTypeXML;

public class SourceXmlFile extends SourceFile {
    private SourceFileContentTypeXML contentType;

    public SourceFileContentTypeXML getContentType() {
        return contentType;
    }

    public void setContentType(SourceFileContentTypeXML contentType) {
        this.contentType = contentType;
    }
}
