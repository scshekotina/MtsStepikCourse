package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Lesson;

import java.util.List;

public interface LessonService {

    List<Lesson> findAll();

    Lesson findById(Long id);

    void create(Lesson lesson);

    void update(Long id, Lesson lesson);

    void delete(Long id);
}
