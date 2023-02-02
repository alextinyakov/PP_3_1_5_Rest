package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartAppController {

    @GetMapping("/admin")
    public String admin() {
        return "admin1";
    }

    @GetMapping("/user")
    public String user() {
        return "user1";
    }


}
