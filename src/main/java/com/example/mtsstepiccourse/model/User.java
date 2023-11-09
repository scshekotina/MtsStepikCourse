package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.dto.UserToEditDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
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
    @NotBlank(message = "Username has to be filled")
    private String username;

    @Column
    @NotBlank(message = "Password has to be filled")
    @Size(min = 5, message = "Minimum password length has to be 5 signs")
    private String password;

    @Column
    @NotBlank(message = "Firstname has to be filled")
    private String firstname;

    @Column
    @NotBlank(message = "Lastname has to be filled")
    private String lastname;

    @Column
    @NotBlank(message = "Email has to be filled")
    private String email;

    @ManyToMany(mappedBy = "users")
    private Set<Course> courses;

    private LocalDateTime registrationDate;

    private LocalDateTime updatingDate;
    @ManyToOne
    private User updatingAuthor;

    private LocalDateTime deletingDate;
    @ManyToOne
    private User deletingAuthor;

    public User(UserToEditDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.firstname = userDto.getFirstname();
        this.lastname = userDto.getLastname();
        this.email = userDto.getEmail();
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
