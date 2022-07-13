package zadacha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zadacha.entities.User;
import zadacha.exceptions.UserAlreadyExistsException;
import zadacha.exceptions.UserNotFoundException;
import zadacha.exceptions.WrongPasswordException;
import zadacha.services.api.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public boolean registerNewUser(@RequestBody User newUser,
                                   HttpServletRequest request)
            throws UserAlreadyExistsException, ServletException {

        String username = newUser.getName();
        String password = newUser.getPassword();

        if(userService.createUser(newUser)) {
            userService.authWithHttpServletRequest(request, username, password);
            return true;
        }

        return false;

    }

    @PutMapping("/login")
    public boolean accessAccount(@RequestBody User user)
            throws UserNotFoundException, WrongPasswordException {

        return userService.loginUser(user);
    }

    @GetMapping("/login")
    public void accessAccount() {

    }

    @GetMapping("/validation")
    public boolean validateName(@RequestParam String name)
            throws UserAlreadyExistsException {

        return userService.checkName(name);
    }
}
