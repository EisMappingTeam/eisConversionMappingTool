package com.eis.conv.mapping.srcHandler.output.obj;

public enum SummaryReportColumns {
    COL_FILES("Files"),
    COL_COUNT("Count");

    private final String col;

    SummaryReportColumns(String col) {
        this.col = col;
    }

    public String getCaption() {
        return col;
    }

}

