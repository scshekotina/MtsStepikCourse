package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.repository.LessonRepository;
import com.example.mtsstepiccourse.repository.ModuleRepository;
import com.example.mtsstepiccourse.util.title.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findByDeletingDateIsNull();
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public void update(Long id, Lesson lesson) {
        Lesson fromRepo = lessonRepository.findById(id).orElseThrow();
        if (lesson.getModule() != null) {
            Module module = moduleRepository.findById(lesson.getModule().getId()).orElseThrow();
            lesson.setModule(module);
        }
        lesson.setCreatingAuthor(fromRepo.getCreatingAuthor());
        lesson.setCreatingDate(fromRepo.getCreatingDate());
        lesson.setUpdatingAuthor(UserUtil.getCurrentUser());
        lesson.setUpdatingDate(LocalDateTime.now());
        lessonRepository.save(lesson);
    }

    @Transactional
    @Override
    public void create(Lesson lesson) {
        if (lesson.getModule() != null) {
            Module module = moduleRepository.findById(lesson.getModule().getId()).orElseThrow();
            lesson.setModule(module);
        }
        lesson.setCreatingAuthor(UserUtil.getCurrentUser());
        lesson.setCreatingDate(LocalDateTime.now());
        lesson.setUpdatingAuthor(UserUtil.getCurrentUser());
        lesson.setUpdatingDate(LocalDateTime.now());
        lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        lesson.setDeletingDate(LocalDateTime.now());
        lesson.setDeletingAuthor(UserUtil.getCurrentUser());
        lessonRepository.save(lesson);
    }
}
