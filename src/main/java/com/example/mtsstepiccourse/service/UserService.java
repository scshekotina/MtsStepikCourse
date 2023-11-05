package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.UserDto;
import com.example.mtsstepiccourse.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User create(UserDto userDto);

    Optional<User> update(Long id, UserDto userDto);

    Optional<User> deleteById(Long id);
}
