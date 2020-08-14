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
    private static String SUM_REPORT_COL_CAPTION_FILES = "Files";
    private static String SUM_REPORT_COL_CAPTION_COUNT = "Count";

    public static TableWithNamedCols createSummaryReport(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = new TableWithNamedCols();

        result.putValue(0, SUM_REPORT_COL_CAPTION_FILES, "Java files");
        result.putValue(0, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getJavaFiles().size()));
        result.putValue(1, SUM_REPORT_COL_CAPTION_FILES, "XML files");
        result.putValue(1, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getXmlFiles().size()));
        result.putValue(2, SUM_REPORT_COL_CAPTION_FILES, "Properties files");
        result.putValue(2, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getPropertyFiles().size()));
        result.putValue(3, SUM_REPORT_COL_CAPTION_FILES, "Unknown files");
        result.putValue(3, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getUnknownFiles().size()));
        result.putValue(4, SUM_REPORT_COL_CAPTION_FILES, "Error files");
        result.putValue(4, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getErrorFiles().size()));

        return result;
    }

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
