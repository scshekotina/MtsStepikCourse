package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course create(CourseDto courseDto);

    Optional<Course> update(Long id, CourseDto courseDto);

    Optional<Course> deleteById(Long id);

    List<Course> findByTitleLike(String prefix);
}
