package com.eis.conv.mapping.srcHandler.startup.acts;

import com.eis.conv.mapping.srcHandler.output.reports.OutputRepoAnalyzerHandler;
import com.eis.conv.mapping.srcHandler.output.OutputRepoAnalyzerWriter;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserLoadSource;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserStartupAction;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class UserActionRunnerLoadSource {
    public static void run(AppStartupParameters appParameters, UserStartupAction action) throws ParserConfigurationException, SAXException, IOException {

        //Read folders and sources
        UserLoadSource userLoadSource = action.getLoadSource() != null ? action.getLoadSource() : new UserLoadSource();
        SourceFilesReader sourceFilesReader = ReadSourceAction.readRepo(userLoadSource.getProject(), userLoadSource.getProduct(), userLoadSource.getVersion(), appParameters.getRepoRootDir());

        //Create reports
        TableWithNamedCols errorReport = OutputRepoAnalyzerHandler.createErrorFilesReport(sourceFilesReader);
        TableWithNamedCols unknownReport = OutputRepoAnalyzerHandler.createUnknownFilesReport(sourceFilesReader);
        TableWithNamedCols summaryReport = OutputRepoAnalyzerHandler.createSummaryReport(sourceFilesReader);
        TableWithNamedCols rulesReport = OutputRepoAnalyzerHandler.createRulesReport(appParameters, sourceFilesReader);
        TableWithNamedCols successFilesReport = OutputRepoAnalyzerHandler.createSuccessFilesReport(sourceFilesReader) ;

        //Print reports
        String resultDir = userLoadSource.getResultDir();
        OutputRepoAnalyzerWriter.saveToFileErrorReport(errorReport, resultDir);
        OutputRepoAnalyzerWriter.saveToFileRulesReport(rulesReport, resultDir);
        OutputRepoAnalyzerWriter.saveToFileSummaryInfoReport(summaryReport, resultDir);
        OutputRepoAnalyzerWriter.saveToFileUnknownReport(unknownReport, resultDir);
        OutputRepoAnalyzerWriter.saveToFileSuccessFilesReport(successFilesReport, resultDir);

        System.out.println("Java files: " + sourceFilesReader.getJavaFiles().size());
        System.out.println("XML files: " + sourceFilesReader.getXmlFiles().size());
        System.out.println("Properties files: " + sourceFilesReader.getPropertyFiles().size());
        System.out.println("Unknown files: " + sourceFilesReader.getUnknownFiles().size());
        System.out.println("Error files: " + sourceFilesReader.getErrorFiles().size());
    }


}
