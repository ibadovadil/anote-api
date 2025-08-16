package com.adil.anoteapi.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class RegisterResponse {
    private String username;
    private String email;
    private String role;
    private String token;
    private String message;
}