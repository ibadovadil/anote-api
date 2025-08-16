package com.adil.anoteapi.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {

    @Size(min = 3, max = 20)
    private String username;

    @Email
    private String email;
}
