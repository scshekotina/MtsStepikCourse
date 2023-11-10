package com.example.mtsstepiccourse.service;

public interface UpdatableEntityService<T> {

    T findById(Long id);

    void create(T entity);

    void update(Long id, T entity);

    void delete(Long id);

}
