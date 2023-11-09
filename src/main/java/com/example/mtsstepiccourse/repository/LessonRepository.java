package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Lesson;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends UpdatableEntityRepository<Lesson> {

}