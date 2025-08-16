package com.adil.anoteapi.mapper;

import com.adil.anoteapi.dto.admin.UserListDto;
import com.adil.anoteapi.dto.user.UserCreateDto;
import com.adil.anoteapi.dto.user.UserDetailDto;
import com.adil.anoteapi.dto.user.UserUpdateDto;
import com.adil.anoteapi.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateDto dto);

    UserDetailDto toDetailDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateDto dto, @MappingTarget User user);

    UserListDto toListDto(User user);
}
