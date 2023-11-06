package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.ModuleToEditDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/modules")
public class AdminModuleController {

    @PostMapping("}")
    public void createModule(@Valid @RequestBody ModuleToEditDto moduleToEditDto) {
    }

    @PutMapping("/{id}")
    public void updateModule(@PathVariable Long id, @Valid @RequestBody ModuleToEditDto moduleToEditDto) {
    }

    @GetMapping("/{id}")
    public ModuleToEditDto getModule(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable Long id) {

    }

}
