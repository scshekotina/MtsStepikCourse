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
        return moduleRepository.findByIdAndDeletingDateIsNull(id).orElseThrow();
    }

    @Transactional
    @Override
    public void create(Module module) {
        module.markAsCreatedAndUpdated();
        save(module);
    }

    @Transactional
    @Override
    public void update(Long id, Module module) {
        Module fromRepo = moduleRepository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        module.setId(id);
        module.markAsCreated(fromRepo.getCreatingAuthor(), fromRepo.getCreatingDate());
        module.markAsUpdated();
        save(module);
    }

    private void save(Module module) {
        if (module.getCourse() != null) {
            Course course = courseRepository.findByIdAndDeletingDateIsNull(module.getCourse().getId()).orElseThrow();
            course.markAsUpdated();
            module.setCourse(course);
        }
        if (module.getLessons() != null) {
            List<Lesson> lessons = module.getLessons().stream()
                    .map(l -> {
                        Lesson lesson = lessonRepository.findByIdAndDeletingDateIsNull(l.getId()).orElseThrow();
                        lesson.markAsUpdated();
                        lesson.setModule(module);
                        return lesson;
                    })
                    .toList();
            module.setLessons(lessons);
        }
        moduleRepository.save(module);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Module module = moduleRepository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        module.markAsDeleted();
        moduleRepository.save(module);
    }
}
