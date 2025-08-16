package com.adil.anoteapi.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordDto {
    private String oldPassword;
    private String newPassword;
}
