package com.eis.conv.mapping.srcHandler.output.reports.sourceAnalysis;

import com.eis.conv.mapping.srcHandler.output.reports.sourceAnalysis.cols.FilesListReportColumns;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;

import java.util.List;

public final class ReportHandlerUnknown {

    public static TableWithNamedCols createUnknownFilesReport(SourceFilesReader sourceFilesReader) {
        return createFilesReport(sourceFilesReader.getUnknownFiles());
    }


    private static TableWithNamedCols createFilesReport(List<String> srcFiles) {
        TableWithNamedCols result = new TableWithNamedCols();
        srcFiles.stream().forEach(item -> result.putInNewRow(FilesListReportColumns.COL_FILE.getCaption(), item));
        return result;
    }
}
