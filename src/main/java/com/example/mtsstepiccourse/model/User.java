package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @ManyToMany(mappedBy = "users")
    private Set<Course> courses;

    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.username = userDto.getUsername();
    }

    public void addCourse(Course course) {
        course.getUsers().add(this);
        courses.add(course);
    }

    public void removeCourse(Course course) {
        course.getUsers().remove(this);
        courses.remove(course);
    }
}
