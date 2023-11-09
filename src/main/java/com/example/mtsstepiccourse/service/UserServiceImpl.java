package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.User;
import com.example.mtsstepiccourse.repository.UserRepository;
import com.example.mtsstepiccourse.util.title.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAllByDeletingDateIsNull();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void create(User user) {
        user.setUpdatingAuthor(UserUtil.getCurrentUser());
        user.setUpdatingDate(LocalDateTime.now());
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        User fromRepository = userRepository.findById(id).orElseThrow();
        user.setId(id);
        user.setCourses(fromRepository.getCourses());
        user.setUpdatingAuthor(UserUtil.getCurrentUser());
        user.setUpdatingDate(LocalDateTime.now());
        user.setRegistrationDate(fromRepository.getRegistrationDate());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setDeletingAuthor(UserUtil.getCurrentUser());
        user.setDeletingDate(LocalDateTime.now());
        userRepository.save(user);
    }
}
