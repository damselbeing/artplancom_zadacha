package zadacha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zadacha.entities.User;
import zadacha.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registration")
    boolean registerNewUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @PutMapping("/login")
    boolean accessAccount(@RequestBody User user) {
        return userService.loginUser(user);
    }
}