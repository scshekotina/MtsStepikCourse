package com.example.mtsstepiccourse.service.user;

import com.example.mtsstepiccourse.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void create(User user);

    void update(Long id, User user);

    void deleteById(Long id);
}
