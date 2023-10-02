package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository repository;

    @Override
    public List<Course> findAll()  {
        return repository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public Course save(Course course){
        return repository.save(course);
    }

    @Override
    public Optional<Course> deleteById(Long id) {
        return Optional.ofNullable(repository.deleteById(id));
    }

    @Override
    public List<Course> findByTitleWithPrefix(String prefix) {
        return repository.findByTitleWithPrefix(prefix);
    }

}
