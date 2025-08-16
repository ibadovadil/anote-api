package com.adil.anoteapi.controller;


import com.adil.anoteapi.entity.User;
import com.adil.anoteapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.resgiter(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return service.verify(user);
    }
}
