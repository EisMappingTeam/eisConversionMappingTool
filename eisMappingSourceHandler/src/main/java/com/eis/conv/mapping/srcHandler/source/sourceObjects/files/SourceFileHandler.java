package com.eis.conv.mapping.srcHandler.source.sourceObjects.files;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.types.SourceFileType;

public final class SourceFileHandler {
    private static final String FILE_EXT_JAVA = "java";
    private static final String FILE_EXT_XML = "xml";
    private static final String FILE_EXT_PROPERTIES = "properties";

    public static SourceFileType getFileType(String file) {
        String ext = FileHelper.getFileExtension(file);
        return SourceFileHandler.getFileTypeByExtension(ext);
    }

    public static SourceFileType getFileTypeByExtension(String ext) {
        if (ext.equalsIgnoreCase(FILE_EXT_JAVA)) {
            return SourceFileType.JAVA;
        } else if (ext.equalsIgnoreCase(FILE_EXT_XML)) {
            return SourceFileType.XML;
        } else if (ext.equalsIgnoreCase(FILE_EXT_PROPERTIES)) {
            return SourceFileType.PROPERTIES;
        } else {
            return SourceFileType.UNKNOWN;
        }

    }
}
