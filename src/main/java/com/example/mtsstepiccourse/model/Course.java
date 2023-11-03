package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.dto.CourseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name="courses")
public class Course {

    public Course(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Course(CourseDto dto) {
        this.id = dto.getId();
        this.author = dto.getAuthor();
        this.title = dto.getTitle();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course author has to be filled")
    @Column
    private String author;

    @NotBlank(message = "Course title has to be filled")
    @Column
    private String title;

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @ManyToMany
    private Set<User> users;

    public void addLesson(Lesson lesson) {
        lesson.setCourse(this);
        this.lessons.add(lesson);
    }
}
