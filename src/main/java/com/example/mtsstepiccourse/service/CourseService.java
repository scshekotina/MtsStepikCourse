package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course save(Course course);

    Optional<Course> deleteById(Long id);

    List<Course> findByTitleWithPrefix(String prefix);
}
