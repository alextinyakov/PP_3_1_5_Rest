package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

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

        return "admins_page";
    }

    @GetMapping("/show_all")
    public String ShowAll(ModelMap model) {
        model.addAttribute("userslist", userService.index());
        return "allUsers";
    }

    // Переделал на PostMapping согласно комментарию к прошлой задаче
    @PostMapping(value = "/deleteUser/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/show_all";
    }

    //    @GetMapping("/addUser")
//    public String addNewUser(User user,Model model) {
//       model.addAttribute(user);
//
//        return "newUserForm";
//    }
    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.getAllRoles());
        return "new";
    }

    //        @PostMapping("/addUser")
//        public String addUser (User user){
//            userService.add(user);
//            return "successCreatedUser";
//        }
    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute User user,
                          @RequestParam(name = "roleId") Long[] roleId) {
        userService.add(user, roleId);
        return "successCreatedUser";
    }

//    @GetMapping("/user-create")
//    public String createUserForm(User user, Model model) {
//        model.addAttribute(user);
//        return "user-create";
//    }
//
//    @PostMapping("/user-create")
//    public String createUser(User user) {
//        appService.saveUser(user);
//        return "redirect:/admin";
//    }
//    // с добавлением юзеров
//
//    @GetMapping(value = "users/add")
//    public String newUserForm(@ModelAttribute("user") User user, Model model) {
//        model.addAttribute("roles", roleService.getAllRoles());
//        //возвращаю страницу с добавлением юзеров
//        return "add_user";
//    }
//
//    @PostMapping(value = "users/add")
//    public String createNewUser(@ModelAttribute("user") User user
//            , @RequestParam(value = "roles") String[] roles) {
//        user.setRoles(roleService.getSetOfRoles(roles));
//        userService.addUser(user);
//        //возвращаю страницу с добавлением юзеров
//        return "redirect:/admin/users";
//    }
//    @GetMapping(value = "/deleteUser/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin/show_all";
//    }
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
