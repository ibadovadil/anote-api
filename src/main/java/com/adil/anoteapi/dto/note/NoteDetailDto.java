package com.adil.anoteapi.dto.note;

import com.adil.anoteapi.dto.tag.TagListDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class NoteDetailDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TagListDto tag; // tag haqqında sadə info
}
