package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.ModuleDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/modules")
public class ModuleController {

    @GetMapping("/{id}")
    public ModuleDto getModule(@PathVariable Long id) {
        return null;
    }
}
