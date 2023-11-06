package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.dto.LessonDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Lob
    @Column(name="content")
    private String text;

    @ManyToOne
    private Module module;

    public Lesson(LessonDto lessonDto, Module module) {
        this.id = lessonDto.getId();
        this.title = lessonDto.getTitle();
        this.text = lessonDto.getText();
        this.module = module;
    }
}
