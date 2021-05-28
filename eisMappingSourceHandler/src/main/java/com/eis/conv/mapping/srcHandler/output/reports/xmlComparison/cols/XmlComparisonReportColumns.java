package com.eis.conv.mapping.srcHandler.output.reports.xmlComparison.cols;

public enum  XmlComparisonReportColumns {
    COL_IMPORT_PATH("Import Path"),
    COL_IMPORT_VALUE("Import Value"),
    COL_EXPORT_PATH("Export path"),
    COL_EXPORT_VALUE("Export value");

    private final String col;

    XmlComparisonReportColumns(String col) {
        this.col = col;
    }

    public String getCaption() {
        return col;
    }

}



