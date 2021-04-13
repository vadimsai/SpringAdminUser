package com.example.demo.controller;

import com.example.demo.service.UserDeteailServic;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    @GetMapping
    public String auth(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("users", user.getUsername());
            return "site";
        } else {
            model.addAttribute("users", "");
            return "site"; }
    }

}
