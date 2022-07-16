package com.account.photo.controller.rest;

import com.account.photo.model.AuthVkModel;
import com.account.photo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.NoSuchAlgorithmException;

@RestController
public class AuthVkController {
    @Autowired
    private AuthVkModel authVkModel;
    @Value("${vkapp.secret.key}")
    private String secretKeyVk;
    @Value("${vkapp.id}")
    private long appId;

    @GetMapping("/auth/vk")
    public RedirectView loginVk(@RequestParam long uid,
                                @RequestParam(name="first_name") String firstName,
                                @RequestParam String hash) throws NoSuchAlgorithmException {
        if(!Utils.checkVkHash(uid, appId, secretKeyVk, hash))
            return new RedirectView("/login");
        authVkModel.auth(uid, firstName);
        return new RedirectView("/");
    }
}
