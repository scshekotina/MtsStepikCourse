package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.dto.LessonToEditDto;
import com.example.mtsstepiccourse.mapper.LessonDtoMapper;
import com.example.mtsstepiccourse.service.LessonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/lessons")
public class AdminLessonController {
    private final LessonService lessonService;
    private final LessonDtoMapper lessonDtoMapper;

    @PostMapping
    public void createLesson(@Valid @RequestBody LessonToEditDto lessonToEditDto) {}

    @PutMapping("/{id}")
    public void updateLesson(@PathVariable Long id, @Valid @RequestBody LessonToEditDto lessonToEditDto) {
        LessonDto lessonDto = lessonDtoMapper.lessonDtoToEditToLessonDto(lessonToEditDto);
        lessonDto.setId(id);
        lessonService.update(id, lessonDto).orElseThrow();

    }

    @GetMapping("/{id}")
    public LessonToEditDto getLesson(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.delete(id);
    }

}
