package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.dto.LessonToEditDto;
import com.example.mtsstepiccourse.mapper.LessonDtoMapper;
import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.service.LessonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/lessons")
public class AdminLessonController {
    private final LessonService lessonService;
    private final LessonDtoMapper lessonDtoMapper;

    @GetMapping
    public List<LessonDto> lessonTable() {
        return lessonService.findAll().stream().map(lessonDtoMapper::lessonToLessonDto).toList();
    }

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
    public LessonToEditDto getLesson(@PathVariable Long id) {
        return lessonDtoMapper.lessonToLessonToEditDto(lessonService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.delete(id);
    }

}
