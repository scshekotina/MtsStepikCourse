package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.*;
import com.example.mtsstepiccourse.mapper.LessonDtoMapper;
import com.example.mtsstepiccourse.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;
    private final LessonDtoMapper lessonDtoMapper;

    @GetMapping("/{id}")
    public LessonDto getLesson(@PathVariable Long id) {
        return lessonDtoMapper.lessonToLessonDto(lessonService.findById(id).orElseThrow());
    }
}
