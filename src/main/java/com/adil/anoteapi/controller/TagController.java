package com.adil.anoteapi.controller;

import com.adil.anoteapi.dto.tag.TagCreateDto;
import com.adil.anoteapi.dto.tag.TagDetailDto;
import com.adil.anoteapi.dto.tag.TagListDto;
import com.adil.anoteapi.dto.tag.TagUpdateDto;
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

    @GetMapping("/all")
    public ResponseEntity<List<TagListDto>> getAllTags() {
        return ResponseEntity.ok(service.getAllTags());
    }
    @GetMapping("/all-detailed")
    public ResponseEntity<List<TagDetailDto>> getAllTagsDetailed() {
        return ResponseEntity.ok(service.getAllTagsDetailed());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TagDetailDto> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTagById(id));
    }

    @PostMapping
    public ResponseEntity<TagDetailDto> createTag(@Valid @RequestBody TagCreateDto dto) {
        return ResponseEntity.ok(service.createTag(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDetailDto> updateTag(@PathVariable Long id,
                                                  @RequestBody TagUpdateDto dto) {
        return ResponseEntity.ok(service.updateTag(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        service.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
