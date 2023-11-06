package com.example.mtsstepiccourse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModuleDtoToEdit {
    private String title;
    private String text;
    private Long courseId;
}
