package com.example.mtsstepiccourse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModuleDto {
    private Long id;
    private String title;
    private String text;
    private CourseSimpleDto course;
    private List<LessonSimpleDto> lessons;
}
