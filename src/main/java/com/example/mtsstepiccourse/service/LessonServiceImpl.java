package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.repository.LessonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public Optional<Lesson> findById(Long id) {
        return lessonRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Lesson> update(long id, LessonDto lessonDto) {
        Optional<Lesson> lessonFromRepository = lessonRepository.findById(id);
        if (lessonFromRepository.isEmpty()) {
            return lessonFromRepository;
        }
        Lesson lesson = new Lesson();
        lesson.setId(id);
        lesson.setText(lessonDto.getText());
        lesson.setTitle(lessonDto.getTitle());
        lesson.setCourse(lessonFromRepository.get().getCourse());
        return Optional.of(lessonRepository.save(lesson));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        lessonRepository.delete(lesson);
    }
}
