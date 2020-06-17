package com.eis.conv.mapping.srcHandler.sourceParser.java;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
//import com.github.javaparser.ast.visitor.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

public class JavaFileParser {
    public CompilationUnit parse(File file) throws FileNotFoundException {
        //SourceRoot sourceRoot = new SourceRoot(Paths.get(file.getParent()));
        //CompilationUnit cu = JavaParser.parse() sourceRoot.parse("", file.getName());
        JavaParser jp = new JavaParser();
        CompilationUnit cu  =jp.parse( file).getResult().get();
        cu.accept(  new ClassVisitor(),  new String());
        cu.accept(  new MethodVisitor(),  new String());


        return cu;
    }

    private static class AnnotationPropertiesVisitor extends VoidVisitorAdapter<String> {
        @Override
        public void visit(NormalAnnotationExpr n, String arg) {
            System.out.println("Method: " + arg + ", Annotation: " + n.getName());
            super.visit(n.getPairs(), arg);
        }
    }

    private static class MethodVisitor extends VoidVisitorAdapter<String> {
        @Override
        public void visit(MethodDeclaration n, String arg) {
            System.out.println("MV: Method: "  + n.getName());
            n.accept(new AnnotationPropertiesVisitor(), n.getName().toString());

            super.visit(n, arg);
        }
    }


    private static class ClassVisitor extends VoidVisitorAdapter<String> {
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
