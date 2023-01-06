package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

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

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/show_all";
    }
//    @GetMapping("/edit/{id}")
//    public String edit (Model model, @PathVariable("id") Long id ){
//        model.addAttribute("user", userService.showById(id));
//        return "edit";
//    }
//
//    @PostMapping("/edit")
//    public String update(@ModelAttribute("user") User user){
//        userService.update(user);
//        return "redirect:/show_all";

//    }
//    @GetMapping("/editUser/{user}")
//    public String editUser(@PathVariable User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "editUser";
//    }

}
