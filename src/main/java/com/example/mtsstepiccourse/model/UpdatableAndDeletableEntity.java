package com.example.mtsstepiccourse.model;

import com.example.mtsstepiccourse.util.title.UserUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
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
    private User updatingAuthor;

    private LocalDateTime deletingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User deletingAuthor;

    public UpdatableAndDeletableEntity(Long id) {
        this.id = id;
        markAsUpdated();
    }

    public void markAsUpdated(){
        setUpdatingAuthor(UserUtil.getCurrentUser());
        setUpdatingDate(LocalDateTime.now());
    }

    public void markAsDeleted() {
        setDeletingDate(LocalDateTime.now());
        setDeletingAuthor(UserUtil.getCurrentUser());
    }
}
