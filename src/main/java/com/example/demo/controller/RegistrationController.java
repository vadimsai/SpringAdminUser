package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.repository.RepositorUser;
import com.example.demo.security.RegistrationForm;
import com.example.demo.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    public final PasswordEncoder passwordEncoder;
    public final RepositorUser userRepos;

    @Autowired
    public RegistrationController(PasswordEncoder passwordEncoder, RepositorUser userRepos) {
        this.passwordEncoder = passwordEncoder;
        this.userRepos = userRepos;
    }

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String processUsers(RegistrationForm form, Model model){
        Optional<Users> users=userRepos.findFirstByEmail(form.getEmail());
        if(users.isEmpty()) {
            userRepos.save(form.toUsers(passwordEncoder));
            return "redirect:/auth/login";
        }else {
            model.addAttribute("message","This email already exists");
            return "registration";
        }

    }


}
