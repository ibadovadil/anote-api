package com.adil.anoteapi.dto.user;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDto {
    private Long id;
    private String username;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
}
