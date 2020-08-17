package com.eis.conv.mapping.srcHandler.output;

import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;
import com.eis.conv.mapping.srcHandler.source.obj.files.files.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.obj.files.files.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.obj.files.types.SourceFileContentTypeXML;

import java.util.List;

public final class OutputRepoAnalyzerHandler {

    //Column captions
    private static String FILES_REPORT_COL_CAPTION_FILE = "File";
    private static String SUM_REPORT_COL_CAPTION_FILES = "Files";
    private static String SUM_REPORT_COL_CAPTION_COUNT = "Count";

    private static String RULES_REPORT_COL_SOURCE = "Source";
    private static String RULES_REPORT_COL_CONTEXT = "Context";
    private static String RULES_REPORT_COL_APPLYEDTO = "Applyed to";
    private static String RULES_REPORT_COL_CODE = "Code";
    private static String RULES_REPORT_COL_ERROR = "Error Message";
    private static String RULES_REPORT_COL_ANNOTATION = "Annotation";
    private static String RULES_REPORT_COL_MESSAGE = "Message";
    private static String RULES_REPORT_COL_PACKAGE = "JPackage";
    private static String RULES_REPORT_COL_REPO = "Repository";


    public static TableWithNamedCols createSummaryReport(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = createSummaryReportBody(sourceFilesReader);
        return result;
    }

    public static TableWithNamedCols createErrorFilesReport(SourceFilesReader sourceFilesReader) {
        return createFilesReport(sourceFilesReader.getErrorFiles());
    }

    public static TableWithNamedCols createUnknownFilesReport(SourceFilesReader sourceFilesReader) {
        return createFilesReport(sourceFilesReader.getUnknownFiles());
    }

    public static TableWithNamedCols createRulesReport(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = createRulesReportEmptyColumns();
        populateRulesJava(result, sourceFilesReader);
        populateRulesXml(result, sourceFilesReader);
        return result;
    }


    private static TableWithNamedCols createFilesReport(List<String> srcFiles) {
        TableWithNamedCols result = new TableWithNamedCols();
        srcFiles.stream().forEach(item -> result.putInNewRow(FILES_REPORT_COL_CAPTION_FILE, item));
        return result;
    }

    private static TableWithNamedCols createSummaryReportBody(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = new TableWithNamedCols();

        result.putValue(0, SUM_REPORT_COL_CAPTION_FILES, "Java files");
        result.putValue(0, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getJavaFiles().size()));

        result.putValue(1, SUM_REPORT_COL_CAPTION_FILES, "XML files");
        result.putValue(1, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getXmlFiles().size()));

        result.putValue(2, SUM_REPORT_COL_CAPTION_FILES, "Properties files");
        result.putValue(2, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getPropertyFiles().size()));

        result.putValue(3, SUM_REPORT_COL_CAPTION_FILES, "Unknown files");
        result.putValue(3, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getUnknownFiles().size()));

        result.putValue(4, SUM_REPORT_COL_CAPTION_FILES, "Error files");
        result.putValue(4, SUM_REPORT_COL_CAPTION_COUNT, String.valueOf(sourceFilesReader.getErrorFiles().size()));
        return result;
    }

    private static TableWithNamedCols createRulesReportEmptyColumns() {
        TableWithNamedCols result = new TableWithNamedCols();
        result.addColumn(RULES_REPORT_COL_SOURCE);
        result.addColumn(RULES_REPORT_COL_CONTEXT);
        result.addColumn(RULES_REPORT_COL_APPLYEDTO);
        result.addColumn(RULES_REPORT_COL_CODE);
        result.addColumn(RULES_REPORT_COL_ERROR);
        result.addColumn(RULES_REPORT_COL_ANNOTATION);
        result.addColumn(RULES_REPORT_COL_MESSAGE);
        result.addColumn(RULES_REPORT_COL_PACKAGE);
        result.addColumn(RULES_REPORT_COL_REPO);
        return result;
    }

    private static void populateRulesJava(TableWithNamedCols report, SourceFilesReader sourceFilesReader) {
        sourceFilesReader.getJavaFiles().stream().forEach(item -> {
            populateRulesReportAnnotation(report, item);
        });
    }

    private static void populateRulesXml(TableWithNamedCols report, SourceFilesReader sourceFilesReader) {
        sourceFilesReader.getXmlFiles().stream().forEach(item -> {
            populateRulesReportXml(report, item);
        });
    }


    private static void populateRulesReportAnnotation(TableWithNamedCols report, SourceJavaFile jFile) {
        jFile.getAnnotations().stream().forEach(item -> {
            //File part
            int row = report.putInNewRow(RULES_REPORT_COL_REPO, jFile.getPartOfProduct());
            report.putValue(row, RULES_REPORT_COL_SOURCE, jFile.getFileName());
            report.putValue(row, RULES_REPORT_COL_CONTEXT, jFile.getClassName());
            report.putValue(row, RULES_REPORT_COL_PACKAGE, jFile.getPackageValue());

            //Annotation part
            if (item.getVariable().length() < 1) {
                report.putValue(row, RULES_REPORT_COL_APPLYEDTO, item.getMethod());
            } else {
                report.putValue(row, RULES_REPORT_COL_APPLYEDTO, item.getVariable());
            }
            report.putValue(row, RULES_REPORT_COL_CODE, item.getAnnotation());
            report.putValue(row, RULES_REPORT_COL_ERROR, "");
            report.putValue(row, RULES_REPORT_COL_ANNOTATION, item.getRawValue());
            report.putValue(row, RULES_REPORT_COL_MESSAGE, "");
        });
    }

    private static void populateRulesReportXml(TableWithNamedCols report, SourceXmlFile xFile) {
        if (xFile.getContentType() == SourceFileContentTypeXML.CONSTRAINT_VALIDATION_RULES) {
            xFile.getXmlConstraintValidations().stream().forEach(item -> {
                //File part
                int row = report.putInNewRow(RULES_REPORT_COL_REPO, xFile.getPartOfProduct());
                report.putValue(row, RULES_REPORT_COL_SOURCE, xFile.getFileName());

                //Rules part
                report.putValue(row, RULES_REPORT_COL_CONTEXT, item.getContext());
                report.putValue(row, RULES_REPORT_COL_APPLYEDTO, item.getApplyedTo());
                report.putValue(row, RULES_REPORT_COL_CODE, "");
                report.putValue(row, RULES_REPORT_COL_ERROR, item.getErrorMessage());
                report.putValue(row, RULES_REPORT_COL_ANNOTATION, "");
                report.putValue(row, RULES_REPORT_COL_MESSAGE, "");
            });
        }
    }

}
