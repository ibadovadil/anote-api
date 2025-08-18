package com.adil.anoteapi.service.interf;

import com.adil.anoteapi.dto.note.NoteCreateDto;
import com.adil.anoteapi.dto.note.NoteDetailDto;
import com.adil.anoteapi.dto.note.NoteListDto;
import com.adil.anoteapi.dto.note.NoteUpdateDto;

import java.util.List;

public interface INoteService {
    NoteDetailDto create(NoteCreateDto dto);
    List<NoteListDto> getAllNotes();
    NoteDetailDto getNoteById(Long id);
    void updateNote(Long id, NoteUpdateDto dto);
    void deleteNote(Long id);
}
