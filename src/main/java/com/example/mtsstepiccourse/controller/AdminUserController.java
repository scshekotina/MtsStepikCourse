package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.UserDto;
import com.example.mtsstepiccourse.dto.UserToEditDto;
import com.example.mtsstepiccourse.mapper.UserDtoMapper;
import com.example.mtsstepiccourse.model.User;
import com.example.mtsstepiccourse.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class AdminUserController {

    private UserService userService;
    private UserDtoMapper userDtoMapper;

    @GetMapping
    public List<UserDto> userTable() {
        return userService.findAll().stream()
                .map(userDtoMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserToEditDto getUser(@PathVariable("id") Long id) {
        return userDtoMapper.userToUserToEditDto(userService.findById(id));
    }

    @PostMapping()
    public void createUser(@Valid @RequestBody UserToEditDto userToEditDto) {
        User user = new User(userToEditDto);
        userService.create(user);
    }

    @PutMapping("/{id}")
    public synchronized void updateUser (@PathVariable Long id,
                                          @Valid @RequestBody UserToEditDto userToEditDto) {
        User user = new User(userToEditDto);
        userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
