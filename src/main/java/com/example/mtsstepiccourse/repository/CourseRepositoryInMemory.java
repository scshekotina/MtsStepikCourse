package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CourseRepositoryInMemory implements CourseRepository {

    private ConcurrentHashMap<Long, Course> courseMap;
    private AtomicLong counter = new AtomicLong(0);

    public CourseRepositoryInMemory() {
        this.courseMap = new ConcurrentHashMap<>();
        courseMap.put(1L, new Course(1L, "Петров А.В.", "Основы кройки и шитья"));
        courseMap.put(2L, new Course(2L, "Мошкина А.В","Введение в архитектурный дизайн"));
        counter.set(2);
    }

    @Override
    public List<Course> findAll()  {
        return new ArrayList<>(courseMap.values());
    }

    @Override
    public Course findById(Long id) {
        return courseMap.get(id);
    }

    @Override
    public synchronized Course save(Course course){
        if (course.getId() == null) {
            course.setId(counter.incrementAndGet());
        }
        return courseMap.put(course.getId(), course);
    }

    @Override
    public synchronized Course deleteById(Long id) {
        Course removed = courseMap.remove(id);
        if (removed != null) {
            counter.decrementAndGet();
        }
        return removed;
    }

    @Override
    public List<Course> findByTitleWithPrefix(String prefix) {
        return courseMap.values()
                .stream()
                .filter(course -> course.getTitle().startsWith(prefix))
                .collect(Collectors.toList());
    }

}
