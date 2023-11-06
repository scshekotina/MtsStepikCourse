package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.*;
import com.example.mtsstepiccourse.mapper.CourseDtoMapper;
import com.example.mtsstepiccourse.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;

@AllArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final CourseDtoMapper courseDtoMapper;

    @GetMapping
    public List<CourseSimpleDto> courseTable() {
        return courseService.simpleFindAll().stream()
                .map(courseDtoMapper::courseToCourseSimpleDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CourseDto getCourse(@PathVariable("id") Long id) {
        return courseDtoMapper.courseToCourseDto(courseService.findById(id));
    }

    @GetMapping("/filter")
    public List<CourseDto> getCoursesByTitlePrefix(@RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        return courseService.findByTitleLike(requireNonNullElse(titlePrefix, "")).stream()
                .map(courseDtoMapper::courseToCourseDto)
                .collect(Collectors.toList());
    }
}
