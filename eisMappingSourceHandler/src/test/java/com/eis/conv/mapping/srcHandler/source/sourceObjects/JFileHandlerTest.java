package com.eis.conv.mapping.srcHandler.source.sourceObjects;


import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.types.SourceFileContentTypeJava;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects.JAnnotation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class JFileHandlerTest {
    private final String DIR = "src/test/resources/source";
    private final String FILE_NAME = "TestAnnotatedClass.java";

    private final String J_PACKAGE = "com.eis.conv.mapping.srcHandler.sourceParser.java";
    private final String ANNOTATION_CANINVOKE = "CanInvoke";
    private final String ANNOTATION_DIGITS = "Digits";

    @Test
    public void loadFromFileTest() throws IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(FILE_NAME);

        SourceJavaFile jFileAnnotations = JFileHandler.loadFromFile(file.toString());
        assertThat(jFileAnnotations.getPackageValue()).isEqualTo(J_PACKAGE);
        assertThat(jFileAnnotations.getAnnotations().size()).isEqualTo(7);
        assertThat(jFileAnnotations.getContentType()).isEqualTo(SourceFileContentTypeJava.ENTITY);

        JAnnotation canInvoke = jFileAnnotations.getAnnotationByName(ANNOTATION_CANINVOKE);
        JAnnotation digits = jFileAnnotations.getAnnotationByName(ANNOTATION_DIGITS);
        assertThat(canInvoke).isNotEqualTo(null);
        assertThat(canInvoke.isClassLevel()).isEqualTo(true);
        assertThat(canInvoke.isMethodLevel()).isEqualTo(false);
        assertThat(digits).isNotEqualTo(null);
        assertThat(digits.isClassLevel()).isEqualTo(false);
        assertThat(digits.isMethodLevel()).isEqualTo(true);
    }

}
