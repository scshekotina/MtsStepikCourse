package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.repository.CourseRepository;
import com.example.mtsstepiccourse.repository.LessonRepository;
import com.example.mtsstepiccourse.repository.ModuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    private final LessonRepository lessonRepository;

    @Override
    public Module findById(Long id) {
        return moduleRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public void create(Module module) {
        if (module.getCourse() != null) {
            Course course = courseRepository.findById(module.getCourse().getId()).orElseThrow();
            module.setCourse(course);
        }
        if (module.getLessons() != null) {
            List<Lesson> lessons = module.getLessons().stream()
                    .map(l -> {
                        Lesson lesson = lessonRepository.findById(l.getId()).orElseThrow();
                        lesson.setModule(module);
                        return lesson;
                    })
                    .toList();
            module.setLessons(lessons);
        }
        moduleRepository.save(module);
    }

    @Transactional
    @Override
    public void update(Long id, Module module) {
        moduleRepository.findById(id).orElseThrow();
        module.setId(id);
        create(module);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Module module = moduleRepository.findById(id).orElseThrow();
        moduleRepository.delete(module);
    }
}
