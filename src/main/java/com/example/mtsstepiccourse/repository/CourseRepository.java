package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findAll();

    Course findById(Long id);

    Course save(Course course);

    Course deleteById(Long id);

    List<Course> findByTitleWithPrefix(String prefix);
}
