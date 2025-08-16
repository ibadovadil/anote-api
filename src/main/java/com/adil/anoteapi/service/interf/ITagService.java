package com.adil.anoteapi.service.interf;

import com.adil.anoteapi.dto.tag.TagCreateDto;
import com.adil.anoteapi.dto.tag.TagDetailDto;
import com.adil.anoteapi.dto.tag.TagListDto;
import com.adil.anoteapi.dto.tag.TagUpdateDto;

import java.util.List;

public interface ITagService {
    TagDetailDto createTag(TagCreateDto dto);
    List<TagListDto> getAllTags();
    List<TagDetailDto> getAllTagsDetailed();
    TagDetailDto getTagById(Long id);
    TagDetailDto updateTag(Long id, TagUpdateDto dto);
    void deleteTag(Long id);
}
