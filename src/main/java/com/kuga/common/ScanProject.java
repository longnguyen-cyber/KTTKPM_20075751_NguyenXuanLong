package com.kuga.common;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScanProject extends VoidVisitorAdapter<Object> {
    //exercise 1
    public static void scanPack(PackageDeclaration n, Object arg, String path, String regex) {
        Pattern pattern = Pattern.compile(regex);
        String packName = n.getNameAsString();
        Matcher matcher = pattern.matcher(packName);
        if (!matcher.matches()) {
            System.err.println("The package in path " + path + " not have in folder " + regex);
        }
    }

    //exercise 2
    public static void isNounStartWithUppercase(ClassOrInterfaceDeclaration clazz, Object arg, String path) {
        String clsName = clazz.getNameAsString();
        String firstChar = clsName.charAt(0) + "";
        if (!firstChar.equals(firstChar.toUpperCase())) {
            System.err.println(
                    "The " + clsName + " class in " + path + " " + clazz.getBegin().get() + " is not start with first uppercase");
        }
    }

    //exercise 3
    public static void hasCommentSpecial(ClassOrInterfaceDeclaration clazz, Object arg, String[] specialWords, String path) {
        Optional<Comment> ocmt = clazz.getComment();
        if (ocmt.isEmpty()) {
            System.err.println("Invalid or missing class comment in " + clazz.getNameAsString() + " in path " + path + " in line " + clazz.getBegin().get());
        } else {
            Comment commentContent = ocmt.get();
            String content = commentContent.getContent();
            for (String specialWord : specialWords) {
                if (!content.contains(specialWord)) {
                    System.err.println("Invalid or missing" + specialWord + "class comment in " + clazz.getNameAsString() + " in path " + path + " in line " + commentContent.getBegin().get());
                }
            }
        }
    }

    //exercise 4
    public static void isNounStartWithLowercase(FieldDeclaration fieldDeclaration, Object arg, String path) {
        for (VariableDeclarator variable : fieldDeclaration.getVariables()) {
            String fieldName = variable.getName().asString();
            if (!Character.isLowerCase(fieldName.charAt(0)) && !fieldDeclaration.isStatic() && !fieldDeclaration.isFinal()) {
                System.err.println("The field '" + fieldName + "' in " + fieldDeclaration.getBegin().get() + " " + path
                        + " does not start with a lowercase letter");
            }
        }
    }

    //exercise 5
    public static void isConstant(List<FieldDeclaration> fieldDeclaration, ClassOrInterfaceDeclaration clazz, String path, Object arg) {
        for (FieldDeclaration field : fieldDeclaration) {
            for (VariableDeclarator variable : field.getVariables()) {
                String fieldName = variable.getName().asString();
                if (clazz.isInterface()) {
                    if (!fieldName.equals(fieldName.toUpperCase())) {
                        System.err.println("Invalid constant name '" + fieldName + "' in interface '"
                                + getEnclosingInterfaceName(field) + field.getBegin().get() + " " + path
                                + "'. Constants must be in uppercase.");
                    } else {
                        if (!Character.isLowerCase(fieldName.charAt(0)) && !field.isStatic() && !field.isFinal()) {
                            System.err.println("The field '" + fieldName + "' in " + field.getBegin().get() + " " + path
                                    + " does not start with a lowercase letter");
                        }
                    }

                } else {

                    if (Character.isUpperCase(fieldName.charAt(0)))
                        System.err.println("Invalid constant name '" + fieldName + "' in interface '"
                                + getEnclosingInterfaceName(field) + field.getBegin().get() + " " + path + "'. Constants must be in interface.");
                }
            }
        }

    }

    //exercise 6
    public static void isVerbStartWithLowercase(MethodDeclaration methodDeclaration, Object arg, String path) {
        String methodName = methodDeclaration.getNameAsString();
        if (!isLowerCaseVerb(methodName)) {
            System.err.println("Invalid method name '" + methodName + "' in class '"
                    + getEnclosingClassName(methodDeclaration) + methodDeclaration.getBegin().get() + " " + path
                    + "'. Method names must start with a lowercase verb.");
        }
    }

    //exercise 7
    public static void hasCommentForMethods(List<MethodDeclaration> methods, Object arg, String path) {
        for (MethodDeclaration method : methods) {
            if (!isExcludedMethod(method)) {
                // Check if the method has a comment
                if (method.getComment().isEmpty()) {
                    System.err.println("Missing comment for method '" + method.getNameAsString()
                            + "' in class '" + getEnclosingClassName(method) + path + " " + method.getBegin().get() + "'. Each method must have a comment describing its functionality.");
                }
            }
        }
    }

    private static String getEnclosingInterfaceName(FieldDeclaration fieldDeclaration) {
        TypeDeclaration<?> parentType = fieldDeclaration.findAncestor(TypeDeclaration.class).orElse(null);
        if (parentType != null && ((ClassOrInterfaceDeclaration) parentType).isInterface()) {
            return parentType.getNameAsString();
        }
        return "Unknown";
    }

    private static String getEnclosingClassName(MethodDeclaration methodDeclaration) {
        return methodDeclaration.findAncestor(ClassOrInterfaceDeclaration.class)
                .map(decl -> decl.getNameAsString()).orElse("Unknown");
    }

    private static boolean isLowerCaseVerb(String methodName) {
        return Character.isLowerCase(methodName.charAt(0));
    }

    private static boolean isExcludedMethod(MethodDeclaration methodDeclaration) {
        String methodName = methodDeclaration.getNameAsString();
        return methodName.equals("hashCode") || methodName.equals("equals") || methodName.equals("toString");
    }
}
