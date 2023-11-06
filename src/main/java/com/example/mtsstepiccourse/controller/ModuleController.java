package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.ModuleDto;
import com.example.mtsstepiccourse.mapper.ModuleDtoMapper;
import com.example.mtsstepiccourse.service.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/modules")
public class ModuleController {

    private final ModuleService moduleService;
    private final ModuleDtoMapper mapper;

    @GetMapping("/{id}")
    public ModuleDto getModule(@PathVariable Long id) {
        return mapper.moduleToModuleDto(moduleService.findById(id));
    }
}
