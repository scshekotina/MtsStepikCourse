package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.dto.CourseToEditDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="courses")
public class Course extends UpdatableAndDeletableEntityWithCreatingData{

    public Course(Long id) {
        super(id);
    }

    public Course(String author, String title) {
        super();
        this.author = author;
        this.title = title;
    }

    public Course(CourseToEditDto dto) {
        super();
        this.author = dto.getAuthor();
        this.title = dto.getTitle();
        if (dto.getModules() != null) {
            this.modules = new ArrayList<>();
            dto.getModules().forEach(module
                    -> this.modules.add(new Module(module.getId())));
        }
    }

    @NotBlank(message = "Course author has to be filled")
    @Column
    private String author;

    @NotBlank(message = "Course title has to be filled")
    @Column
    private String title;

    @OneToMany(mappedBy = "course")
    private List<Module> modules;

    @ManyToMany
    @JoinTable(
            name = "rating",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;


}
