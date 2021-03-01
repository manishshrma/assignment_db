package com.example.assignment.controller;

import com.example.assignment.model.User;
import com.example.assignment.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Controller
public class UploadlerController {

    @Autowired
    private UploadService uploadService;



    public void homepage()
    {


    }



    @GetMapping("/importfile")
    public String index() {
        return "view/Upload";
    }
    @PostMapping("/uploaded_csv")
    public String testCsv(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
        } else {
            System.out.println("Test working................................................");
            List<User> users = uploadService.saveCsvData(file);
            model.addAttribute("users", users);
            model.addAttribute("status", true);
        }
        return "view/CsvView";
    }
}
