package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CourseRepository {

    private ConcurrentHashMap<Long, Course> courses;
    private AtomicLong counter = new AtomicLong(0);

    public CourseRepository() {
        this.courses = new ConcurrentHashMap<>();
        courses.put(1L, new Course(1L, "Петров А.В.", "Основы кройки и шитья"));
        courses.put(2L, new Course(2L, "Мошкина А.В","Введение в архитектурный дизайн"));
        counter.set(2);
    }

    public List<Course> findAll()  {
        return new ArrayList<>(courses.values());
    }

    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(courses.get(id));
    }

    public synchronized Course save(Course course){
        if (course.getId() == null) {
            course.setId(counter.incrementAndGet());
        }
        return courses.put(course.getId(), course);
    }

    public synchronized void deleteById(Long id) {
        if (courses.remove(id) != null) {
            counter.decrementAndGet();
        }
    }

}
