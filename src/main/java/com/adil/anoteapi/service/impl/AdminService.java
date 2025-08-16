package com.adil.anoteapi.service.impl;

import com.adil.anoteapi.dto.admin.UserListDto;
import com.adil.anoteapi.dto.user.UserDetailDto;
import com.adil.anoteapi.entity.User;
import com.adil.anoteapi.enums.Role;
import com.adil.anoteapi.exception.ResourceNotFoundException;
import com.adil.anoteapi.exception.admin.InvalidRoleException;
import com.adil.anoteapi.mapper.UserMapper;
import com.adil.anoteapi.repository.UserRepository;
import com.adil.anoteapi.service.interf.IAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService implements IAdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AdminService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserListDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toListDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserListDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toListDto(user);
    }

    @Override
    public UserListDto updateUserRole(Long userId, String role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role newRole = mapRole(role);
        user.setRole(newRole);
        User updatedUser = userRepository.save(user);

        return userMapper.toListDto(updatedUser); // MapStruct və ya manual mapping
    }

    private Role mapRole(String role) {
        switch (role.toLowerCase()) {
            case "admin":
                return Role.ROLE_ADMIN;
            case "user":
                return Role.ROLE_USER;
            default:
                throw new InvalidRoleException("Invalid role: " + role);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);    }
}