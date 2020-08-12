package com.eis.conv.mapping.srcHandler.output;

import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;

import java.util.List;

public final class OutputRepoAnalyzerHandler {

    private static String OUTPUT_FILE_ERR_REPORT = "ErrorFiles.csv";
    private static String OUTPUT_FILE_UNKNOWN_REPORT = "UnknownFiles.csv";
    private static String OUTPUT_FILE_SUMMARYINFO_REPORT = "SummaryInfo.txt";
    private static String OUTPUT_FILE_RULES_REPORT = "rules.csv";

    private static String ERR_REPORT_COL_CAPTION_FILE = "File";

    public static TableWithNamedCols createErrorFilesReport(SourceFilesReader sourceFilesReader) {
        return createFilesReport(sourceFilesReader.getErrorFiles());
    }

    public static TableWithNamedCols createUnknownFilesReport(SourceFilesReader sourceFilesReader) {
        return createFilesReport(sourceFilesReader.getUnknownFiles());
    }



    private static TableWithNamedCols createFilesReport(List<String> files) {
        TableWithNamedCols result = new TableWithNamedCols();
        files.stream().forEach(item -> result.putInNewRow(ERR_REPORT_COL_CAPTION_FILE, item));
        return result;
    }

}
