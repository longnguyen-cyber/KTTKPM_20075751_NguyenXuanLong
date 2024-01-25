package com.kuga;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.kuga.common.IteratorFile;
import com.kuga.common.Reflection;
import com.kuga.consts.IConstant;
import com.kuga.models.Account;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Câu 1
//        processFile();
        //Câu 2
		processFolder();
        //Câu 3
        Account account = new Account();
//        Reflection.reflect(account.getClass());

    }

    private static void processFolder() {
        File folder = new File(IConstant.FOLDER_TEST);
        IteratorFile.processFiles(folder);

    }

    private static void processFile() throws FileNotFoundException {
        ParseResult<CompilationUnit> result = null;
        FileInputStream in = new FileInputStream(IConstant.FILE_TEST);
        try {
            JavaParser parser = new JavaParser();
            result = parser.parse(in);
            Optional<CompilationUnit> optional = result.getResult();
            if (optional.isPresent()) {
                CompilationUnit cUnit = optional.get();
                System.out.println("INFORMATION FIELDS OF FILE: " + IConstant.FILE_TEST.split("/")[IConstant.FILE_TEST.split("/").length - 1]);
               IteratorFile.getField(cUnit);
                System.out.println("\n");
                System.out.println("INFORMATION METHODS OF FILE: " + IConstant.FILE_TEST.split("/")[IConstant.FILE_TEST.split("/").length - 1]);
                IteratorFile.getMethod(cUnit);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


}
