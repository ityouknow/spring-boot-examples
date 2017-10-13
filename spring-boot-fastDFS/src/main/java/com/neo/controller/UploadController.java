package com.neo.controller;

import com.neo.config.Configurations;
import com.neo.fastdfs.FastDFSClient;
import com.neo.fastdfs.FastDFSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private Configurations configuration;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            String name=file.getOriginalFilename();
            String ext = name.substring(name.lastIndexOf(".") + 1);
            String path=saveFile(file,name,ext);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

            redirectAttributes.addFlashAttribute("path",
                    "file path url '" + path + "'");

        } catch (Exception e) {
            logger.error("upload file failed",e);
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }



    public String saveFile(MultipartFile multipartFile, String fileName, String ext){
        String path="";
        String[] fileAbsolutePath={};
        try {
            byte[] file_buff = null;
            InputStream inputStream=multipartFile.getInputStream();
            if(inputStream!=null){
                int len1 = inputStream.available();
                file_buff = new byte[len1];
                inputStream.read(file_buff);
            }
            inputStream.close();
            FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
            try {
                fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (fileAbsolutePath==null) {
                System.out.println("upload file failed,please upload again!");
            }
            path=configuration.getFdfsUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}