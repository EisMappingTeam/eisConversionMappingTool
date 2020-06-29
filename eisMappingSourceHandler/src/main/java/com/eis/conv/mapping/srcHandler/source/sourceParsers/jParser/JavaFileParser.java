package com.eis.conv.mapping.srcHandler.source.sourceParsers.jParser;

import com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects.FileAnnotations;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileNotFoundException;


public class JavaFileParser {

    //https://github.com/javaparser/javaparser/issues/1336
    public CompilationUnit parse(File file) throws FileNotFoundException {
        FileAnnotations fileAnnotations = new FileAnnotations();

        JavaParser jp = new JavaParser();
        CompilationUnit cu = jp.parse(file).getResult().get();
        cu.accept(new ClassVisitor(fileAnnotations), "");
        cu.accept(new MethodVisitor(fileAnnotations), "");

        return cu;
    }

    private static class AnnotationPropertiesVisitor extends VoidVisitorAdapter<String> {
        FileAnnotations fileAnnotations;

        private AnnotationPropertiesVisitor(FileAnnotations _fileAnnotations) {
            fileAnnotations = _fileAnnotations;
        }

        @Override
        public void visit(NormalAnnotationExpr n, String arg) {
            System.out.println("Method: " + arg + ", Annotation: " + n.getName());
            super.visit(n.getPairs(), arg);
        }
    }


    private static class MethodVisitor extends VoidVisitorAdapter<String> {
        FileAnnotations fileAnnotations;

        private MethodVisitor(FileAnnotations _fileAnnotations) {
            fileAnnotations = _fileAnnotations;
        }

        @Override
        public void visit(MethodDeclaration n, String arg) {
            System.out.println("MV: Method: " + n.getName());
            n.accept(new AnnotationPropertiesVisitor(fileAnnotations), n.getName().toString());
            super.visit(n, arg);
        }
    }


    //Sample with parameters:
    //https://stackoverflow.com/questions/5410193/get-class-annotations-from-java-source-file
    private static class ClassVisitor extends VoidVisitorAdapter<String> {
        FileAnnotations fileAnnotations;

        private ClassVisitor(FileAnnotations _fileAnnotations) {
            fileAnnotations = _fileAnnotations;
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, String arg) {
            for (FieldDeclaration ff : n.getFields()) {

                for (VariableDeclarator vd : ff.getVariables()) {
                    System.out.println("DECL:" + vd.getName());
                    for (AnnotationExpr ae : ff.getAnnotations()) {
                        System.out.println("DECL variable: " + vd.getName() + "  Annotation " + ae.toString());
                    }
                }
            }
            super.visit(n, arg);
        }
    }

}