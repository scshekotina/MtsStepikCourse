package com.example.mtsstepiccourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {

    private Long id;
    private String author;
    private String title;
    private List<ModuleDto> modules;
}
