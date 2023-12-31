package com.example.mtsstepiccourse.dto;

import com.example.mtsstepiccourse.util.title.TitleCase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CourseToEditDto {

    @NotBlank(message = "Course author had to be filled")
    private String author;

    @TitleCase(mode = TitleCaseMode.RU, message = "Title should write in russian title case mode")
    @NotBlank(message = "Course title had to be filled")
    private String title;

    private List<Long> moduleIds;

}
