package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course save(Course course);

    void deleteById(Long id);

    List<Course> findByTitleWithPrefix(String prefix);
}
