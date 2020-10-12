package com.eis.conv.mapping.srcHandler.output.reports;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.eis.conv.mapping.srcHandler.output.obj.RulesReportColumns;
import com.eis.conv.mapping.srcHandler.output.obj.TableWithNamedCols;
import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.SourcePropertyFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.SourceXmlConstraintFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.types.ContentTypeXML;
import com.eis.conv.mapping.srcHandler.source.entities.jObjects.JVariableDeclaration;

import java.util.List;

public final class ReportHandlerRules {

    //Annotation parameters
    private static String ANNOTATION_PARAMETERS_MESSAGE = "message";
    private static String ANNOTATION_PARAMETERS_MAX = "max";
    private static String ANNOTATION_PARAMETERS_MIN = "min";
    private static String ANNOTATION_PARAMETERS_LENGTH = "length";
    private static String ANNOTATION_PARAMETERS_GROUPS = "groups";
    private static String ANNOTATION_PARAMETERS_REGEXP = "regexp";

    //Hardcoded report values
    private static String VAL_JSORCE_TYPE_ENTITY = "Entity";
    private static String VAL_XSORCE_TYPE_CVALIDATIONS = "Constraint validations";


    public static TableWithNamedCols createRulesReport(SourceFilesReader sourceFilesReader) {
        TableWithNamedCols result = createRulesReportEmptyColumns();
        populateRulesJava(result, sourceFilesReader);
        populateRulesXml(result, sourceFilesReader);
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
            populateRulesReportAnnotation(report, sourceFilesReader, item);
        });
    }

    private static void populateRulesXml(TableWithNamedCols report, SourceFilesReader sourceFilesReader) {
        sourceFilesReader.getXmlFiles().stream().forEach(item -> {
            populateRulesReportXml(report, sourceFilesReader.getPropertyFiles(), item);
        });
    }


    private static void populateRulesReportAnnotation(TableWithNamedCols report, SourceFilesReader sourceFilesReader, SourceJavaFile jFile) {
        jFile.getAnnotations().stream().forEach(item -> {
            //File part
            int row = report.putInNewRow(RulesReportColumns.COL_REPO.getCaption(), jFile.getPartOfProduct());
            report.putValue(row, RulesReportColumns.COL_SOURCE.getCaption(), FileHelper.getFileName(jFile.getFileName()));
            report.putValue(row, RulesReportColumns.COL_CONTEXT.getCaption(), jFile.getClassName());
            report.putValue(row, RulesReportColumns.COL_PACKAGE.getCaption(), jFile.getPackageValue());
            report.putValue(row, RulesReportColumns.COL_COND_DEFINED_IN.getCaption(), getJSourceType(jFile));

            //Annotation part
            if (item.getVariable().length() < 1) {
                report.putValue(row, RulesReportColumns.COL_APPLIED_TO.getCaption(), parseMethodSetGetToVariable(item.getMethod()));
            } else {
                report.putValue(row, RulesReportColumns.COL_APPLIED_TO.getCaption(), item.getVariable());
            }
            report.putValue(row, RulesReportColumns.COL_CODE.getCaption(), item.getAnnotation());
            report.putValue(row, RulesReportColumns.COL_ANNOTATION.getCaption(), item.getRawValue());
            //Parse annotation parameters
            report.putValue(row, RulesReportColumns.COL_ERROR_CODE.getCaption(), fixErrorMessage(item.getParameterValues(ANNOTATION_PARAMETERS_MESSAGE)));
            report.putValue(row, RulesReportColumns.COL_ERROR_MESSAGE.getCaption(), findFirstInProperties(sourceFilesReader.getPropertyFiles(), fixErrorMessage(item.getParameterValues(ANNOTATION_PARAMETERS_MESSAGE))));

            //Constraints
            report.putValue(row, RulesReportColumns.COL_PARAM_MIN.getCaption(), getConstraintValue(item.getParameterValues(ANNOTATION_PARAMETERS_MIN), sourceFilesReader.getJavaFiles()));
            report.putValue(row, RulesReportColumns.COL_PARAM_MAX.getCaption(), getConstraintValue(item.getParameterValues(ANNOTATION_PARAMETERS_MAX), sourceFilesReader.getJavaFiles()));
            report.putValue(row, RulesReportColumns.COL_PARAM_LENGTH.getCaption(), getConstraintValue(item.getParameterValues(ANNOTATION_PARAMETERS_LENGTH), sourceFilesReader.getJavaFiles()));


            report.putValue(row, RulesReportColumns.COL_PARAM_GROUP_COND.getCaption(), item.getParameterValues(ANNOTATION_PARAMETERS_GROUPS));
            report.putValue(row, RulesReportColumns.COL_PARAM_REGEXP.getCaption(), item.getParameterValues(ANNOTATION_PARAMETERS_REGEXP));
        });
    }


    private static void populateRulesReportXml(TableWithNamedCols report, List<SourcePropertyFile> pFiles, SourceXmlConstraintFile xFile) {
        if (xFile.getContentType() == ContentTypeXML.CONSTRAINT_VALIDATION_RULES) {
            xFile.getXmlConstraintValidations().stream().forEach(item -> {
                //File part
                int row = report.putInNewRow(RulesReportColumns.COL_REPO.getCaption(), xFile.getPartOfProduct());
                report.putValue(row, RulesReportColumns.COL_SOURCE.getCaption(), FileHelper.getFileName(xFile.getFileName()));
                report.putValue(row, RulesReportColumns.COL_COND_DEFINED_IN.getCaption(), getXmlSourceType(xFile));

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

    private static String getXmlSourceType(SourceXmlConstraintFile xFile) {
        if (xFile.isConstraintValidation()) {
            return VAL_XSORCE_TYPE_CVALIDATIONS;
        }
        return "";
    }

    private static String findFirstInProperties(List<SourcePropertyFile> pFiles, String key) {
        for (SourcePropertyFile pF : pFiles) {
            if (pF.isKeyPresent(key)) {
                String result = pF.getProperty(key).getValue();
                return result;
            }
        }
        return "";
    }

    private static String fixErrorMessage(String errMsg) {
        return errMsg.replace("{", "").replace("}", "");
    }

    private static String parseMethodSetGetToVariable(String methodName) {
        String result = methodName;
        if (methodName.length() > 3) {
            String prefix = StringHelper.getLeft(methodName, 3);
            if (prefix.equals("set") || prefix.equals("get")) {
                result = StringHelper.getRight(result, result.length() - 3);
                result = StringHelper.lowerFirst(result);
            }
        }
        return result;
    }

    private static String getConstraintValue(String value, List<SourceJavaFile> jFiles) {
        if (value == null || isNumberConst(value) || value.length() < 1 || value.indexOf(".") < 0) {
            return value;
        }
        String jClassName = value.split("\\.")[0];
        String varName = value.split("\\.")[1];

        JVariableDeclaration variableDeclaration = getVarDeclaration(jClassName, varName, jFiles);
        if (variableDeclaration.getVariable().equals(varName)) {
            return variableDeclaration.getInitializationValue();
        }
        return value;
    }

    private static boolean isNumberConst(String constraintValue) {
        return StringHelper.isNumeric(constraintValue);
    }

    private static JVariableDeclaration getVarDeclaration(String className, String variableName, List<SourceJavaFile> jFiles) {
        for (SourceJavaFile jF : jFiles) {
            if (jF.getClassName().equals(className)) {

                for (JVariableDeclaration variableDeclaration : jF.getVariables()) {
                    if (variableDeclaration.getVariable().equals(variableName)) {
                        return variableDeclaration;
                    }
                }
            }
        }
        return new JVariableDeclaration();
    }


}
