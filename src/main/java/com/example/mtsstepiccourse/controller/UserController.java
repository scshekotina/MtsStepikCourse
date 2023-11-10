package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.*;
import com.example.mtsstepiccourse.mapper.CourseDtoMapper;
import com.example.mtsstepiccourse.mapper.LessonDtoMapper;
import com.example.mtsstepiccourse.mapper.ModuleDtoMapper;
import com.example.mtsstepiccourse.service.course.CourseService;
import com.example.mtsstepiccourse.service.lesson.LessonService;
import com.example.mtsstepiccourse.service.module.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;

@AllArgsConstructor
@RestController
@RequestMapping
public class UserController {
    private final CourseService courseService;
    private final LessonService lessonService;
    private final ModuleService moduleService;

    private final CourseDtoMapper courseDtoMapper;
    private final ModuleDtoMapper moduleDtoMapper;
    private final LessonDtoMapper lessonDtoMapper;



    @GetMapping("/courses")
    public List<CourseSimpleDto> courseTable() {
        return courseService.simpleFindAll().stream()
                .map(courseDtoMapper::courseToCourseSimpleDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/courses/{id}")
    public CourseDto getCourse(@PathVariable("id") Long id) {
        return courseDtoMapper.courseToCourseDto(courseService.findById(id));
    }

    @GetMapping("/filter")
    public List<CourseDto> getCoursesByTitlePrefix(@RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        return courseService.findByTitleLike(requireNonNullElse(titlePrefix, "")).stream()
                .map(courseDtoMapper::courseToCourseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/modules/{id}")
    public ModuleDto getModule(@PathVariable Long id) {
        return moduleDtoMapper.moduleToModuleDto(moduleService.findById(id));
    }

    @GetMapping("/lessons/{id}")
    public LessonDto getLesson(@PathVariable Long id) {
        return lessonDtoMapper.lessonToLessonDto(lessonService.findById(id));
    }
}
