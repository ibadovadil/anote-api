package com.adil.anoteapi.dto.tag;

import com.adil.anoteapi.entity.User;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor @Builder
public class TagDetailDto {
    private Long id;
    private String name;
    private Long userId;
    private String username;
    private Instant createdAt;
    private Instant updatedAt;
}
