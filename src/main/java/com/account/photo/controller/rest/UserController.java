package com.account.photo.controller.rest;

import com.account.photo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserModel userModel;

    @PostMapping("create")
    public String create(@RequestParam(required = false, name = "vk_id") String vkId,
                         @RequestParam(required = false) String username,
                         @RequestParam(required = false) String role,
                         @RequestParam(required = false, name="creator_pass") String creatorPassword) {
        return userModel.create(vkId, username, role, creatorPassword);
    }
}
