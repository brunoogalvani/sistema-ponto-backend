package com.bruno.SistemaPonto.controllers;

import com.bruno.SistemaPonto.entities.User;
import com.bruno.SistemaPonto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{name}/{login}/{password}")
    public User createUser(@PathVariable String name, @PathVariable String login, @PathVariable String password){
        User user = new User(name, login, password);
        return userRepository.save(user);
    }
}
