package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.*;
import com.example.mtsstepiccourse.repository.UpdatableEntityRepository;
import com.example.mtsstepiccourse.security.UserAuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public abstract class UpdatableEntityServiceImpl<T extends UpdatableAndDeletableEntityWithCreatingData> {

    protected UpdatableEntityRepository<T> repository;
    protected final UserAuthService userAuthService;

    public T findById(Long id) {
        return repository.findByIdAndDeletingDateIsNull(id).orElseThrow();
    }

    @Transactional
    public void create(T entity) {
        userAuthService.markAsCreated(entity);
        save(entity);
    }

    @Transactional
    public void update(Long id, T entity) {
        T fromRepo = repository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        entity.setId(id);
        userAuthService.markAsCreated(entity, fromRepo.getCreatingAuthor(), fromRepo.getCreatingDate());
        userAuthService.markAsUpdated(entity);
        save(entity, fromRepo);
    }


    public void save(T entity) {
        save(entity, null);
    }

    @Transactional
    public void save(T entity, T entityFromRepo) {
        deleteLinkedEntities(entity, entityFromRepo);
        repository.save(entity);
        saveLinkedEntities(entity);
    }

    @Transactional
    protected abstract void deleteLinkedEntities(T entity,T entityFromRepo);

    @Transactional
    protected abstract void saveLinkedEntities(T entity);

    @Transactional
    public void delete(Long id) {
        T entity = repository.findByIdAndDeletingDateIsNull(id).orElseThrow();
        userAuthService.markAsDeleted(entity);
        repository.save(entity);
    }

}
