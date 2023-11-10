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
        save(entity);
        entity.markAsCreatedAndUpdated();
    }

    @Transactional
    public void update(Long id, T entity) {
        T fromRepo = repository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        entity.setId(id);
        save(entity);
        entity.markAsCreated(fromRepo.getCreatingAuthor(), fromRepo.getCreatingDate());
        entity.markAsUpdated();
    }

    public void save(T entity) {
        updateLinkedEntities(entity);
        repository.save(entity);
    }

    public abstract void updateLinkedEntities(T entity);

    @Transactional
    public void delete(Long id) {
        T entity = repository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        repository.save(entity);
        entity.markAsDeleted();
    }
}
