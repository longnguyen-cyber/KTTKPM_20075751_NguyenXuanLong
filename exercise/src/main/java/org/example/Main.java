package org.example;

import jdepend.xmlui.JDepend;

import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        JDepend jdepend = new JDepend(new PrintWriter("reports/report.xml"));
        jdepend.addDirectory("D:\\code\\KTTKPM_20075751_NguyenXuanLong\\Library-Assistant");
        jdepend.analyze();
        System.out.println("Analysis completed");
    }
}