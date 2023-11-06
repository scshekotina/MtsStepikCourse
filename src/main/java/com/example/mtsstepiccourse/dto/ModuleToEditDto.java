package com.example.mtsstepiccourse.dto;

import com.example.mtsstepiccourse.model.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModuleToEditDto {
    private String title;
    private String text;
    private Long courseId;
    private List<Lesson> lessons;
}
