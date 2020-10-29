package com.eis.conv.mapping.srcHandler.source.parsers;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.srcHandler.source.files.java.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.files.java.jObjects.JAnnotation;
import com.eis.conv.mapping.srcHandler.source.files.java.jObjects.JVariableDeclaration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaFileParserTest {

    private final String DIR = "src/test/resources/source";
    private final String VAR_DECL_FILE_NAME = "TestDataTypeFieldLengthConstants.java";
    private final String ANNOTATIONS_FILE_NAME = "TestBusinessCustomer.java";

    //Var decl
    private final String CODE_TYPE_FIELD_LENGHT_NAME = "CODE_TYPE_FIELD_LENGHT";
    private final String CODE_TYPE_FIELD_LENGHT_VALUE = "20";

    private final String ERROR_LOG_LENGTH_NAME = "ERROR_LOG_LENGTH";
    private final String ERROR_LOG_LENGTH_VALUE = "2000";

    //Annotations
    private final String ANN_SIC_OR_NAICS_REQUIRED = "SicOrNaicsRequired";


    @Test
    public void parseVariableDeclarationTest() throws IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(VAR_DECL_FILE_NAME);
        String fileContent = FileHelper.getFileAsSting(file.toString());

        JavaFileParser javaFileParser = new JavaFileParser();
        SourceJavaFile sourceJavaFile = javaFileParser.parse(fileContent);

        assertThat(sourceJavaFile.getVariables().size()).isEqualTo(15);

        JVariableDeclaration vCodeTypeFieldLength = sourceJavaFile.getVariableByName(CODE_TYPE_FIELD_LENGHT_NAME);
        assertThat(vCodeTypeFieldLength).isNotEqualTo(null);
        assertThat(vCodeTypeFieldLength.getVariable()).isEqualTo(CODE_TYPE_FIELD_LENGHT_NAME);
        assertThat(vCodeTypeFieldLength.getInitializationValue()).isEqualTo(CODE_TYPE_FIELD_LENGHT_VALUE);

        JVariableDeclaration vErrorLogLength = sourceJavaFile.getVariableByName(ERROR_LOG_LENGTH_NAME);
        assertThat(vErrorLogLength).isNotEqualTo(null);
        assertThat(vErrorLogLength.getVariable()).isEqualTo(ERROR_LOG_LENGTH_NAME);
        assertThat(vErrorLogLength.getInitializationValue()).isEqualTo(ERROR_LOG_LENGTH_VALUE);
    }

    @Test
    public void parseAnnotationsTest() throws IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(ANNOTATIONS_FILE_NAME);
        String fileContent = FileHelper.getFileAsSting(file.toString());

        JavaFileParser javaFileParser = new JavaFileParser();
        SourceJavaFile sourceJavaFile = javaFileParser.parse(fileContent);

        assertThat(sourceJavaFile.getVariables().size()).isEqualTo(14);
        assertThat(sourceJavaFile.getAnnotations().size()).isEqualTo(57);

        JAnnotation sicOrNaics = sourceJavaFile.getAnnotationByName(ANN_SIC_OR_NAICS_REQUIRED);
        assertThat(sicOrNaics).isNotEqualTo(null);
        assertThat(sicOrNaics.getAnnotation()).isEqualTo(ANN_SIC_OR_NAICS_REQUIRED);
        assertThat(sicOrNaics.getVariable()).isEqualTo("details");
        assertThat(sicOrNaics.getParameters().size()).isEqualTo(2);
        assertThat(sicOrNaics.getParameterValues("message")).isEqualTo("{crm00092}");
    }

}