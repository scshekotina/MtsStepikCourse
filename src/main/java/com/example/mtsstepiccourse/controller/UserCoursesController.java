package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.mapper.ToCourseDtoMapper;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.service.UserCoursesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserCoursesController {

    private UserCoursesService userCoursesService;
    private ToCourseDtoMapper toCourseDtoMapper;

    @GetMapping("/{user_id}/courses")
    public Set<CourseDto> getCourses(@PathVariable("user_id") Long userId) {
        Set<Course> courses = userCoursesService.getCourses(userId);
        return (courses.stream().map(toCourseDtoMapper::courseToCourseDto).collect(Collectors.toSet()));
    }

    @PostMapping("/{user_id}/courses")
    public void assignCourse(@PathVariable("user_id") Long userId,
                             @RequestParam("course_id") Long courseId) {
        userCoursesService.addCourse(userId, courseId);
    }

    @DeleteMapping("/{user_id}/courses")
    public void removeCourse(@PathVariable("user_id") Long userId,
                                @RequestParam("course_id") Long courseId) {
        userCoursesService.removeCourse(userId, courseId);
    }
}
