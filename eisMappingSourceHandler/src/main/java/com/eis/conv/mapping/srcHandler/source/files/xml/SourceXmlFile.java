package com.eis.conv.mapping.srcHandler.source.files.xml;

import com.eis.conv.mapping.srcHandler.source.files.SourceFile;
import com.eis.conv.mapping.srcHandler.source.files.types.ContentTypeXML;

public class SourceXmlFile extends SourceFile {
    private ContentTypeXML contentType = ContentTypeXML.UNKNOWN;

    public ContentTypeXML getContentType() {
        return contentType;
    }

    public void setContentType(ContentTypeXML contentType) {
        this.contentType = contentType;
    }


}
