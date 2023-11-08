package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByDeletingDateIsNull();
}