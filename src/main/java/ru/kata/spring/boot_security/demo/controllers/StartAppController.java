package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartAppController {

    @GetMapping("/admin")
    public String admin(){
        return "admins_page_b9";
    }

    @GetMapping("/user")
    public String user(){
        return "users_page_b";
    }


}
