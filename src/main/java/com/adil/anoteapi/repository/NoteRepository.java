package com.adil.anoteapi.repository;

import com.adil.anoteapi.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUserId(Long userId);

    List<Note> findAllByUserIdAndTagId(Long userId, Long tagId);
}