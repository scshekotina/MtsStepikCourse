package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.repository.CourseRepository;
import com.example.mtsstepiccourse.repository.ModuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;

    @Override
    public List<Course> simpleFindAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findByIdWithUsers(id).orElseThrow();
    }

    @Override
    @Transactional
    public void create(Course course) {
        if (course.getModules() != null) {
            List<Module> modules = course.getModules().stream()
                    .map(m -> {
                        Module fromRepo = moduleRepository.findById(m.getId()).orElseThrow();
                        fromRepo.setCourse(course);
                        return fromRepo;
                    }).toList();
            course.setModules(modules);
        }
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public void update(Long id, Course course){
        courseRepository.findById(id).orElseThrow();
        course.setId(id);
        create(course);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Course byId = courseRepository.findById(id).orElseThrow();
        courseRepository.delete(byId);
    }

    @Override
    public List<Course> findByTitleLike(String title) {
        return courseRepository.findByTitleLike("%" + title + "%");
    }

}
