package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.UpdatableAndDeletableEntityWithCreatingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@NoRepositoryBean
public interface UpdatableEntityRepository<T extends UpdatableAndDeletableEntityWithCreatingData> extends JpaRepository<T, Long> {

    @Transactional
    Optional<T> findByIdAndDeletingDateIsNull(@Param("id") Long id);
}