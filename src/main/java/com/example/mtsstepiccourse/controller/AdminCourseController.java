package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.CourseOnlyModulesDto;
import com.example.mtsstepiccourse.dto.CourseToEditDto;
import com.example.mtsstepiccourse.mapper.CourseDtoMapper;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.service.course.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/courses")
public class AdminCourseController {
    private final CourseService courseService;
    private final CourseDtoMapper courseDtoMapper;

    @GetMapping("/{id}")
    public CourseOnlyModulesDto getCourse(@PathVariable("id") Long id) {
        return courseDtoMapper.courseToCourseOnlyModulesDtoDto(courseService.findById(id));
    }

    @PostMapping
    public void createCourse(@Valid @RequestBody CourseToEditDto courseToEditDto) {
        Course course = new Course(courseToEditDto);
        courseService.create(course);
    }

    @PutMapping("/{id}")
    public synchronized void updateCourse(@PathVariable Long id,
                             @Valid @RequestBody CourseToEditDto courseToEditDto) {
        Course course = new Course(courseToEditDto);
        courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
    }

}
