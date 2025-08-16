package com.adil.anoteapi.mapper;

import com.adil.anoteapi.dto.tag.*;
import com.adil.anoteapi.entity.Tag;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag toEntity(TagCreateDto dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username") // əgər username lazımdırsa
    TagDetailDto toDetailDto(Tag tag);

    TagListDto toListDto(Tag tag);

    List<TagListDto> toListDtoList(List<Tag> tags);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTagFromDto(TagUpdateDto dto, @MappingTarget Tag tag);
}