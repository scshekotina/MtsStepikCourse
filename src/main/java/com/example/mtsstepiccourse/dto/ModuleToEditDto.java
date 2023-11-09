package com.example.mtsstepiccourse.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Module's title had to be filled")
    private String title;
    private String text;
    private Long courseId;
    private List<Long> lessonIds;
}
