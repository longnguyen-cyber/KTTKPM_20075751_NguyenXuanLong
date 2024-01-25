package com.kuga;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.*;
import com.kuga.common.DirExplorer;
import com.kuga.common.ScanProject;
import com.kuga.consts.IConstant;


public class CommonOperations {
    public static void listClasses(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            try {

                FileInputStream in = new FileInputStream(file);
                ParseResult<CompilationUnit> result = null;
                JavaParser parser = new JavaParser();
                result = parser.parse(in);
                Optional<CompilationUnit> optional = result.getResult();
                if (optional.isPresent()) {
                    CompilationUnit cUnit = optional.get();
                    PackageDeclaration packageDeclaration = cUnit.getPackageDeclaration().orElse(null);
                    ClassOrInterfaceDeclaration classOrInterfaceDeclaration = cUnit
                            .findFirst(ClassOrInterfaceDeclaration.class).orElse(null);
                    FieldDeclaration fieldDeclaration = cUnit.findFirst(FieldDeclaration.class).orElse(null);
                    List<FieldDeclaration> fields = cUnit.findAll(FieldDeclaration.class);
                    MethodDeclaration methodDeclaration = cUnit.findFirst(MethodDeclaration.class).orElse(null);
                    List<MethodDeclaration> methods = cUnit.findAll(MethodDeclaration.class);
                    //exercise 1

//                    String regex = "com\\.kuga\\..*";
//                    if (packageDeclaration != null) {
//                        ScanProject.scanPack(packageDeclaration, null, path, regex);
//                    }
//                    //exercise 2
//                    if(classOrInterfaceDeclaration!= null) {
//                    	ScanProject.isNounStartWithUppercase(classOrInterfaceDeclaration, null, path);
//                    }
//                    //exercise 3
//                    String[] specialWords = {"@created-date", "@author"};
//                    if(classOrInterfaceDeclaration!= null) {
//                    	ScanProject.hasCommentSpecial(classOrInterfaceDeclaration, null, specialWords, path);
//                    }
//                    //exercise 4
//                    if(fieldDeclaration!= null) {
//                    	ScanProject.isNounStartWithLowercase(fieldDeclaration, null, path);
//                    }
//                    //exercise 5
//                    if (classOrInterfaceDeclaration != null && fields != null) {
//                        ScanProject.isConstant(fields, classOrInterfaceDeclaration, path, null);
//                    }
//                    //exercise 6
//                    if (methodDeclaration != null) {
//                        ScanProject.isVerbStartWithLowercase(methodDeclaration, null, path);
//                    }
//                    //exercise 7
                    if (methodDeclaration != null) {
                        ScanProject.hasCommentForMethods(methods, null, path);
                    }
                }
            } catch (Exception e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
    }
    public static void main(String[] args) {
        File projectDir = new File(IConstant.PRJ_PATH);
        listClasses(projectDir);
    }
}