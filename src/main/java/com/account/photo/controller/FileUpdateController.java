package com.account.photo.controller;

import com.account.photo.entity.Photo;
import com.account.photo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@RequestMapping("/upload")
@Controller
public class FileUpdateController {
    @Value("${upload.photo}")
    private String uploadPhoto;
    @Autowired
    private PhotoRepository photoRepository;

    @PostMapping("/photo")
    public String photo(MultipartFile file) throws IOException {
        String normalName = file.getOriginalFilename().replaceAll(" +", "_");
        String name = URLEncoder.encode(UUID.randomUUID() + "_" + normalName, StandardCharsets.UTF_8);
        Files.copy(file.getInputStream(), Path.of(uploadPhoto + "/" + name));
        photoRepository.save(new Photo(file.getOriginalFilename(), name));
        return "redirect:/?path=/upload/photo/" + name;
    }
}
