package com.adil.anoteapi.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String username;
    private List<String> roles;
}