package com.eis.conv.mapping.srcHandler.output.reports.xmlComparison;

import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.output.reports.xmlComparison.cols.XmlComparisonReportColumns;


import java.util.List;

public class ReportHandlerXmlComparison {

    public static TableWithNamedCols createImportVsExportReport(List<XmlNode> importNodes, List<XmlNode> exportNodes) {
        return importVsExportReport(importNodes, exportNodes);
    }

    public static TableWithNamedCols createExportVsImportReport(List<XmlNode> importNodes, List<XmlNode> exportNodes) {
        return exportVsImportReport(importNodes, exportNodes);
    }


    private static TableWithNamedCols importVsExportReport(List<XmlNode> importNodes, List<XmlNode> exportNodes) {
        TableWithNamedCols rep = new TableWithNamedCols();
        rep.addColumn(XmlComparisonReportColumns.COL_IMPORT_PATH.getCaption());
        rep.addColumn(XmlComparisonReportColumns.COL_IMPORT_VALUE.getCaption());
        rep.addColumn(XmlComparisonReportColumns.COL_EXPORT_PATH.getCaption());
        rep.addColumn(XmlComparisonReportColumns.COL_EXPORT_VALUE.getCaption());
        populateReport(rep, importNodes, exportNodes);
        return rep;
    }

    private static TableWithNamedCols exportVsImportReport(List<XmlNode> importNodes, List<XmlNode> exportNodes) {
        TableWithNamedCols rep = new TableWithNamedCols();
        rep.addColumn(XmlComparisonReportColumns.COL_EXPORT_PATH.getCaption());
        rep.addColumn(XmlComparisonReportColumns.COL_EXPORT_VALUE.getCaption());
        rep.addColumn(XmlComparisonReportColumns.COL_IMPORT_PATH.getCaption());
        rep.addColumn(XmlComparisonReportColumns.COL_IMPORT_VALUE.getCaption());
        populateReport(rep, exportNodes, importNodes);
        return rep;
    }

    private static void populateReport(TableWithNamedCols rep, List<XmlNode> leftNodes, List<XmlNode> rightNodes) {
        int row;
        String colCaption;
        XmlNode correspondingNode;

        for (XmlNode item : leftNodes) {
            colCaption = rep.getColumnCaption(0);
            row = rep.putInNewRow(colCaption, item.getPath());
            colCaption = rep.getColumnCaption(1);
            rep.putValue(row, colCaption, item.getValue());

            correspondingNode = getNodeByPath(item.getPath(), rightNodes);
            colCaption = rep.getColumnCaption(2);
            rep.putValue(row, colCaption, correspondingNode.getPath());
            colCaption = rep.getColumnCaption(3);
            rep.putValue(row, colCaption, correspondingNode.getValue());
        }
    }

    private static XmlNode getNodeByPath(String path, List<XmlNode> nodes) {
        for (XmlNode item : nodes) {
            if (item.getPath().equalsIgnoreCase(path)) {
                return item;
            }
        }
        return new XmlNode();
    }

}


