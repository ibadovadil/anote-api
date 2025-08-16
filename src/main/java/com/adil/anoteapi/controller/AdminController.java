package com.adil.anoteapi.controller;

import com.adil.anoteapi.dto.admin.UserListDto;
import com.adil.anoteapi.dto.user.UserDetailDto;
import com.adil.anoteapi.service.impl.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adil.anoteapi.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserListDto>> getAllUsers() {
        List<UserListDto> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserListDto> getUserById(@PathVariable Long id) {
        UserListDto userDto = service.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserListDto> updateUserRole(@PathVariable Long id, @RequestParam String role) {
        UserListDto updatedUser = service.updateUserRole(id, role);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
