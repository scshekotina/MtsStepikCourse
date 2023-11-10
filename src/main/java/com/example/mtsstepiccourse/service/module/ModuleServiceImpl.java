package com.example.mtsstepiccourse.service.module;

import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.repository.CourseRepository;
import com.example.mtsstepiccourse.repository.LessonRepository;
import com.example.mtsstepiccourse.repository.UpdatableEntityRepository;
import com.example.mtsstepiccourse.service.UpdatableEntityServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl extends UpdatableEntityServiceImpl<Module> implements ModuleService {
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public ModuleServiceImpl(UpdatableEntityRepository<Module> repository, CourseRepository courseRepository,
                             LessonRepository lessonRepository) {
        super(repository);
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void updateLinkedEntities(Module entity) {
        if (entity.getCourse() != null) {
            Course course = courseRepository.findByIdAndDeletingDateIsNull(entity.getCourse().getId()).orElseThrow();
            course.markAsUpdated();
            entity.setCourse(course);
        }
        if (entity.getLessons() != null) {
            List<Lesson> lessons = entity.getLessons().stream()
                    .map(l -> {
                        Lesson lesson = lessonRepository.findByIdAndDeletingDateIsNull(l.getId()).orElseThrow();
                        lesson.markAsUpdated();
                        lesson.setModule(entity);
                        return lesson;
                    })
                    .toList();
            entity.setLessons(lessons);
        }
    }
}
