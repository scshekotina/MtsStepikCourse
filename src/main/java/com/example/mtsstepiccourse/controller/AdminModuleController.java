package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.ModuleToEditDto;
import com.example.mtsstepiccourse.mapper.ModuleDtoMapper;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.service.ModuleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/modules")
public class AdminModuleController {

    private final ModuleService moduleService;
    private final ModuleDtoMapper mapper;

    @PostMapping("")
    public void createModule(@Valid @RequestBody ModuleToEditDto moduleToEditDto) {
        Module module = new Module(moduleToEditDto);
        moduleService.create(module);
    }

    @PutMapping("/{id}")
    public void updateModule(@PathVariable Long id, @Valid @RequestBody ModuleToEditDto moduleToEditDto) {
        Module module = new Module(moduleToEditDto);
        moduleService.update(id, module);
    }

    @GetMapping("/{id}")
    public ModuleToEditDto getModule(@PathVariable Long id) {
        return mapper.moduleToModuleToEditDto(moduleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable Long id) {
        moduleService.delete(id);
    }

}
