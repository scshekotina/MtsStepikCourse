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
        return courseRepository.findAllByDeletingDateIsNullOrderByTitle();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findByIdWithLessons(id).orElseThrow();
    }

    @Override
    @Transactional
    public void create(Course course) {
        course.markAsCreatedAndUpdated();
        save(course);
    }

    @Override
    @Transactional
    public void update(Long id, Course course){
        Course fromRepo = courseRepository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        course.setId(id);
        course.markAsCreated(fromRepo.getCreatingAuthor(),fromRepo.getCreatingDate());
        course.markAsUpdated();
        save(course);
    }

    private void save(Course course) {
        if (course.getModules() != null) {
            List<Module> modules = course.getModules().stream()
                    .map(m -> {
                        Module fromRepo = moduleRepository.findByIdAndDeletingDateIsNull(m.getId()).orElseThrow();
                        fromRepo.setCourse(course);
                        fromRepo.markAsUpdated();
                        return fromRepo;
                    }).toList();
            course.setModules(modules);
        }
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Course course = courseRepository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        course.markAsDeleted();
        courseRepository.save(course);
    }

    @Override
    public List<Course> findByTitleLike(String title) {
        return courseRepository.findByTitleLike("%" + title + "%");
    }

}
