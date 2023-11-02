package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    List<Lesson> findAll(Long courseId);

    Optional<Lesson> findById(Long id);

    Lesson create(LessonDto lessonDto);

    Optional<Lesson> update(long id, LessonDto lessonDto);

    Optional<Lesson> deleteById(Long id);

}
