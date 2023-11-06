package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.dto.ModuleDto;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.repository.CourseRepository;
import com.example.mtsstepiccourse.repository.ModuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findByIdWithUsers(id);
    }


    @Override
    @Transactional
    public Course create(CourseDto courseDto) {
        Course course = new Course(courseDto);
        List<Long> module_ids = courseDto.getModules().stream().map(ModuleDto::getCourseId).toList();
        List<Module> modules = moduleRepository.findAllById(module_ids);
        modules.forEach(course::addModule);
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Optional<Course> update(Long id, CourseDto courseDto){
        Optional<Course> courseFromRepository = courseRepository.findById(id);
        if(courseFromRepository.isEmpty()) {
            return courseFromRepository;
        }
        Course course = new Course(courseDto);
        course.setId(id);
        List<Long> module_ids = courseDto.getModules().stream().map(ModuleDto::getCourseId).toList();
        List<Module> modules = moduleRepository.findAllById(module_ids);
        course.setModules(modules);
        return Optional.of(courseRepository.save(course));
    }

    @Override
    @Transactional
    public Optional<Course> deleteById(Long id) {
        Optional<Course> byId = courseRepository.findById(id);
        byId.ifPresent(courseRepository::delete);
        return byId;
    }

    @Override
    public List<Course> findByTitleLike(String title) {
        return courseRepository.findByTitleLike("%" + title + "%");
    }

}
