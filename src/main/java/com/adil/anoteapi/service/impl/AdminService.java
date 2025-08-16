package com.adil.anoteapi.service.impl;

import com.adil.anoteapi.entity.User;
import com.adil.anoteapi.enums.Role;
import com.adil.anoteapi.repository.UserRepository;
import com.adil.anoteapi.service.interf.IAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUserRole(Long userId, String role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Role enum dəyərinə çevirmək
        Role newRole;
        try {
            newRole = Role.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role);
        }

        user.setRole(newRole);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}