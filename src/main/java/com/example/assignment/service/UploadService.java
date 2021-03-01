package com.example.assignment.service;

import com.example.assignment.model.User;
import com.example.assignment.repository.UploadRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {

    @Autowired
    private UploadRepo uploadRepo;


    public List<User> saveCsvData(MultipartFile file) throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        ArrayList<User> users = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

        for (CSVRecord record : records) {

            User user = new User();
            user.setId(Long.parseLong(record.get("id")));
            user.setEmail(record.get("email"));
            user.setName(record.get("name"));
            users.add(user); // adding to arraylist (in case of caching)
            uploadRepo.save(user);// saving to my database

        }
        return users;
    }
}
