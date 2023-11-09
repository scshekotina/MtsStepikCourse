package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.dto.ModuleToEditDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="modules")
public class Module extends UpdatableAndDeletableEntityWithCreatingData {
    @Column
    private String title;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "module", fetch = FetchType.EAGER)
    private List<Lesson> lessons;

    public Module() {
        super();
    }

    public Module(Long id) {
        super(id);
    }

    public Module(ModuleToEditDto dto) {
        super();
        this.title = dto.getTitle();
        if (dto.getCourseId() != null) {
            this.course = new Course();
            this.course.setId(dto.getCourseId());
        }
        if (dto.getLessonIds() != null) {
            this.lessons = new ArrayList<>();
            dto.getLessonIds().forEach(id ->
                    this.lessons.add(new Lesson(id)));
        }
    }
}
