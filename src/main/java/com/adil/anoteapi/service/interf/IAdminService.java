package com.adil.anoteapi.service.interf;

import com.adil.anoteapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User updateUserRole(Long userId, String role);

    void deleteUser(Long userId);
}
