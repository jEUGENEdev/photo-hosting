package com.account.photo.controller.rest;

import com.account.photo.model.AuthVkModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class AuthVkController {
    @Autowired
    private AuthVkModel authVkModel;

    @GetMapping("/auth/vk")
    public RedirectView loginVk(@RequestParam long uid,
                                @RequestParam(name="first_name") String firstName) {
        authVkModel.auth(uid, firstName);
        return new RedirectView("/");
    }
}
