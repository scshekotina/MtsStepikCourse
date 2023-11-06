package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> simpleFindAll();

    Course findById(Long id);

    void create(Course course);

    void update(Long id, Course course);

    void deleteById(Long id);

    List<Course> findByTitleLike(String prefix);

}
