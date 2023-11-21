package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.security.UserPrincipal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class UpdatableAndDeletableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime updatingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserPrincipal updatingAuthor;

    private LocalDateTime deletingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserPrincipal deletingAuthor;

    public UpdatableAndDeletableEntity() {
    }

    public UpdatableAndDeletableEntity(Long id) {
        this.id = id;
    }
}
