package com.example.demo.controller;
import com.example.demo.model.Role;
import com.example.demo.model.Status;
import com.example.demo.model.Users;
import com.example.demo.repository.RepositorUser;
import com.example.demo.service.ServiceUserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ServiceUserDB serviceUserDB;
    public final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(ServiceUserDB serviceUserDB, PasswordEncoder passwordEncoder) {
        this.serviceUserDB = serviceUserDB;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String adm() {
        return "admin";
    }


    @PostMapping("/getAll")
    public String findAll(Model model){
        List<Users> users= serviceUserDB.findAll();
        model.addAttribute("users",users);
        return "admin";
    }

    @PostMapping("/findById")
    public String findById(@RequestParam("id") int id, Model model){
        List<Users> users= serviceUserDB.findById(id);
        if(users!=null) {
            List<Users> usersf= serviceUserDB.findById(id);
            model.addAttribute("usersid", usersf);
            return "admin";
        }else {model.addAttribute("messagef", "User with id-"+id+" does not exist");
        return "admin";}

    }

    @PostMapping("/save")
    public String save(@RequestParam("name") String name,
                       @RequestParam("surname") String surname,
                       @RequestParam("email") String email,
                       @RequestParam("address") String address,
                       @RequestParam("password") String password,
                       @RequestParam("role") Role role,
                       @RequestParam("status") Status status,
                       Model model) {
        Optional<Users> users=serviceUserDB.findFirstByEmail(email);
        if(users.isEmpty()) {
            serviceUserDB.save(new Users(name, surname, email, address, password, role, status));
            model.addAttribute("messages", "user save");
            return "admin";
        }else {model.addAttribute("messages","This email already exists, please update email");
            return "admin";}
    }



    @PostMapping  ("/update")
    public String update(@RequestParam("id") long id,
                         @RequestParam("name") String name,
                         @RequestParam("surname") String surname,
                         @RequestParam("email") String email,
                         @RequestParam("address") String address,
                         @RequestParam("password") String password,
                         @RequestParam("role") Role role,
                         @RequestParam("status") Status status,
                         Model model) {
        List<Users> userid= serviceUserDB.findById(id);
        List<Users> users=serviceUserDB.findAllByEmail(email);
        if(userid==null){model.addAttribute("messageu","User with id-"+id+" does not exist");
            return "admin";}
        else if(users.size()>1){model.addAttribute("messageu","This email already exists, please update email");
            return "admin";
        }else  {
            serviceUserDB.save(new Users(id,name, surname, email, address, password, role, status));
            model.addAttribute("messageu", "user update");
            return "admin";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id,Model model){
        List<Users> users= serviceUserDB.findById(id);
        if(users!=null) {
            serviceUserDB.delete(id);
            model.addAttribute("messaged", "user delete");
            return "admin";
        }else {model.addAttribute("messaged", "User with id-"+id+" does not exist");
            return "admin";}


    }

    @PostMapping("/findBysur")
    public String findByS(@RequestParam("surname") String surname,Model model){
        List<Users> user= serviceUserDB.findBySur(surname);
        if(user.isEmpty()) {
            model.addAttribute("messagefs", "User with surname-"+surname+" does not exist");
            return "admin";
        }else {model.addAttribute("userssur",user);
            return "admin";}
    }

}
