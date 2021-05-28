package com.eis.conv.mapping.srcHandler.output.reports.sourceAnalysis;

import com.eis.conv.mapping.srcHandler.output.TableReportWriter;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;

public final class OutputRepoAnalyzerWriter {


    private static String OUTPUT_FILE_ERR_REPORT = "ErrorFiles.csv";
    private static String OUTPUT_FILE_UNKNOWN_REPORT = "UnknownFiles.csv";
    private static String OUTPUT_FILE_SUMMARYINFO_REPORT = "SummaryInfo.txt";
    private static String OUTPUT_FILE_RULES_REPORT = "rules.csv";
    private static String OUTPUT_FILE_SUCCESS_REPORT = "successFiles.csv";


    public static boolean saveToFileErrorReport(TableWithNamedCols report, String resultDir) {
        return TableReportWriter.saveReportToFile(report, resultDir, OUTPUT_FILE_ERR_REPORT);
    }

    public static boolean saveToFileUnknownReport(TableWithNamedCols report, String resultDir) {
        return TableReportWriter.saveReportToFile(report, resultDir, OUTPUT_FILE_UNKNOWN_REPORT);
    }

    public static boolean saveToFileSummaryInfoReport(TableWithNamedCols report, String resultDir) {
        return TableReportWriter.saveReportToFile(report, resultDir, OUTPUT_FILE_SUMMARYINFO_REPORT);
    }

    public static boolean saveToFileRulesReport(TableWithNamedCols report, String resultDir) {
        return TableReportWriter.saveReportToFile(report, resultDir, OUTPUT_FILE_RULES_REPORT);
    }

    public static boolean saveToFileSuccessFilesReport(TableWithNamedCols report, String resultDir) {
        return TableReportWriter.saveReportToFile(report, resultDir, OUTPUT_FILE_SUCCESS_REPORT);
    }

}
