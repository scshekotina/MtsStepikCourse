package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Lesson;

public interface LessonService {

    Lesson findById(Long id);

    void create(Lesson lesson);

    void update(Long id, Lesson lesson);

    void delete(Long id);
}
