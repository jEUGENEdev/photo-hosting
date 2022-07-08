package com.account.photo.model;

import com.account.photo.entity.Photo;
import com.account.photo.entity.User;
import com.account.photo.repository.PhotoRepository;
import com.account.photo.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class PhotoModel {
    @Autowired
    private PhotoRepository photoRepository;

    @Transactional
    public String get(String cnt, String ofs) {
        if(cnt == null || ofs == null)
            return "{\"status\": \"err\"}";
        int count;
        long offset;
        try {
            count = Integer.parseInt(cnt);
            offset = Long.parseLong(ofs);
        } catch(RuntimeException e) {
            return "{\"status\": \"err\"}";
        }
        User user = null;
        try {
            user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        } catch(ClassCastException ignored) {}
        List<Photo> photoList = photoRepository.findByIdRange(offset+1, offset+count+1);
        StringBuilder response = new StringBuilder("{\"status\": \"ok\", \"items\": [ ");
        for(Photo photo : photoList) {
            boolean liked = false;
            try {
                liked = user.getPhotos().contains(photo);
            } catch(NullPointerException ignored) {}
            response.append(String.format("{\"id\": \"%s\", \"orig_name\": \"%s\", \"name\": \"%s\", \"like_count\": \"%s\", \"liked\": \"%s\"},",
                    photo.getId(), photo.getName(), photo.getFullName(), photo.getUsers().size(), liked ? "1" : "0"));
        }
        response.deleteCharAt(response.length()-1).append("]}");
        return response.toString();
    }

    public String count() {
        return String.format("{\"count\": \"%s\"}", photoRepository.count());
    }

    @Transactional
    public String like(String id, UserDetails principal) {
        long idPhoto;
        try {
            idPhoto = Long.parseLong(id);
        } catch(Exception e) {
            return "{\"status\": \"err\"}";
        }
        Photo photo = photoRepository.findById(idPhoto).orElse(null);
        if(photo == null)
            return "{\"status\": \"err\"}";
        boolean like = false;
        List<Photo> photoList = principal.getUser().getPhotos();
        if(photoList.contains(photo)) {
            photoList.remove(photo);
            photo.getUsers().remove(principal.getUser());
        }
        else {
            like = true;
            photoList.add(photo);
            photo.getUsers().add(principal.getUser());
        }
        return String.format("{\"status\": \"ok\", \"type\": \"%s\"}", like ? "like" : "dislike");
    }
}
