package com.eis.conv.mapping.srcHandler.sourceParser.java;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.nio.file.Paths;

public class JavaFileParser {
    public CompilationUnit parse(File file) {
        SourceRoot sourceRoot = new SourceRoot(Paths.get(file.getParent()));
        CompilationUnit cu = sourceRoot.parse("", file.getName());
        return cu;
    }

}
