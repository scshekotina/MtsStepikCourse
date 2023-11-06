package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return repository.findByIdWithUsers(id);
    }

    @Override
    public Course create(CourseDto courseDto) {
        Course course = new Course(courseDto);
        return repository.save(course);
    }

    @Override
    @Transactional
    public Optional<Course> update(Long id, CourseDto courseDto){
        Optional<Course> courseFromRepository = repository.findById(id);
        if(courseFromRepository.isEmpty()) {
            return courseFromRepository;
        }
        Course course = new Course(courseDto);
        course.setId(id);
        course.setModules(courseFromRepository.get().getModules());
        return Optional.of(repository.save(course));
    }

    @Override
    @Transactional
    public Optional<Course> deleteById(Long id) {
        Optional<Course> byId = repository.findById(id);
        byId.ifPresent(repository::delete);
        return byId;
    }

    @Override
    public List<Course> findByTitleLike(String title) {
        return repository.findByTitleLike("%" + title + "%");
    }

    @Override
    public List<Lesson> getLessons(Long courseId) {
        Course course = repository.findByIdWithLessons(courseId).orElseThrow();
        List<Lesson> lessons = new ArrayList<>();
        for(Module m : course.getModules()) {
            lessons.addAll(m.getLessons());
        }
        return lessons;
    }

    @Override
    @Transactional
    public Module addModule(Long moduleId, LessonDto lessonDto) {
        Course course = repository.findById(moduleId).orElseThrow();
        Module module = new Module();
        course.addModule(module);
        repository.save(course);
        return module;
    }
}
