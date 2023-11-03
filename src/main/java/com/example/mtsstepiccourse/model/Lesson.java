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
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Lob
    @Column
    private String text;

    @ManyToOne(optional = false)
    private Course course;

    public Lesson(LessonDto lessonDto, Course course) {
        this.id = lessonDto.getId();
        this.title = lessonDto.getTitle();
        this.text = lessonDto.getText();
        this.course = course;
    }
}
