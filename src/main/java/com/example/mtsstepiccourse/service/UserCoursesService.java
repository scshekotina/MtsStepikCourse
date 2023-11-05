package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Course;

import java.util.Set;

public interface UserCoursesService {

    Set<Course> getCourses(Long userId);

    void addCourse(Long userId, Long courseId);

    void removeCourse(Long userId, Long courseId);

}
