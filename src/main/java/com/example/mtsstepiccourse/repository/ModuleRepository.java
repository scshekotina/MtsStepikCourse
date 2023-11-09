package com.example.mtsstepiccourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mtsstepiccourse.model.Module;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Transactional
    Optional<Module> findByIdAndDeletingDateIsNull(@Param("id")Long id);
}