package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.*;
import com.example.mtsstepiccourse.mapper.ToLessonDtoMapper;
import com.example.mtsstepiccourse.service.LessonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
public class LessonController {
    private final LessonService lessonService;
    private final ToLessonDtoMapper toLessonDtoMapper;

    @PutMapping("/lessons/{id}")
    public void updateLesson(@PathVariable Long id, @Valid @RequestBody LessonDtoToEdit lessonDtoToEdit) {
        LessonDto lessonDto = toLessonDtoMapper.lessonDtoToEditToLessonDto(lessonDtoToEdit);
        lessonDto.setId(id);
        lessonService.update(id, lessonDto).orElseThrow();

    }

    @GetMapping("/lessons/{id}")
    public LessonDto getLesson(@PathVariable Long id) {
        return toLessonDtoMapper.lessonToLessonDto(lessonService.findById(id).orElseThrow());
    }

    @DeleteMapping("/lessons/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.delete(id);
    }

}
