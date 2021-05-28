package com.eis.conv.mapping.srcHandler.output.reports.sourceAnalysis;

import com.eis.conv.mapping.srcHandler.output.reports.sourceAnalysis.cols.FilesListReportColumns;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;

import java.util.ArrayList;
import java.util.List;

public final class ReportHandlerSuccess {


    public static TableWithNamedCols createProcessedFilesReport(SourceFilesReader sourceFilesReader) {
        List<String> allFiles = new ArrayList<>();
        sourceFilesReader.getJavaFiles().stream().forEach(item -> allFiles.add(item.getFileName()));
        sourceFilesReader.getPropertyFiles().stream().forEach(item -> allFiles.add(item.getFileName()));
        sourceFilesReader.getXmlFiles().stream().forEach(item -> allFiles.add(item.getFileName()));

        return createFilesReport(allFiles);
    }


    private static TableWithNamedCols createFilesReport(List<String> srcFiles) {
        TableWithNamedCols result = new TableWithNamedCols();
        srcFiles.stream().forEach(item -> result.putInNewRow(FilesListReportColumns.COL_FILE.getCaption(), item));
        return result;
    }
}

