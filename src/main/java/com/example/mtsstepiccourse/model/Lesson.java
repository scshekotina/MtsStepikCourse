package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.dto.LessonToEditDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="lessons")
public class Lesson extends UpdatableAndDeletableEntityWithCreatingData {

    @Column
    private String title;

    @Lob
    @Column(name="content")
    private String text;

    @ManyToOne
    private Module module;

    public Lesson() {
        super();
    }

    public Lesson(Long id) {
        super(id);
    }

    public Lesson(LessonToEditDto lessonDto) {
        super();
        this.title = lessonDto.getTitle();
        this.text = lessonDto.getText();
        if (lessonDto.getModuleId() != null) {
            this.module = new Module(lessonDto.getModuleId());
        }
    }
}
