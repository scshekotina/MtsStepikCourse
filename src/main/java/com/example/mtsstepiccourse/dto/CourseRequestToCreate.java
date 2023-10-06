package com.example.mtsstepiccourse.dto;

import com.example.mtsstepiccourse.util.title.TitleCase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseRequestToCreate {

    @NotBlank(message = "Course author had to be filled")
    private String author;

    @TitleCase(message = "Title should write in title case mode")
    @NotBlank(message = "Course title had to be filled")
    private String title;
}
