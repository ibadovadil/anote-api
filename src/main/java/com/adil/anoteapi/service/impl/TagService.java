package com.adil.anoteapi.service.impl;

import com.adil.anoteapi.dto.tag.TagCreateDto;
import com.adil.anoteapi.dto.tag.TagDetailDto;
import com.adil.anoteapi.dto.tag.TagListDto;
import com.adil.anoteapi.dto.tag.TagUpdateDto;
import com.adil.anoteapi.entity.Tag;
import com.adil.anoteapi.entity.User;
import com.adil.anoteapi.exception.ResourceAlreadyExistsException;
import com.adil.anoteapi.exception.ResourceNotFoundException;
import com.adil.anoteapi.mapper.TagMapper;
import com.adil.anoteapi.repository.TagRepository;
import com.adil.anoteapi.repository.UserRepository;
import com.adil.anoteapi.service.interf.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.condition.ConditionsReportEndpoint;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {


    private final TagRepository repo;
    private final TagMapper mapper;
    private final UserRepository userRepository;
    private final ConditionsReportEndpoint conditionsReportEndpoint;


    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public TagDetailDto createTag(TagCreateDto dto) {
        User currentUser = getCurrentUser();
        if (repo.existsByNameAndUserId(dto.getName(), currentUser.getId())) {
            throw new ResourceAlreadyExistsException("Tag already exists");
        }
        Tag tag = mapper.toEntity(dto);
        tag.setUser(currentUser);

        Tag savedTag = repo.save(tag);
        return mapper.toDetailDto(savedTag);
    }

    @Override
    public List<TagListDto> getAllTags() {
        User currentUser = getCurrentUser();
        return mapper.toListDtoList(repo.findAllByUserId(currentUser.getId()));
    }

    @Override
    public List<TagDetailDto> getAllTagsDetailed() {
        User currentUser = getCurrentUser();
        return mapper.toListDtoListDetailed(repo.findAllByUserId(currentUser.getId()));
    }

    @Override
    public TagDetailDto getTagById(Long id) {
        User currentUser = getCurrentUser();
        Tag tag = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));

        if (!tag.getUser().getId().equals(currentUser.getId())) {
            throw new ResourceNotFoundException("Tag not found for current user");
        }

        return mapper.toDetailDto(tag);
    }

    @Override
    public TagDetailDto updateTag(Long id, TagUpdateDto dto) {
        User currentUser = getCurrentUser();
        Tag tag = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));

        if (!tag.getUser().getId().equals(currentUser.getId())) {
            throw new ResourceNotFoundException("Tag not found for current user");
        }
        mapper.updateTagFromDto(dto, tag);

        mapper.updateTagFromDto(dto, tag);
        Tag updatedTag = repo.save(tag);
        return mapper.toDetailDto(updatedTag);
    }

    @Override
    public void deleteTag(Long id) {
        User currentUser = getCurrentUser();
        Tag tag = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        if (!tag.getUser().getId().equals(currentUser.getId())) {
            throw new ResourceNotFoundException("Tag not found for current user");
        }

        repo.delete(tag);
    }
}
