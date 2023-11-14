package com.example.mtsstepiccourse.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class UserPrincipal {
    @Id
    private Long id;
    private String username;
    private String password;

    @ManyToMany(mappedBy = "userPrincipal")
    private Set<Role> roles = new HashSet<>();

}
