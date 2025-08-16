package com.adil.anoteapi.service.impl;

import com.adil.anoteapi.dto.tag.TagCreateDto;
import com.adil.anoteapi.dto.tag.TagDetailDto;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {

    private final TagRepository repo;
    private final TagMapper mapper;
    private final UserRepository userRepository;


    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public TagDetailDto createTag(TagCreateDto dto) {
        User currentUser = getCurrentUser();
        if (repo.existsByNameAndUserId(dto.getName(),currentUser.getId())){
            throw new ResourceAlreadyExistsException("Tag already exists");
        }
        Tag tag = mapper.toEntity(dto);
        tag.setUser(currentUser);

        Tag savedTag = repo.save(tag);
        return mapper.toDetailDto(savedTag);
    }

    @Override
    public List<TagDetailDto> getAllTags(Long userId) {
        List<Tag> tags = repo.findAllByUserId(userId);
        return tags.stream()
                .map(mapper::toDetailDto)
                .toList();
    }


    @Override
    public TagDetailDto updateTag(Long id, TagUpdateDto dto) {
        Tag tag = repo.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
        mapper.updateTagFromDto(dto, tag);
        Tag updatedTag = repo.save(tag);
        return mapper.toDetailDto(updatedTag);
    }

    @Override
    public void deleteTag(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("User tapılmadı"));
        repo.deleteById(id);
    }
}
