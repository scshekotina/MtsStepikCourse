package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.repository.LessonRepository;
import com.example.mtsstepiccourse.repository.ModuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public void create(Lesson lesson) {
        if (lesson.getModule() != null) {
            Module module = moduleRepository.findById(lesson.getModule().getId()).orElseThrow();
            lesson.setModule(module);
        }
        lesson.markAsCreatedAndUpdated();
        lessonRepository.save(lesson);
    }

    @Transactional
    @Override
    public void update(Long id, Lesson lesson) {
        Lesson fromRepo = lessonRepository.findById(id).orElseThrow();
        if (lesson.getModule() != null) {
            Module module = moduleRepository.findById(lesson.getModule().getId()).orElseThrow();
            lesson.setModule(module);
        }
        lesson.markAsCreated(fromRepo.getCreatingAuthor(), fromRepo.getCreatingDate());
        lesson.markAsUpdated();
        lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        lesson.markAsDeleted();
        lessonRepository.save(lesson);
    }
}
