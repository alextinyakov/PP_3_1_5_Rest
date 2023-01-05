package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.Service.UserService;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admins_page")
    public String home() {

        return "admins_page";
    }
    @GetMapping("/show_all")
    public String ShowAll(ModelMap model) {
        model.addAttribute("userslist", userService.index());
        return "allUsers";
    }

}
