package com.codewindy.h2;


import cn.hutool.core.date.DateUtil;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class TestZip4j {

    public static void main(String[] args) throws ZipException {
        System.out.println("DateUtil.now() = " + DateUtil.now());
        ZipFile zipFile = new ZipFile("D:\\Music\\Movies.zip");
        zipFile.extractAll("D:\\Music\\movies");
        System.out.println("end = " + DateUtil.now());


    }
}
