package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserControllerREST {

    private final UserService userService;


    @Autowired
    public UserControllerREST(UserService userService) {
        this.userService = userService;

    }

    @GetMapping()
    public ResponseEntity<User> showAuthUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<> (user, HttpStatus.OK);
    }

}
