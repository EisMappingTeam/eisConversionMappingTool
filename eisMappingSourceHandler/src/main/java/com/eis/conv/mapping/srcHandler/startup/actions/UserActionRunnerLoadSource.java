package com.eis.conv.mapping.srcHandler.startup.actions;

import com.eis.conv.mapping.srcHandler.output.OutputRepoAnalyzerHandler;
import com.eis.conv.mapping.srcHandler.output.OutputRepoAnalyzerWriter;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;
import com.eis.conv.mapping.srcHandler.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserLoadSource;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupAction;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class UserActionRunnerLoadSource {
    public static void run(AppStartupParameters appParameters, UserStartupAction action) throws ParserConfigurationException, SAXException, IOException {
        System.out.println("Startup action: " + action.getActionName());

        //Read folders and sources
        UserLoadSource userLoadSource = action.getLoadSource() != null ? action.getLoadSource() : new UserLoadSource();
        SourceFilesReader sourceFilesReader = ReadSourceAction.readRepo(userLoadSource.getProject(), userLoadSource.getProduct(), userLoadSource.getVersion(), appParameters.getRepoRootDir());
        TableWithNamedCols errorReport = OutputRepoAnalyzerHandler.createErrorFilesReport(sourceFilesReader);
        TableWithNamedCols unknownReport = OutputRepoAnalyzerHandler.createUnknownFilesReport(sourceFilesReader);
        TableWithNamedCols summaryReport = OutputRepoAnalyzerHandler.createSummaryReport(sourceFilesReader);
        TableWithNamedCols rulesReport = OutputRepoAnalyzerHandler.createRulesReport(sourceFilesReader);

        String resultDir = userLoadSource.getResultDir();
        OutputRepoAnalyzerWriter.saveToFileErrorReport(errorReport, resultDir);
        OutputRepoAnalyzerWriter.saveToFileRulesReport(rulesReport, resultDir);
        OutputRepoAnalyzerWriter.saveToFileSummaryInfoReport(summaryReport, resultDir);
        OutputRepoAnalyzerWriter.saveToFileUnknownReport(unknownReport, resultDir);

        System.out.println("Java files: " + sourceFilesReader.getJavaFiles().size());
        System.out.println("XML files: " + sourceFilesReader.getXmlFiles().size());
        System.out.println("Properties files: " + sourceFilesReader.getPropertyFiles().size());
        System.out.println("Unknown files: " + sourceFilesReader.getUnknownFiles().size());
        System.out.println("Error files: " + sourceFilesReader.getErrorFiles().size());
    }


}
