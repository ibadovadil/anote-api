package com.adil.anoteapi.controller;

import com.adil.anoteapi.dto.tag.TagCreateDto;
import com.adil.anoteapi.dto.tag.TagDetailDto;
import com.adil.anoteapi.entity.Tag;
import com.adil.anoteapi.service.impl.TagService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping("/tags/users/{id}")
    public ResponseEntity<List<TagDetailDto>> getAllTags(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllTags(id));
    }

    @PostMapping
    public ResponseEntity<TagDetailDto> createTag(@Valid @RequestBody TagCreateDto dto) {
        TagDetailDto createdTag = service.createTag(dto);
        return ResponseEntity.ok(createdTag);
    }
}
