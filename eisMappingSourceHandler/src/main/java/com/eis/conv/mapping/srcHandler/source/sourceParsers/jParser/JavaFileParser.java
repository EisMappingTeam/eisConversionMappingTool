package com.eis.conv.mapping.srcHandler.source.sourceParsers.jParser;

import com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects.FileAnnotations;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects.JAnnotation;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileNotFoundException;


public class JavaFileParser {


//    public FileAnnotations parse(File file) throws FileNotFoundException {
//        FileAnnotations fileAnnotations = new FileAnnotations();
//
//        JavaParser jp = new JavaParser();
//
//        CompilationUnit cu = jp.parse(file).getResult().get();
//        cu.accept(new ClassHeaderVisitor(fileAnnotations), ""); //Load class annotations
//        cu.accept(new ClassVisitor(fileAnnotations), "");       //Load class variables annotations
//        cu.accept(new MethodVisitor(fileAnnotations), "");      //Load method annotations
//
//        return fileAnnotations;
//    }

    //https://github.com/javaparser/javaparser/issues/1336
    public FileAnnotations parse(String fileContent) throws FileNotFoundException {
        FileAnnotations fileAnnotations = new FileAnnotations();

        JavaParser jp = new JavaParser();

        CompilationUnit cu = jp.parse(fileContent).getResult().get();
        cu.accept(new ClassHeaderVisitor(fileAnnotations), ""); //Load class annotations
        cu.accept(new ClassVisitor(fileAnnotations), "");       //Load class variables annotations
        cu.accept(new MethodVisitor(fileAnnotations), "");      //Load method annotations

        return fileAnnotations;
    }


    private static class AnnotationPropertiesVisitor extends VoidVisitorAdapter<String> {
        FileAnnotations fileAnnotations;

        private AnnotationPropertiesVisitor(FileAnnotations _fileAnnotations) {
            fileAnnotations = _fileAnnotations;
        }

        @Override
        public void visit(NormalAnnotationExpr n, String arg) {
            //Method annotations
            //System.out.println("Method: " + arg + ", Annotation: " + n.getName());
            JAnnotation jAnnotation = new JAnnotation();
            jAnnotation.setClassLevel(false);
            jAnnotation.setAnnotation(n.getName().toString());
            jAnnotation.setMethod(arg);
            jAnnotation.setRawValue(n.toString());

            fileAnnotations.getAnnotations().add(jAnnotation);
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
            //System.out.println("       MV: Method: " + n.getName());
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
            //Class variable annotations
            for (FieldDeclaration ff : n.getFields()) {
                for (VariableDeclarator vd : ff.getVariables()) {
                    for (AnnotationExpr ae : ff.getAnnotations()) {
                        //System.out.println("VARIABLE DECL: " + vd.getName() + "  Annotation " + ae.toString());
                        JAnnotation jAnnotation = new JAnnotation();
                        jAnnotation.setClassLevel(false);
                        jAnnotation.setVariable(vd.getName().asString());
                        jAnnotation.setAnnotation(ae.getName().asString());
                        jAnnotation.setRawValue(ae.toString());

                        fileAnnotations.getAnnotations().add(jAnnotation);
                    }
                }
            }
            super.visit(n, arg);
        }
    }


    private static class ClassHeaderVisitor extends VoidVisitorAdapter<String> {
        FileAnnotations fileAnnotations;

        private ClassHeaderVisitor(FileAnnotations _fileAnnotations) {
            fileAnnotations = _fileAnnotations;
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, String arg) {
            //Class header annotations
            for (AnnotationExpr ae : n.getAnnotations()) {
                //System.out.println("Annotation CLASS: " + ae.getName());
                JAnnotation jAnnotation = new JAnnotation();
                jAnnotation.setClassLevel(true);
                jAnnotation.setAnnotation(ae.getName().toString());
                jAnnotation.setRawValue(ae.toString());

                fileAnnotations.getAnnotations().add(jAnnotation);
            }
            super.visit(n, arg);
        }
    }


}