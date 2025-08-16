package com.adil.anoteapi.service.interf;

import com.adil.anoteapi.dto.tag.TagCreateDto;
import com.adil.anoteapi.dto.tag.TagDetailDto;
import com.adil.anoteapi.dto.tag.TagUpdateDto;
import com.adil.anoteapi.entity.Tag;

import java.util.List;

public interface ITagService {
    TagDetailDto createTag(TagCreateDto dto);
    List<TagDetailDto> getAllTags(Long id);
    TagDetailDto updateTag(Long id, TagUpdateDto dto);
    void deleteTag(Long id);
}
