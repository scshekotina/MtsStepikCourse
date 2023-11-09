package com.example.mtsstepiccourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mtsstepiccourse.model.Module;


public interface ModuleRepository extends JpaRepository<Module, Long> {
}