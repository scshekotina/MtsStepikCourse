package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository repository;

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return repository.findById(id);
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
        course.setLessons(courseFromRepository.get().getLessons());
        return Optional.of(repository.save(course));
    }

    @Override
    @Transactional
    public Optional<Course> deleteById(Long id) {
        Optional<Course> byId = repository.findById(id);
        byId.ifPresent(course -> repository.delete(course));
        return byId;
    }

    @Override
    public List<Course> findByTitleLike(String title) {
        return repository.findByTitleLike("%" + title + "%");
    }






}
