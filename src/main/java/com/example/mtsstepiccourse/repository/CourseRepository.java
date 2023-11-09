package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends UpdatableEntityRepository<Course> {

    @Query("select c from Course c left join fetch c.modules m left join m.lessons " +
            "where c.id=:id and c.deletingDate is NULL")
    Optional<Course> findByIdWithLessons(@Param("id") Long id);

    @Query("select c from Course c left join fetch c.users where c.id=:id")
    Optional<Course> findByIdWithUsers(@Param("id") Long id);

    List<Course> findAllByDeletingDateIsNullOrderByTitle();

    List<Course> findByTitleLike(String title);
}
