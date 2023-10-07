package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.dto.CourseRequestToCreate;
import com.example.mtsstepiccourse.dto.CourseRequestToUpdate;
import com.example.mtsstepiccourse.mapper.CourseRequestToCreateToCourseMapper;
import com.example.mtsstepiccourse.mapper.CourseRequestToUpdateToCourseMapper;
import com.example.mtsstepiccourse.mapper.CourseToCourseDtoMapper;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final CourseToCourseDtoMapper courseToCourseDtoMapper;
    private final CourseRequestToUpdateToCourseMapper courseRequestToUpdateToCourseMapper;
    private final CourseRequestToCreateToCourseMapper courseRequestToCreateToCourseMapper;

    @GetMapping("")
    public List<CourseDto> courseTable() {
        return courseService.findAll().stream()
                .map(courseToCourseDtoMapper::courseToCourseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CourseDto getCourse(@PathVariable("id") Long id) {
        return courseToCourseDtoMapper.courseToCourseDto(courseService.findById(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public synchronized void updateCourse(@PathVariable Long id,
                             @Valid @RequestBody CourseRequestToUpdate request) {
        courseService.findById(id).orElseThrow();
        Course course = courseRequestToUpdateToCourseMapper.courseRequestToCreateToCourse(request);
        course.setId(id);
        courseService.save(course);
    }

    @PostMapping
    public Course createCourse(@Valid @RequestBody CourseRequestToCreate request) {
        Course course = courseRequestToCreateToCourseMapper.courseRequestToCreateToCourse(request);
        return courseService.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id).orElseThrow();
    }

    @GetMapping("/filter")
    public List<CourseDto> getCoursesByTitlePrefix(@RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        return courseService.findByTitleWithPrefix(requireNonNullElse(titlePrefix, "")).stream()
                .map(courseToCourseDtoMapper::courseToCourseDto)
                .collect(Collectors.toList());
    }
}
