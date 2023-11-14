package com.example.mtsstepiccourse.security;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="role_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="user_id", referencedColumnName="id"))
    private Set<UserPrincipal> userPrincipal;

    public Role(String name) {
        this.name = name;
    }
}
