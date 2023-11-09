package com.example.mtsstepiccourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseOnlyModulesDto {

    private Long id;
    private String author;
    private String title;
    private List<ModuleSimpleDto> modules;
}
