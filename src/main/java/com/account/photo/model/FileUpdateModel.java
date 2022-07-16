package com.account.photo.model;

import com.account.photo.entity.Photo;
import com.account.photo.entity.User;
import com.account.photo.repository.PhotoRepository;
import com.account.photo.repository.UserRepository;
import com.account.photo.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.UUID;

@Component
public class FileUpdateModel {
    @Value("${upload.photo}")
    private String uploadPhoto;
    @Value("${upload.photo.url}")
    private String uploadPhotoUrl;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private UserRepository userRepository;

    public String photo(MultipartFile file) throws IOException {
        User user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        if(System.currentTimeMillis() - user.getLastPhotoPost() < (new Random().nextInt(30001) + 30000))
            return "redirect:/?error";
        user.setLastPhotoPost(System.currentTimeMillis());
        userRepository.save(user);
        String normalName = file.getOriginalFilename();
        String name = URLEncoder.encode(UUID.randomUUID() + "_" + normalName, StandardCharsets.UTF_8)
                .replaceAll("[ -]+", "_");
        Files.copy(file.getInputStream(), Path.of(uploadPhoto + "/" + name));
        photoRepository.save(new Photo(file.getOriginalFilename(), name));
        return "redirect:/?path=" + uploadPhotoUrl + "/" + name;
    }
}
