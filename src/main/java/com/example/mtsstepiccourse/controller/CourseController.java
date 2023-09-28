package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.CourseRequestToCreate;
import com.example.mtsstepiccourse.dto.CourseRequestToUpdate;
import com.example.mtsstepiccourse.model.Course;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.mtsstepiccourse.repository.CourseRepository;

import java.util.List;

import static java.util.Objects.requireNonNullElse;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseRepository courseRepository;

    @GetMapping("")
    public List<Course> courseTable() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable("id") Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable Long id,
                             @Valid @RequestBody CourseRequestToUpdate request) {
        Course course = courseRepository.findById(id).orElseThrow();
        course.setTitle(request.getTitle());
        course.setAuthor(request.getAuthor());
        courseRepository.save(course);
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseRequestToCreate request) {
        Course course = new Course(request.getAuthor(), request.getTitle());
        return courseRepository.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }

    @GetMapping("/filter")
    public List<Course> getCoursesByTitlePrefix(@RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        return courseRepository.findByTitleWithPrefix(requireNonNullElse(titlePrefix, ""));
    }
}
