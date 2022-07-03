package com.account.photo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PageController {
    @ModelAttribute
    public void model(Model model) {
        model.addAttribute("anon", SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().toList().get(0).toString().equals("ROLE_ANONYMOUS"));
    }

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }
}
