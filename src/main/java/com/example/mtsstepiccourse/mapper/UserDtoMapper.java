package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.UserDto;
import com.example.mtsstepiccourse.dto.UserToEditDto;
import com.example.mtsstepiccourse.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto userToUserDto(User user);
    UserToEditDto userToUserToEditDto(User user);}
