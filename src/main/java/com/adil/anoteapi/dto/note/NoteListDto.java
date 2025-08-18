package com.adil.anoteapi.dto.note;

import com.adil.anoteapi.dto.tag.TagListDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class NoteListDto {
    private Long id;
    private String title;
    private String content;
    private String tagName;
}
