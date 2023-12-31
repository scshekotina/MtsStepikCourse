package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.dto.LessonToEditDto;
import com.example.mtsstepiccourse.mapper.LessonDtoMapper;
import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.service.lesson.LessonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/lessons")
public class AdminLessonController {
    private final LessonService lessonService;
    private final LessonDtoMapper lessonDtoMapper;

    @PostMapping
    public void createLesson(@Valid @RequestBody LessonToEditDto lessonToEditDto) {
        Lesson lesson = new Lesson(lessonToEditDto);
        lessonService.create(lesson);
    }

    @PutMapping("/{id}")
    public void updateLesson(@PathVariable Long id, @Valid @RequestBody LessonToEditDto lessonToEditDto) {
        Lesson lesson = new Lesson(lessonToEditDto);
        lesson.setId(id);
        lessonService.update(id, lesson);
    }

    @GetMapping("/{id}")
    @Transactional
    public LessonDto getLesson(@PathVariable Long id) {
        return lessonDtoMapper.lessonToLessonDto(lessonService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.delete(id);
    }

}
