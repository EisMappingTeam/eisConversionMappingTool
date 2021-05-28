package com.eis.conv.mapping.srcHandler.startup.acts;


import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.output.reports.xmlComparison.OutputXmlComparisonWriter;
import com.eis.conv.mapping.srcHandler.output.reports.xmlComparison.ReportHandlerXmlComparison;
import com.eis.conv.mapping.srcHandler.processing.compareXml.ProductXmlReader;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserCompareImportExport;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserStartupAction;

import java.util.List;

public class UserActionRunnerCompareXmlImportExport {
    public static void run(AppStartupParameters appParameters, UserStartupAction action) {

        //Read folders and sources
        UserCompareImportExport compareImportExport = action.getCompareImportExport() != null ? action.getCompareImportExport() : new UserCompareImportExport();
        List<XmlNode> importData = ProductXmlReader.readXmlToList(compareImportExport.getImportFile());
        List<XmlNode> exportData = ProductXmlReader.readXmlToList(compareImportExport.getExportFile());
        String outputDir = compareImportExport.getResultDir();

        //Create reports
        TableWithNamedCols reportImportVsExport = ReportHandlerXmlComparison.createImportVsExportReport(importData, exportData);
        TableWithNamedCols reportExportVsImport = ReportHandlerXmlComparison.createImportVsExportReport(importData, exportData);

        //Print reports
        OutputXmlComparisonWriter.saveToFileImportVsExportReport(reportImportVsExport, outputDir);
        OutputXmlComparisonWriter.saveToFileExportVsImportReport(reportExportVsImport, outputDir);
    }

}
