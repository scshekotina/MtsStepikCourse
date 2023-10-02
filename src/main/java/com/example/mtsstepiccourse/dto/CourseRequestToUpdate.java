package com.example.mtsstepiccourse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseRequestToUpdate {

    @NotBlank(message = "Course author had to be filled")
    private String author;

    @NotBlank(message = "Course title had to be filled")
    private String title;
}
