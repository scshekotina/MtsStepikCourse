package com.example.mtsstepiccourse.service.module;

import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.model.UpdatableAndDeletableEntity;
import com.example.mtsstepiccourse.repository.LessonRepository;
import com.example.mtsstepiccourse.repository.UpdatableEntityRepository;
import com.example.mtsstepiccourse.service.UpdatableEntityServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl extends UpdatableEntityServiceImpl<Module> implements ModuleService {
    private final LessonRepository lessonRepository;

    public ModuleServiceImpl(UpdatableEntityRepository<Module> repository,
                             LessonRepository lessonRepository) {
        super(repository);
        this.lessonRepository = lessonRepository;
    }

    @Override
    protected void deleteLinkedEntities(Module entity, Module entityFromRepo) {
        if (entityFromRepo != null && entityFromRepo.getLessons() != null) {
            Set<Long> lessonsFromRepo = entityFromRepo.getLessons().stream().map(UpdatableAndDeletableEntity::getId)
                    .collect(Collectors.toSet());

            Set<Long> forDelete;
            if (entity.getLessons() != null) {
                Set<Long> lessonIds = entity.getLessons().stream().map(UpdatableAndDeletableEntity::getId)
                        .collect(Collectors.toSet());

                forDelete = lessonsFromRepo.stream().filter(id -> !lessonIds.contains(id)).collect(Collectors.toSet());
            } else {
                forDelete = lessonsFromRepo;
            }

            forDelete.forEach(id -> {
                Lesson lesson = lessonRepository.findById(id).orElseThrow();
                lesson.setModule(null);
                lesson.markAsUpdated();
                lessonRepository.save(lesson);

            });
        }
    }

    @Override
    protected void saveLinkedEntities(Module entity) {
        if(entity.getLessons() != null) {
            List<Lesson> lessons = entity.getLessons().stream()
                    .map(l -> {
                        Lesson lesson = lessonRepository.findByIdAndDeletingDateIsNull(l.getId()).orElseThrow();
                        lesson.setModule(entity);
                        lesson.markAsUpdated();
                        return lesson;
                    }).toList();

            entity.setLessons(lessons);
        }
    }
}
