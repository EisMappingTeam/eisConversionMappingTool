package com.eis.conv.mapping.srcHandler.output.reports;

import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;

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

    public static TableWithNamedCols createRulesReport(AppStartupParameters appStartupParameters, SourceFilesReader sourceFilesReader) {
        return ReportHandlerRules.createRulesReport(appStartupParameters, sourceFilesReader);
    }

    public static TableWithNamedCols createSuccessFilesReport(SourceFilesReader sourceFilesReader) {
        return ReportHandlerSuccess.createProcessedFilesReport(sourceFilesReader);
    }
}
