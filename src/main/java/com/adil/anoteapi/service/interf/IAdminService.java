package com.adil.anoteapi.service.interf;

import com.adil.anoteapi.dto.admin.UserListDto;
import com.adil.anoteapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    List<UserListDto> getAllUsers();

    UserListDto getUserById(Long id);

    UserListDto updateUserRole(Long userId, String role);

    void deleteUser(Long userId);
}
