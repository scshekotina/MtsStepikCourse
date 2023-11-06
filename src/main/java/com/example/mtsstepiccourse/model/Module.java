package com.example.mtsstepiccourse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "module")
    private List<Lesson> lessons;

    public void addLesson(Lesson lesson) {
        lesson.setModule(this);
        this.lessons.add(lesson);
    }
}
