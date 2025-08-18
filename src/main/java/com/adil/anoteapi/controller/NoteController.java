package com.adil.anoteapi.controller;

import com.adil.anoteapi.dto.note.NoteCreateDto;
import com.adil.anoteapi.dto.note.NoteDetailDto;
import com.adil.anoteapi.dto.note.NoteListDto;
import com.adil.anoteapi.dto.note.NoteUpdateDto;
import com.adil.anoteapi.service.interf.INoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {
    private final INoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteListDto>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDetailDto> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @PostMapping
    public ResponseEntity<NoteDetailDto> create(@RequestBody NoteCreateDto dto) {
        return ResponseEntity.ok(noteService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNote(@PathVariable Long id,
                                             @RequestBody NoteUpdateDto dto) {
        noteService.updateNote(id, dto);
        return ResponseEntity.ok("Note updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok("Note deleted successfully");
    }


}
