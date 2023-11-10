package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.*;
import com.example.mtsstepiccourse.repository.UpdatableEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public abstract class UpdatableEntityServiceImpl<T extends UpdatableAndDeletableEntityWithCreatingData> {

    protected UpdatableEntityRepository<T> repository;

    public T findById(Long id) {
        return repository.findByIdAndDeletingDateIsNull(id).orElseThrow();
    }

    @Transactional
    public void create(T entity) {
        entity.markAsCreatedAndUpdated();
        save(entity);
    }

    @Transactional
    public void update(Long id, T entity) {
        T fromRepo = repository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        entity.setId(id);
        entity.markAsCreated(fromRepo.getCreatingAuthor(), fromRepo.getCreatingDate());
        entity.markAsUpdated();
        save(entity, fromRepo);
    }


    public void save(T entity) {
        save(entity, null);
    }

    public void save(T entity, T entityFromRepo) {
        updateLinkedEntities(entity, entityFromRepo);
        repository.save(entity);
    }

    protected abstract void updateLinkedEntities(T entity, T entityFromRepo);

    @Transactional
    public void delete(Long id) {
        T entity = repository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        entity.markAsDeleted();
        repository.save(entity);
    }
}
