package com.eis.conv.mapping.srcHandler.output.reports.sourceAnalysis;

import com.eis.conv.mapping.srcHandler.output.reports.sourceAnalysis.cols.SummaryReportColumns;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;

public final class ReportHandlerSummary {

    public static TableWithNamedCols createSummaryReport(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = createSummaryReportBody(sourceFilesReader);
        return result;
    }


    private static TableWithNamedCols createSummaryReportBody(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = new TableWithNamedCols();

        result.putValue(0, SummaryReportColumns.COL_FILES.getCaption(), "Java files");
        result.putValue(0, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getJavaFiles().size()));

        result.putValue(1, SummaryReportColumns.COL_FILES.getCaption(), "XML files");
        result.putValue(1, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getXmlFiles().size()));

        result.putValue(2, SummaryReportColumns.COL_FILES.getCaption(), "Properties files");
        result.putValue(2, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getPropertyFiles().size()));

        result.putValue(3, SummaryReportColumns.COL_FILES.getCaption(), "Unknown files");
        result.putValue(3, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getUnknownFiles().size()));

        result.putValue(4, SummaryReportColumns.COL_FILES.getCaption(), "Error files");
        result.putValue(4, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getErrorFiles().size()));
        return result;
    }

}
