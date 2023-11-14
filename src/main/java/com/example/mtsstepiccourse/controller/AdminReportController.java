package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.CourseWithUsersDto;
import com.example.mtsstepiccourse.mapper.CourseDtoMapper;
import com.example.mtsstepiccourse.service.course.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/report")
public class AdminReportController {
    private final CourseService courseService;
    private final CourseDtoMapper courseDtoMapper;

    @Secured("ROLE_ADMIN")
    @GetMapping("/courses/{id}")
    public CourseWithUsersDto getCourseWithUsers(@PathVariable("id") Long id) {
        return courseDtoMapper.courseToCourseWithUsersDto(courseService.findById(id));
    }
}
