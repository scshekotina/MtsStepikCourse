package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.model.Lesson;

import java.util.Optional;

public interface LessonService {

    Optional<Lesson> findById(Long id);

    Optional<Lesson> update(long id, LessonDto lessonDto);

    void delete(Long id);
}
