package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.dto.CourseToEditDto;
import com.example.mtsstepiccourse.mapper.CourseDtoMapper;
import com.example.mtsstepiccourse.mapper.LessonDtoMapper;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/courses")
public class AdminCourseController {
    private final CourseService courseService;
    private final CourseDtoMapper courseDtoMapper;
    private final LessonDtoMapper lessonDtoMapper;

    @GetMapping("/{id}")
    public CourseToEditDto getCourse(@PathVariable("id") Long id) {
        return courseDtoMapper.courseToCourseToDto(courseService.findById(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public synchronized void updateCourse(@PathVariable Long id,
                             @Valid @RequestBody CourseToEditDto request) {
        CourseDto courseDto = courseDtoMapper.courseRequestToUpdateToCourseDto(request);
        courseService.update(id, courseDto).orElseThrow();
    }

    @PostMapping
    public void createCourse(@Valid @RequestBody CourseToEditDto request) {
        CourseDto courseDto = courseDtoMapper.courseRequestToUpdateToCourseDto(request);
        Course course = courseService.create(courseDto);
        courseDtoMapper.courseToCourseSimpleDto(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id).orElseThrow();
    }

}
