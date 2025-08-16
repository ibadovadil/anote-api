package com.adil.anoteapi.controller;


import com.adil.anoteapi.dto.auth.LoginRequest;
import com.adil.anoteapi.dto.auth.LoginResponse;
import com.adil.anoteapi.dto.auth.RegisterRequest;
import com.adil.anoteapi.dto.auth.RegisterResponse;
import com.adil.anoteapi.entity.User;
import com.adil.anoteapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }
}
