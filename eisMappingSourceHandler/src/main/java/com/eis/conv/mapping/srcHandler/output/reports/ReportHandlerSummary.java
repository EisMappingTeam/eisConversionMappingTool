package com.eis.conv.mapping.srcHandler.output.reports;

import com.eis.conv.mapping.srcHandler.output.obj.SummaryReportColumns;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;

public final class ReportHandlerSummary {

    public static TableWithNamedCols createSummaryReport(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = createSummaryReportBody(sourceFilesReader);
        return result;
    }


    private static TableWithNamedCols createSummaryReportBody(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = new TableWithNamedCols();

        result.putValue(0, SummaryReportColumns.COL_FILES.getCaption(), "Java srcFiles");
        result.putValue(0, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getJavaFiles().size()));

        result.putValue(1, SummaryReportColumns.COL_FILES.getCaption(), "XML srcFiles");
        result.putValue(1, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getXmlFiles().size()));

        result.putValue(2, SummaryReportColumns.COL_FILES.getCaption(), "Properties srcFiles");
        result.putValue(2, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getPropertyFiles().size()));

        result.putValue(3, SummaryReportColumns.COL_FILES.getCaption(), "Unknown srcFiles");
        result.putValue(3, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getUnknownFiles().size()));

        result.putValue(4, SummaryReportColumns.COL_FILES.getCaption(), "Error srcFiles");
        result.putValue(4, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getErrorFiles().size()));
        return result;
    }

}
