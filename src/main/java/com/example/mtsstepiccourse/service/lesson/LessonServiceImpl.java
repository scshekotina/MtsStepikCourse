package com.example.mtsstepiccourse.service.lesson;

import com.example.mtsstepiccourse.model.Lesson;
import com.example.mtsstepiccourse.repository.UpdatableEntityRepository;
import com.example.mtsstepiccourse.service.UpdatableEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl extends UpdatableEntityServiceImpl<Lesson> implements LessonService {


    public LessonServiceImpl(UpdatableEntityRepository<Lesson> repository) {
        super(repository);
    }

    @Override
    protected void deleteLinkedEntities(Lesson entity, Lesson entityFromRepo) {

    }

    @Override
    protected void saveLinkedEntities(Lesson entity) {

    }
}
