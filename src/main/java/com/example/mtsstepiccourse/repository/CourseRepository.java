package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends UpdatableEntityRepository<Course> {

    @Transactional
    @Query("select distinct c from Course c left join fetch c.modules m left join m.lessons l " +
            "where c.id=:id and c.deletingDate is NULL and m.deletingDate is null and l.deletingDate is null")
    Optional<Course> findByIdWithLessons(@Param("id") Long id);

    @Query("select c from Course c left join fetch c.users where c.id=:id")
    Optional<Course> findByIdWithUsers(@Param("id") Long id);

    List<Course> findAllByDeletingDateIsNullOrderByTitle();

    List<Course> findByTitleLike(String title);
}
