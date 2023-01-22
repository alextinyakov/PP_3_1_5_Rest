package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
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

    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleService roleService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    //    @GetMapping("/admins_page")
//    public String home() {
//
//        return "admins_page_b";
//    }





        // Показать всех в таблице
       // Объединенный метод контроллера
        // Объединил с методом home
    @GetMapping("/admins_page")
    public String showAll(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("usersList", userService.index());
        model.addAttribute("role", roleService.getAllRoles());
     //       model.addAttribute("user", new User());
        return "admins_page_b";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.getAllRoles());
        return "admins_page_b";
    }
    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(name = "roleId") Long[] roleId) {
        userService.add(user, roleId);
        return "redirect:/admin/admins_page";
    }

    // Переделал на DeleteMapping согласно комментарию к прошлой задаче
    // см test2 на каждого юзера отдельное окно delete
    @DeleteMapping(value = "/deleteUser/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/admins_page";
    }

//    @GetMapping("/addUser")
//    public ModelAndView newUser() {
//        User user = new User();
//        ModelAndView mav = new ModelAndView("new3");
//        mav.addObject("user", user);
//
//        List<Role> roles = roleRepository.findAll();
//
//        mav.addObject("allRoles", roles);
//
//        return mav;
//    }

//    @GetMapping("/addUser")
//    public String addUser(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("role", roleService.getAllRoles());
//        return "new";
//    }


//    @PostMapping(value = "/addUser")
//    public String addUser(@ModelAttribute("user") @Valid User user,
//                          BindingResult bindingResult,
//                          @RequestParam(name = "roleId") Long[] roleId) {
//        if (bindingResult.hasErrors())
//            return "new";
//        userService.add(user, roleId);
//        return "successCreatedUser";
//    }


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
