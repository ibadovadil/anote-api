package com.adil.anoteapi.mapper;

import com.adil.anoteapi.dto.note.NoteCreateDto;
import com.adil.anoteapi.dto.note.NoteDetailDto;
import com.adil.anoteapi.dto.note.NoteListDto;
import com.adil.anoteapi.dto.note.NoteUpdateDto;
import com.adil.anoteapi.entity.Note;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    Note toEntity(NoteCreateDto dto);

    NoteDetailDto toDetailDto(Note note);

    NoteListDto toListDto(Note note);

    List<NoteListDto> toListDtoList(List<Note> notes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateNoteFromDto(NoteUpdateDto dto, @MappingTarget Note note);
}
