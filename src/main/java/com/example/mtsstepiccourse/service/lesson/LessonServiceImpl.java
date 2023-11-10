package com.example.mtsstepiccourse.service.lesson;

import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.repository.ModuleRepository;
import com.example.mtsstepiccourse.repository.UpdatableEntityRepository;
import com.example.mtsstepiccourse.service.UpdatableEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl extends UpdatableEntityServiceImpl<Lesson> implements LessonService {

    private final ModuleRepository moduleRepository;

    public LessonServiceImpl(UpdatableEntityRepository<Lesson> repository, ModuleRepository moduleRepository) {
        super(repository);
        this.moduleRepository = moduleRepository;
    }

    @Override
    public void updateLinkedEntities(Lesson entity) {
        if (entity.getModule() != null) {
            Module module = moduleRepository.findByIdAndDeletingDateIsNull(entity.getModule().getId()).orElseThrow();
            module.markAsUpdated();
            entity.setModule(module);
        }
    }
}
