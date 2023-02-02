package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;


import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminControllerREST {
    private final UserService userService;


    @Autowired
    public AdminControllerREST(UserService userService) {
        this.userService = userService;
    }

    // Методы RestController - возвращают JSON
    @GetMapping()
    public ResponseEntity<List<User>> showAll() {
        return new ResponseEntity<>(userService.index(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.I_AM_A_TEAPOT);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user) {

        userService.update(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok(HttpStatus.OK);

    }


}
