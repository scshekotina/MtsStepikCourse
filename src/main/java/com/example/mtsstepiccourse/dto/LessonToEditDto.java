package com.example.mtsstepiccourse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonToEditDto {

    @NotBlank(message = "Lesson's title had to be filled")
    private String title;
    private String text;
    private Long moduleId;

}
