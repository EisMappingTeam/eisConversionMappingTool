package com.eis.conv.mapping.srcHandler.output.reports;

import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;

public final class OutputRepoAnalyzerHandler {


    public static TableWithNamedCols createSummaryReport(SourceFilesReader sourceFilesReader) {
        return ReportHandlerSummary.createSummaryReport(sourceFilesReader);
    }

    public static TableWithNamedCols createErrorFilesReport(SourceFilesReader sourceFilesReader) {
        return ReportHandlerErrorFiles.createErrorFilesReport(sourceFilesReader);
    }

    public static TableWithNamedCols createUnknownFilesReport(SourceFilesReader sourceFilesReader) {
        return ReportHandlerUnknown.createUnknownFilesReport(sourceFilesReader);
    }

    public static TableWithNamedCols createRulesReport(SourceFilesReader sourceFilesReader) {
        return ReportHandlerRules.createRulesReport(sourceFilesReader);
    }

    public static TableWithNamedCols createSuccessFilesReport(SourceFilesReader sourceFilesReader) {
        return ReportHandlerSuccess.createProcessedFilesReport(sourceFilesReader);
    }
}
