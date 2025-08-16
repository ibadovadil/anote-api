package com.adil.anoteapi.dto.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class NoteUpdateDto {
    @Size(max = 255)
    private String title;

    @NotBlank
    private String content;

    private Long tagId; // tag dəyişdirilə bilər
}
