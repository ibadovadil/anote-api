package com.adil.anoteapi.service.impl;

import com.adil.anoteapi.dto.note.NoteCreateDto;
import com.adil.anoteapi.dto.note.NoteDetailDto;
import com.adil.anoteapi.dto.note.NoteListDto;
import com.adil.anoteapi.dto.note.NoteUpdateDto;
import com.adil.anoteapi.dto.tag.TagListDto;
import com.adil.anoteapi.entity.Note;
import com.adil.anoteapi.entity.Tag;
import com.adil.anoteapi.entity.User;
import com.adil.anoteapi.exception.ResourceNotFoundException;
import com.adil.anoteapi.mapper.NoteMapper;
import com.adil.anoteapi.repository.NoteRepository;
import com.adil.anoteapi.repository.TagRepository;
import com.adil.anoteapi.repository.UserRepository;
import com.adil.anoteapi.service.interf.INoteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService implements INoteService {


    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;


    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    @Override
    public List<NoteListDto> getAllNotes() {
        User user = getCurrentUser();
        return noteRepository.findAllByUserId(user.getId())
                .stream()
                .map(noteMapper::toListDto)
                .collect(Collectors.toList());    }

    @Override
    public NoteDetailDto getNoteById(Long id) {
        User user = getCurrentUser();
        Note note = noteRepository.findById(id)
                .filter(n -> n.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Note not found or access denied"));
        return noteMapper.toDetailDto(note);    }

    @Override
    public void updateNote(Long id, NoteUpdateDto dto) {
        User user = getCurrentUser();
        Note note = noteRepository.findById(id)
                .filter(n -> n.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Note not found or access denied"));

        if (dto.getTitle() != null) note.setTitle(dto.getTitle());
        if (dto.getContent() != null) note.setContent(dto.getContent());

        if (dto.getTagId() != null) {
            note.getUser().getTags().stream()
                    .filter(t -> t.getId().equals(dto.getTagId()))
                    .findFirst()
                    .ifPresentOrElse(note::setTag, () -> {
                        throw new ResourceNotFoundException("Tag not found for this user");
                    });
        } else {
            note.setTag(null);
        }

        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        User user = getCurrentUser();
        Note note = noteRepository.findById(id)
                .filter(n -> n.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Note not found or access denied"));

        noteRepository.delete(note);
    }


    @Override
    public NoteDetailDto create(NoteCreateDto dto) {
        User currentUser = getCurrentUser();
        Note note = noteMapper.toEntity(dto);
        note.setUser(currentUser);

        //Tag yoxlanisi
        if (dto.getTagId() != null) {
            Tag tag = tagRepository.findById(dto.getTagId()).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
            if (!tag.getUser().getId().equals(currentUser.getId())) {
                throw new ResourceNotFoundException("Tag not found for current user");
            }
            note.setTag(tag);
        }
        Note savedNote = noteRepository.save(note);
        return noteMapper.toDetailDto(savedNote);
    }


}