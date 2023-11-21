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
public abstract class UpdatableAndDeletableEntityWithCreatingData extends UpdatableAndDeletableEntity {

    private LocalDateTime creatingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserPrincipal creatingAuthor;

    public UpdatableAndDeletableEntityWithCreatingData() {
        super();
    }

    public UpdatableAndDeletableEntityWithCreatingData(Long id) {
        super(id);
    }

}
