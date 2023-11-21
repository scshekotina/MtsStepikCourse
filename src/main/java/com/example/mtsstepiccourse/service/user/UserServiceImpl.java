package com.example.mtsstepiccourse.service.user;

import com.example.mtsstepiccourse.model.User;
import com.example.mtsstepiccourse.repository.UserRepository;
import com.example.mtsstepiccourse.security.UserAuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    protected final UserAuthService userAuthService;

    @Override
    public List<User> findAll() {
        return userRepository.findAllByDeletingDateIsNullOrderByUsername();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void create(User user) {
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        User fromRepository = userRepository.findById(id).orElseThrow();
        user.setId(id);
        user.setCourses(fromRepository.getCourses());
        userAuthService.markAsUpdated(user);
        user.setRegistrationDate(fromRepository.getRegistrationDate());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userAuthService.markAsDeleted(user);
        userRepository.save(user);
    }
}
