package com.eis.conv.mapping.srcHandler.output.reports.xmlComparison;

        import com.eis.conv.mapping.srcHandler.output.TableReportWriter;
        import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;

public class OutputXmlComparisonWriter {
    private static String OUTPUT_FILE_IMPORT_VS_EXPORT = "Import_vs_Export.csv";
    private static String OUTPUT_FILE_EXPORT_VS_IMPORT = "Export_vs_Import.csv";


    public static boolean saveToFileImportVsExportReport(TableWithNamedCols report, String resultDir) {
        return TableReportWriter.saveReportToFile(report, resultDir, OUTPUT_FILE_IMPORT_VS_EXPORT);
    }

    public static boolean saveToFileExportVsImportReport(TableWithNamedCols report, String resultDir) {
        return TableReportWriter.saveReportToFile(report, resultDir, OUTPUT_FILE_EXPORT_VS_IMPORT);
    }

}
