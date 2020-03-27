package com.codewindy.mongodb.controller;

/**
 * @author jkwindy@126.com
 * @date 2019-05-24 11:01
 */

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = System.getProperty("user.dir");

    @GetMapping("/")
    public String index() {

        return "index.html";
    }

    @PostMapping("/upload") //
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();

            HttpHeaders requestHeaders = request.getRequestHeaders();

            List<MultipartFile> files = request.getFiles("file");

            /*Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);*/
            OutputStream outputStream=new FileOutputStream(file.getOriginalFilename());
            IOUtils.write(bytes,outputStream);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }


    @PostMapping("/uploadMulti") //
    public String multiFileUpload(@RequestParam("file") MultipartFile[] file,
                                   RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        if (file.length==0) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            HttpHeaders requestHeaders = request.getRequestHeaders();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}