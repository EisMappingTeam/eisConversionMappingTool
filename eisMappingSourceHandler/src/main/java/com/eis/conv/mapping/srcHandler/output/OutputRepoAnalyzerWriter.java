package com.eis.conv.mapping.srcHandler.output;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public final class OutputRepoAnalyzerWriter {

    private static String COL_SPLITTER = "\t";
    private static String END_ROW = "\r\n";

    private static String OUTPUT_FILE_ERR_REPORT = "ErrorFiles.csv";
    private static String OUTPUT_FILE_UNKNOWN_REPORT = "UnknownFiles.csv";
    private static String OUTPUT_FILE_SUMMARYINFO_REPORT = "SummaryInfo.txt";
    private static String OUTPUT_FILE_RULES_REPORT = "rules.csv";


    public static boolean saveToFileErrorReport(TableWithNamedCols report, String resultDir) {
        return saveToFile(report, resultDir, OUTPUT_FILE_ERR_REPORT);
    }

    public static boolean saveToFileUnknownReport(TableWithNamedCols report, String resultDir) {
        return saveToFile(report, resultDir, OUTPUT_FILE_UNKNOWN_REPORT);
    }

    public static boolean saveToFileSummaryInfoReport(TableWithNamedCols report, String resultDir) {
        return saveToFile(report, resultDir, OUTPUT_FILE_SUMMARYINFO_REPORT);
    }

    public static boolean saveToFileRulesReport(TableWithNamedCols report, String resultDir) {
        return saveToFile(report, resultDir, OUTPUT_FILE_RULES_REPORT);
    }


    private static boolean saveToFile(TableWithNamedCols report, String resultDir, String fileName) {
        String fileFullName = FileHelper.extendPath(resultDir, fileName);
        if (FileHelper.isFileExist(fileFullName)) {
            if (!FileHelper.fileDelete(fileFullName)) {
                return false;
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileFullName));
            writer.write(report.getCaptionsAsString(COL_SPLITTER) + END_ROW);

            for (int i = 0; i < report.getRowsCount(); i++) {
                String s;
                s = report.getRowAsString(i, COL_SPLITTER);
                writer.write(s + END_ROW);
            }
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
