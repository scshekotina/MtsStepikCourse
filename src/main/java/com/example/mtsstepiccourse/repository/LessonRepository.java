package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}