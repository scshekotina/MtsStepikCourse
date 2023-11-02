package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.repository.CourseRepository;
import com.example.mtsstepiccourse.repository.LessonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private LessonRepository lessonRepository;
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public List<Lesson> findAll(Long courseId) {
        return lessonRepository.findByCourse_Id(courseId);
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        return lessonRepository.findById(id);
    }

    @Override
    public Lesson create(LessonDto lessonDto) {
        Course course = courseRepository.findById(lessonDto.getCourseId()).orElseThrow();
        Lesson lesson = new Lesson(lessonDto, course);
        return lessonRepository.save(lesson);
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
    public Optional<Lesson> deleteById(Long id) {
        Optional<Lesson> byId = lessonRepository.findById(id);
        byId.ifPresent(lesson -> lessonRepository.delete(lesson));
        return byId;
    }
}