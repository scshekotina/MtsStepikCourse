package com.example.mtsstepiccourse.service;

import com.example.mtsstepiccourse.model.Module;

public interface ModuleService {

    Module findById(Long id);

    void create(Module module);

    void update(Long id, Module module);

    void delete(Long id);
}
