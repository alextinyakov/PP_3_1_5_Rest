package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.utils.PersonNotFoundException;
import ru.kata.spring.boot_security.demo.utils.UserErrorResponse;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminControllerREST {
    private final UserService userService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminControllerREST(UserService userService, RoleService roleService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

//    // Показать всех в таблице
//    // Объединенный метод контроллера
//    // Объединил с методом home
//    @GetMapping("/admins_page")
//    public String showAll(ModelMap model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", user);
//        model.addAttribute("usersList", userService.index());
//        model.addAttribute("role", roleService.getAllRoles());
//        return "admins_page_b9";
//    }

    // Методы RestController - возвращают JSON
    @GetMapping()
    public ResponseEntity<List<User>> showAll() {
        return new ResponseEntity<> ( userService.index(), HttpStatus.OK );
    }

//    @GetMapping("/admins_page/{id}")
//    public User getUser(@PathVariable Long id) {
//        return userService.findById(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        return new ResponseEntity<> (userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser (@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.I_AM_A_TEAPOT);
    }

    @PatchMapping()
    public ResponseEntity<HttpStatus> updateUser (@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> addUser (@RequestBody User user){
        userService.add(user);
        return ResponseEntity.ok(HttpStatus.OK);

    }


//    // Добавить нового юзера
//    @GetMapping("/addUser")
//    public String addUser(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("role", roleService.getAllRoles());
//        return "admins_page_b9";
//    }
//
//    // Запостить нового юзера
//    @PostMapping(value = "/addUser")
//    public String addUser(@ModelAttribute("user") User user,
//                          @RequestParam(name = "roleId") Long[] roleId) {
//        userService.add(user, roleId);
//        return "redirect:/admin/admins_page";
//    }
//
//
//    // Переделал на DeleteMapping согласно комментарию к прошлой задаче
//    @DeleteMapping(value = "/delete/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin/admins_page";
//    }
//
//
//    // Редактировать юзера
//    @GetMapping("/edit/{id}")
//    public String editUser(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.findById(id));
//        model.addAttribute("role", roleService.getAllRoles());
//        return "admins_page_b9";
//    }
//
//
//    // Запатчить юзера
//    @PatchMapping(value = "/edit/{id}")
//    public String editUser(@ModelAttribute("user") User user,
//                           @RequestParam(name = "roleId") Long[] roleId) {
//        userService.add(user, roleId);
//        return "redirect:/admin/admins_page";
//    }

}
