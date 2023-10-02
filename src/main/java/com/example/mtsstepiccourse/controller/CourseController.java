package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.CourseRequestToCreate;
import com.example.mtsstepiccourse.dto.CourseRequestToUpdate;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.requireNonNullElse;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("")
    public List<Course> courseTable() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable("id") Long id) {
        return courseService.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public synchronized void updateCourse(@PathVariable Long id,
                             @Valid @RequestBody CourseRequestToUpdate request) {
        Course course = courseService.findById(id).orElseThrow();
        course.setTitle(request.getTitle());
        course.setAuthor(request.getAuthor());
        courseService.save(course);
    }

    @PostMapping
    public Course createCourse(@RequestBody CourseRequestToCreate request) {
        Course course = new Course(request.getAuthor(), request.getTitle());
        return courseService.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id).orElseThrow();
    }

    @GetMapping("/filter")
    public List<Course> getCoursesByTitlePrefix(@RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        return courseService.findByTitleWithPrefix(requireNonNullElse(titlePrefix, ""));
    }
}
