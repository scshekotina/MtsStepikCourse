package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c left join fetch c.modules m left join m.lessons " +
            "where c.id=:id and c.deletingDate is NULL")
    Optional<Course> findByIdWithLessons(@Param("id") Long id);

    @Query("select c from Course c left join fetch c.users where c.id=:id")
    Optional<Course> findByIdWithUsers(@Param("id") Long id);

    Optional<Course> findByIdAndDeletingDateIsNull(@Param("id") Long id);

    List<Course> findAllByDeletingDateIsNullOrderByTitle();

    List<Course> findByTitleLike(String title);
}
