package com.eis.conv.mapping.srcHandler.output;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TableReportWriter {

    private static String COL_SPLITTER = "\t";
    private static String END_ROW = "\r\n";

    public static boolean saveReportToFile(TableWithNamedCols report, String resultDir, String fileName) {
        return saveToFile(report, resultDir, fileName);
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