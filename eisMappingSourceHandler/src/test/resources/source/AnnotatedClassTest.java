package com.eis.conv.mapping.srcHandler.sourceParser.java;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;
import java.io.File;

@CanInvoke
@CanExtend(integer=0, fraction=99)
@Entity
public class JavaFileParser {

    @Valid
    private Address address, aaaddd;

    @Long (min=2, max=10)
    private long lllXX;
    private long EEE;

    public CompilationUnit someName(File file) {
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(JavaFileParser.class).resolve(file.getPath()));
        CompilationUnit cu = sourceRoot.parse("", file.getName());
        return cu;
    }

    @Digits(integer=3, fraction=6)
    public String getSdsdsds() {
        int s;
    }
}