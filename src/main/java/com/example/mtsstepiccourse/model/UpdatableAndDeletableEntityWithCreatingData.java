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
public abstract class UpdatableAndDeletableEntityWithCreatingData extends UpdatableAndDeletableEntity {

    private LocalDateTime creatingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User creatingAuthor;

    public UpdatableAndDeletableEntityWithCreatingData(Long id) {
        super(id);
        markAsCreatedAndUpdated();
    }

    public void markAsCreated(User user, LocalDateTime dateTime) {
        setCreatingAuthor(user);
        setCreatingDate(dateTime);
    }

    public void markAsCreatedAndUpdated() {
        markAsCreated(UserUtil.getCurrentUser(), LocalDateTime.now());
        super.markAsUpdated();
    }

}
