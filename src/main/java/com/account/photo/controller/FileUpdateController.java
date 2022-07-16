package com.account.photo.controller;

import com.account.photo.model.FileUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/upload")
@Controller
public class FileUpdateController {
    @Autowired
    private FileUpdateModel fileUpdateModel;

    @PostMapping("/n/photo")
    public String photo(MultipartFile file) throws IOException {
        return fileUpdateModel.photo(file);
    }
}
