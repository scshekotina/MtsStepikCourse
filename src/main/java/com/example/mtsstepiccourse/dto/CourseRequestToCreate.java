package com.example.mtsstepiccourse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseRequestToCreate {

    @NotBlank(message = "Course author has to be filled")
    private String author;

    @NotBlank(message = "Course title has to be filled")
    private String title;
}
