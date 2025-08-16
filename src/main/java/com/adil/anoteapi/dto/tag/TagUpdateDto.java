package com.adil.anoteapi.dto.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TagUpdateDto {
    @NotBlank
    @Size(max = 50)
    private String name;
}
