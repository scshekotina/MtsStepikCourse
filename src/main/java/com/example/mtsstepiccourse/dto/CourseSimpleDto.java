package com.example.mtsstepiccourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseSimpleDto {

    private Long id;
    private String author;
    private String title;
}
