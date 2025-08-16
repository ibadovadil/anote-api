package com.adil.anoteapi.dto.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class TagCreateDto {
    @NotBlank
    @Size(max = 50)
    private String name;
}

