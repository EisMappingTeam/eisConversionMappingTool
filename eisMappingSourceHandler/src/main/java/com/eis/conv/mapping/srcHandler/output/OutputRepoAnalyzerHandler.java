package com.eis.conv.mapping.srcHandler.output;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.output.obj.FilesListReportColumns;
import com.eis.conv.mapping.srcHandler.output.obj.RulesReportColumns;
import com.eis.conv.mapping.srcHandler.output.obj.SummaryReportColumns;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;
import com.eis.conv.mapping.srcHandler.source.obj.files.files.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.obj.files.files.SourcePropertyFile;
import com.eis.conv.mapping.srcHandler.source.obj.files.files.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.obj.files.types.SourceFileContentTypeXML;

import java.util.List;

public final class OutputRepoAnalyzerHandler {
    //Annotation parameters
    private static String ANNOTATION_PARAMETERS_MESSAGE = "message";
    private static String ANNOTATION_PARAMETERS_MAX = "max";
    private static String ANNOTATION_PARAMETERS_MIN = "min";
    private static String ANNOTATION_PARAMETERS_LENGTH = "length";
    private static String ANNOTATION_PARAMETERS_GROUPS = "groups";

    //Hardcoded report values
    private static String VAL_JSORCE_TYPE_ENTITY = "Entity";
    private static String VAL_XSORCE_TYPE_CVALIDATIONS = "Constraint validations";


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
        srcFiles.stream().forEach(item -> result.putInNewRow(FilesListReportColumns.COL_FILE.getCaption(), item));
        return result;
    }

    private static TableWithNamedCols createSummaryReportBody(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = new TableWithNamedCols();

        result.putValue(0, SummaryReportColumns.COL_FILES.getCaption(), "Java files");
        result.putValue(0, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getJavaFiles().size()));

        result.putValue(1, SummaryReportColumns.COL_FILES.getCaption(), "XML files");
        result.putValue(1, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getXmlFiles().size()));

        result.putValue(2, SummaryReportColumns.COL_FILES.getCaption(), "Properties files");
        result.putValue(2, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getPropertyFiles().size()));

        result.putValue(3, SummaryReportColumns.COL_FILES.getCaption(), "Unknown files");
        result.putValue(3, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getUnknownFiles().size()));

        result.putValue(4, SummaryReportColumns.COL_FILES.getCaption(), "Error files");
        result.putValue(4, SummaryReportColumns.COL_COUNT.getCaption(), String.valueOf(sourceFilesReader.getErrorFiles().size()));
        return result;
    }

    private static TableWithNamedCols createRulesReportEmptyColumns() {
        TableWithNamedCols result = new TableWithNamedCols();
        for (RulesReportColumns col : RulesReportColumns.values()) {
            result.addColumn(col.getCaption());
        }
        return result;
    }

    private static void populateRulesJava(TableWithNamedCols report, SourceFilesReader sourceFilesReader) {
        sourceFilesReader.getJavaFiles().stream().forEach(item -> {
            populateRulesReportAnnotation(report, sourceFilesReader.getPropertyFiles(), item);
        });
    }

    private static void populateRulesXml(TableWithNamedCols report, SourceFilesReader sourceFilesReader) {
        sourceFilesReader.getXmlFiles().stream().forEach(item -> {
            populateRulesReportXml(report, sourceFilesReader.getPropertyFiles(), item);
        });
    }


    private static void populateRulesReportAnnotation(TableWithNamedCols report, List<SourcePropertyFile> pFiles, SourceJavaFile jFile) {
        jFile.getAnnotations().stream().forEach(item -> {
            //File part
            int row = report.putInNewRow(RulesReportColumns.COL_REPO.getCaption(), jFile.getPartOfProduct());
            report.putValue(row, RulesReportColumns.COL_SOURCE.getCaption(), FileHelper.getFileName(jFile.getFileName()));
            report.putValue(row, RulesReportColumns.COL_CONTEXT.getCaption(), jFile.getClassName());
            report.putValue(row, RulesReportColumns.COL_PACKAGE.getCaption(), jFile.getPackageValue());
            report.putValue(row, RulesReportColumns.COL_TYPE.getCaption(), getJSourceType(jFile));

            //Annotation part
            if (item.getVariable().length() < 1) {
                report.putValue(row, RulesReportColumns.COL_APPLIED_TO.getCaption(), item.getMethod());
            } else {
                report.putValue(row, RulesReportColumns.COL_APPLIED_TO.getCaption(), item.getVariable());
            }
            report.putValue(row, RulesReportColumns.COL_CODE.getCaption(), item.getAnnotation());
            report.putValue(row, RulesReportColumns.COL_ANNOTATION.getCaption(), item.getRawValue());
            //Parse annotation parameters
            report.putValue(row, RulesReportColumns.COL_ERROR_CODE.getCaption(), fixErrorMessage(item.getParameterValues(ANNOTATION_PARAMETERS_MESSAGE)));
            report.putValue(row, RulesReportColumns.COL_ERROR_MESSAGE.getCaption(), findFirstInProperties(pFiles, fixErrorMessage(item.getParameterValues(ANNOTATION_PARAMETERS_MESSAGE))));
            report.putValue(row, RulesReportColumns.COL_PARAM_MIN.getCaption(), item.getParameterValues(ANNOTATION_PARAMETERS_MIN));
            report.putValue(row, RulesReportColumns.COL_PARAM_MAX.getCaption(), item.getParameterValues(ANNOTATION_PARAMETERS_MAX));
            report.putValue(row, RulesReportColumns.COL_PARAM_LENGTH.getCaption(), item.getParameterValues(ANNOTATION_PARAMETERS_LENGTH));
            report.putValue(row, RulesReportColumns.COL_PARAM_GROUP_COND.getCaption(), item.getParameterValues(ANNOTATION_PARAMETERS_GROUPS));
        });
    }


    private static void populateRulesReportXml(TableWithNamedCols report, List<SourcePropertyFile> pFiles, SourceXmlFile xFile) {
        if (xFile.getContentType() == SourceFileContentTypeXML.CONSTRAINT_VALIDATION_RULES) {
            xFile.getXmlConstraintValidations().stream().forEach(item -> {
                //File part
                int row = report.putInNewRow(RulesReportColumns.COL_REPO.getCaption(), xFile.getPartOfProduct());
                report.putValue(row, RulesReportColumns.COL_SOURCE.getCaption(), FileHelper.getFileName(xFile.getFileName()));
                report.putValue(row, RulesReportColumns.COL_TYPE.getCaption(), getXmlSourceType(xFile));

                //Rules part
                report.putValue(row, RulesReportColumns.COL_CONTEXT.getCaption(), item.getContext());
                report.putValue(row, RulesReportColumns.COL_APPLIED_TO.getCaption(), item.getApplyedTo());
                report.putValue(row, RulesReportColumns.COL_CODE.getCaption(), "");
                report.putValue(row, RulesReportColumns.COL_ERROR_CODE.getCaption(), item.getErrorMessage());
                if (item.getErrorMessage().length() > 0) {
                    report.putValue(row, RulesReportColumns.COL_ERROR_MESSAGE.getCaption(), findFirstInProperties(pFiles, item.getErrorMessage()));
                } else {
                    report.putValue(row, RulesReportColumns.COL_ERROR_MESSAGE.getCaption(), "");
                }
                report.putValue(row, RulesReportColumns.COL_ANNOTATION.getCaption(), "");
            });
        }
    }

    private static String getJSourceType(SourceJavaFile jFile) {
        if (jFile.isEntity()) {
            return VAL_JSORCE_TYPE_ENTITY;
        }
        return "";
    }

    private static String getXmlSourceType(SourceXmlFile xFile) {
        if (xFile.isConstraintValidation()) {
            return VAL_XSORCE_TYPE_CVALIDATIONS;
        }
        return "";
    }

    private static String findFirstInProperties(List<SourcePropertyFile> pFiles, String key) {
        for (SourcePropertyFile pF : pFiles) {
            if (pF.isKeyPresent(key)) {
                return pF.getProperty(key).getValue();
            }
        }
        return "";
    }

    private static String fixErrorMessage(String errMsg) {
        return errMsg.replace("{", "").replace("}", "");
    }
}
