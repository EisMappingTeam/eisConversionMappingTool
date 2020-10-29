package com.eis.conv.mapping.srcHandler.source.files;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.srcHandler.source.files.types.SourceFileType;

public final class SourceFileChecker {
    private static final String FILE_EXT_JAVA = "java";
    private static final String FILE_EXT_XML = "xml";
    private static final String FILE_EXT_PROPERTIES = "properties";

    public static SourceFileType getFileType(String file) {
        String ext = FileHelper.getFileExtension(file);
        return SourceFileChecker.getFileTypeByExtension(ext);
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
