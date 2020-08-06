package com.eis.conv.mapping.srcHandler.source.sourceObjects;


import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.SourceJavaFile;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//TODO: not completed
public class JFileHandlerTest {

    private final String fileName = "C:\\111\\jC.java";

    private final String javaFileContent = "package com.eis.conv.mapping.srcHandler.sourceParser.java;\n" +
            "\n" +
            "import com.github.javaparser.ast.CompilationUnit;\n" +
            "import com.github.javaparser.utils.CodeGenerationUtils;\n" +
            "import com.github.javaparser.utils.SourceRoot;\n" +
            "import java.io.File;\n" +
            "\n" +
            "@CanInvoke\n" +
            "@CanExtend(integer=0, fraction=99)\n" +
            "@Entity\n" +
            "public class JavaFileParser {\n" +
            "\t\n" +
            "\t@Valid\n" +
            "    private Address address, aaaddd;\n" +
            "\t\n" +
            "\t@Long (min=2, max=10)\n" +
            "    private long lllXX;\n" +
            "\tprivate long EEE;\n" +
            "\n" +
            "    public CompilationUnit XXXYYY(File file) {\n" +
            "        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(JavaFileParser.class).resolve(file.getPath()));\n" +
            "        CompilationUnit cu = sourceRoot.parse(\"\", file.getName());\n" +
            "        return cu;\n" +
            "    }\n" +
            "\t\n" +
            "\t@Digits(integer=3, fraction=6)\n" +
            "\tpublic String getSdsdsds() {\n" +
            "\t}\n" +
            "}";

    @Test
    public void loadFromFileTest() throws IOException {
        SourceJavaFile jFileAnnotations = JFileHandler.loadFromFile("");
        assertThat(jFileAnnotations.getFileName()).isEqualTo("");
    }

    @Test
    public void loadFromStringTest() throws FileNotFoundException {
        SourceJavaFile jFileAnnotations = JFileHandler.loadFromString(fileName, javaFileContent);

        assertThat(jFileAnnotations.getFileName()).isEqualTo(fileName);
        assertThat(jFileAnnotations.getAnnotations().size()).isEqualTo(7);

    }

    @Test
    public void loadFromFileListTest() throws IOException {
        List<SourceJavaFile> result = JFileHandler.loadFromFileList(new ArrayList<String>());
        assertThat(result.size()).isEqualTo(0);
    }
}
