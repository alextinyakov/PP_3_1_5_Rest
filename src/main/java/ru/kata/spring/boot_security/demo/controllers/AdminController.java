package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admins_page")
    public String home() {

        return "adminsPage";
    }

    @GetMapping("/show_all")
    public String showAll(ModelMap model) {
        model.addAttribute("usersList", userService.index());
        model.addAttribute("role", roleService.getAllRoles());
        return "allUsers";
    }

    // Переделал на DeleteMapping согласно комментарию к прошлой задаче
    @DeleteMapping(value = "/deleteUser/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/show_all";
    }



    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.getAllRoles());
        return "new";
    }


    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult,
                          @RequestParam(name = "roleId") Long[] roleId){
        if (bindingResult.hasErrors())
            return "new";
        userService.add(user, roleId);
        return "successCreatedUser";
    }


    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {

        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roleList", roleList);
        return "edit";
    }

    @PatchMapping(value = "/edit/{id}")
    public String editUser(@ModelAttribute("user") User user, @RequestParam(name = "roleId") Long[] roleId) {
        userService.add(user, roleId);
        return "successCreatedUser";
    }

}
