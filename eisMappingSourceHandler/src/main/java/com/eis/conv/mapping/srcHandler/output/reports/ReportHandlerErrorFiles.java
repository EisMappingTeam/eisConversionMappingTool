package com.eis.conv.mapping.srcHandler.output.reports;

import com.eis.conv.mapping.srcHandler.output.obj.FilesListReportColumns;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;

import java.util.List;

public final class ReportHandlerErrorFiles {

    public static TableWithNamedCols createErrorFilesReport(SourceFilesReader sourceFilesReader) {
        return createFilesReport(sourceFilesReader.getErrorFiles());
    }


    private static TableWithNamedCols createFilesReport(List<String> srcFiles) {
        TableWithNamedCols result = new TableWithNamedCols();
        srcFiles.stream().forEach(item -> result.putInNewRow(FilesListReportColumns.COL_FILE.getCaption(), item));
        return result;
    }


}
