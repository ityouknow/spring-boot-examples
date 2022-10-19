package com.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E://temp//";

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
            byte[] bytes = file.getBytes();
            Path dir = Paths.get(UPLOADED_FOLDER);
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            // Create parent dir if not exists
            if(!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Server throw IOException");
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}