package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.UserDto;
import com.example.mtsstepiccourse.model.User;
import com.example.mtsstepiccourse.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(UserDto userDto) {
        User user = new User(userDto);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(Long id, UserDto userDto) {
        Optional<User> fromRepository = userRepository.findById(id);
        if (fromRepository.isEmpty()) {
            return fromRepository;
        }
        User user = new User(userDto);
        user.setCourses(fromRepository.get().getCourses());
        return Optional.of(userRepository.save(user));
    }

    @Override
    @Transactional
    public Optional<User> deleteById(Long id) {
        Optional<User> fromRepository = userRepository.findById(id);
        fromRepository.ifPresent(userRepository::delete);
        return fromRepository;
    }
}
