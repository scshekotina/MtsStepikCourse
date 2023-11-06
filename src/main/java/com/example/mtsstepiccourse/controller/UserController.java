package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.UserDto;
import com.example.mtsstepiccourse.dto.UserDtoToEdit;
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
public class UserController {

    private UserService userService;
    private UserDtoMapper userDtoMapper;

    @GetMapping
    public List<UserDto> userTable() {
        return userService.findAll().stream()
                .map(userDtoMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userDtoMapper.userToUserDto(userService.findById(id).orElseThrow());
    }

    @PostMapping()
    public UserDto createUser(@Valid @RequestBody UserDtoToEdit request) {
        UserDto userDto = userDtoMapper.userDtoToEditToUserDto(request);
        User user = userService.create(userDto);
        return userDtoMapper.userToUserDto(user);
    }

    @PutMapping("/{id}")
    public synchronized void updateUser (@PathVariable Long id,
                                          @Valid @RequestBody UserDtoToEdit request) {
        UserDto userDto = userDtoMapper.userDtoToEditToUserDto(request);
        userService.update(id, userDto).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id).orElseThrow();
    }

}
