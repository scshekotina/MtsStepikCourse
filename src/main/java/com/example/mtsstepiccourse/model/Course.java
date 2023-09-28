package com.example.mtsstepiccourse.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Course {

    public Course(String author, String title) {
        this.author = author;
        this.title = title;
    }

    private Long id;
    private String author;
    private String title;


}
