package com.account.photo.controller.rest;

import com.account.photo.model.PhotoModel;
import com.account.photo.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoModel photoModel;

    @GetMapping("/get")
    public String get(@RequestParam(required=false) String count, @RequestParam(required=false) String offset, HttpServletResponse response) {
        return photoModel.get(count, offset);
    }

    @GetMapping("/count")
    public String count() {
        return photoModel.count();
    }

    @GetMapping("/n/like")
    public String like(@RequestParam(required = false) String id) {
        return photoModel.like(id, (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
