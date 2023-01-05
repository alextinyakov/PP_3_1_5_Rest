package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.models.User;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/users/all/";
    }


    @GetMapping("/all")
    public String allUsers(ModelMap model) {
        model.addAttribute("userslist", userService.index());
        return "users";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
//        User user = new User();
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "successCreatedUser";
    }

    @GetMapping("/edit/{id}")
    public String edit (Model model, @PathVariable("id") Long id ){
       model.addAttribute("user", userService.showById(id));
       return "edit";
    }

    @PostMapping ("/edit")
    public String update(@ModelAttribute("user") User user){
       userService.update(user);
       return "redirect:/users/all";

    }

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users/all";
    }


}

