package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Optional<Lesson> findByIdAndDeletingDateIsNull(@Param("id") Long id);
}