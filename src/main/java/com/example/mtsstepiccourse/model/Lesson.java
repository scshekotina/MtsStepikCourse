package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.dto.LessonToEditDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    private LocalDateTime creatingDate;
    @ManyToOne
    private User creatingAuthor;

    private LocalDateTime updatingDate;
    @ManyToOne
    private User updatingAuthor;

    private LocalDateTime deletingDate;
    @ManyToOne
    private User deletingAuthor;

    public Lesson(Long id) {
        this.id = id;
    }

    public Lesson(LessonToEditDto lessonDto) {
        this.title = lessonDto.getTitle();
        this.text = lessonDto.getText();
        if (lessonDto.getModuleId() != null) {
            this.module = new Module(lessonDto.getModuleId());
        }
    }
}
