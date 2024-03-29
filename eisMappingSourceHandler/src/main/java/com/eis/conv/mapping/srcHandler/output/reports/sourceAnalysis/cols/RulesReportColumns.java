package com.eis.conv.mapping.srcHandler.output.reports.sourceAnalysis.cols;

public enum RulesReportColumns {
    COL_SOURCE("Source"),
    COL_CONTEXT("Context"),
    COL_APPLIED_TO("Applied to"),
    COL_CODE("Code"),
    COL_ERROR_CODE("Error Message"),
    COL_ERROR_MESSAGE("Message"),
    COL_PARAM_MAX("Max"),
    COL_PARAM_MIN("Min"),
    COL_PARAM_LENGTH("Length"),
    COL_PARAM_GROUP_COND("Group conditions"),
    COL_PARAM_REGEXP("Regexp"),
    COL_ANNOTATION("Annotation"),
    COL_PACKAGE("JPackage"),
    COL_REPO("Repository"),
    COL_COND_DEFINED_IN("Defined in");


    private final String col;

    RulesReportColumns(String col) {
        this.col = col;
    }

    public String getCaption() {
        return col;
    }

}
