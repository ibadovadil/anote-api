package com.adil.anoteapi.service.interf;

import com.adil.anoteapi.dto.user.UserChangePasswordDto;
import com.adil.anoteapi.dto.user.UserDetailDto;
import com.adil.anoteapi.dto.user.UserUpdateDto;

public interface IUserService {
    UserDetailDto getUserDetails();

    UserDetailDto updateUser(UserUpdateDto dto);

    void changePassword(UserChangePasswordDto dto);

    void deleteUser();
}
