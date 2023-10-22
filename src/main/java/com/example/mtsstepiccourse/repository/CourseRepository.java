package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTitleLike(String title);
}
