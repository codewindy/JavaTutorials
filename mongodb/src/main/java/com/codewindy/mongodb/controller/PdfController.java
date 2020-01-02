//package com.codewindy.mongodb.controller;
//
//import cn.hutool.core.io.FileUtil;
//import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
//import com.itextpdf.text.DocumentException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.net.URLEncoder;
//import java.util.List;
//
///**
// * @author: jkwindy
// * @Date: 2019/01/09
// */
//@RestController
//public class PdfController {
//
//    @Value("${PDF_FONT}")
//    private String PDF_FONT;
//
//    @RequestMapping(value = "/downloadPDF")
//    public void downloadPDF(HttpServletResponse response) throws IOException, DocumentException {
//        //1. 渲染freemaker获取html
//
//
//        //2. 根据html 生成pdf ps： 需要提前安装服务器对应的wkhtmltopdf.org
//        Pdf pdf = new Pdf();
//
//        //pdf.addPageFromString("<html><head><meta charset=\"utf-8\"></head><h1>test for wkhtmltopdf function using java </h1></html>");
//        pdf.addPageFromUrl("https://www.voidtools.com/");
//
//        // Add a Table of Contents
//        //pdf.addToc();
//
//        // The `wkhtmltopdf` shell command accepts different types of options such as global, page, headers and footers, and toc. Please see `wkhtmltopdf -H` for a full explanation.
//        // All options are passed as array, for example:
//               // pdf.addParam(new Param("--no-footer-line"), new Param("--header-html", "file:///header.html"));
//              //  pdf.addParam(new Param("--enable-javascript"));
//        // Add styling for Table of Contents
//               // pdf.addTocParam(new Param("--xsl-style-sheet", "my_toc.xsl"));
//
//        // Save the PDF
//        String filePath="/tmp/a.pdf";
//        try {
//            pdf.saveAsDirect(filePath);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //3. 下载文件
//        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
//        try {
//
//            File file = new File(filePath);
//            FileInputStream fileInputStream = new FileInputStream(file);
//            OutputStream outputStream = response.getOutputStream();
//            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
//            //强制浏览器下载 fileName 转utf-8
//            String fileName = file.getName();
//            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName="+  fileName +";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8"));
//            outputStream.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
