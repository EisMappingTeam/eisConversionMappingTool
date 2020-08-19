package com.eis.conv.mapping.srcHandler.output.obj;

public enum FilesListReportColumns {
    COL_FILE("File");

    private final String col;

    FilesListReportColumns(String col) {
        this.col = col;
    }

    public String getCaption() {
        return col;
    }

}

