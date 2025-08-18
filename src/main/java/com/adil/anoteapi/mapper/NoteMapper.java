package com.adil.anoteapi.mapper;

import com.adil.anoteapi.dto.note.NoteCreateDto;
import com.adil.anoteapi.dto.note.NoteDetailDto;
import com.adil.anoteapi.dto.note.NoteListDto;
import com.adil.anoteapi.dto.tag.TagListDto;
import com.adil.anoteapi.entity.Note;
import com.adil.anoteapi.entity.Tag;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    // create üçün dto → entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true) // service-də set edəcəyik
    @Mapping(target = "tag", ignore = true)  // service-də set edəcəyik
    Note toEntity(NoteCreateDto dto);

    // entity → detail dto
    @Mapping(source = "tag.name", target = "tagName")
    NoteDetailDto toDetailDto(Note note);


    @Mapping(source = "tag.name", target = "tagName")
    NoteListDto toListDto(Note note);
}