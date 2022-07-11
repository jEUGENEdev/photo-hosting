package com.account.photo.controller.rest;

import com.account.photo.model.PhotoModel;
import com.account.photo.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoModel photoModel;

    @PostMapping("/get")
    public String get(@RequestParam(required=false) String count, @RequestParam(required=false) String offset) {
        return photoModel.get(count, offset);
    }

    @PostMapping("/count")
    public String count() {
        return photoModel.count();
    }

    @PostMapping("/n/like")
    public String like(@RequestParam(required = false) String id) {
        return photoModel.like(id, (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
