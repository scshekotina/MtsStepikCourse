package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c join fetch c.lessons where c.id=:id")
    @Transactional
    Optional<Course> findByIdWithLessons(@Param("id") Long id);

    @Query("select c from Course c left join fetch c.users where c.id=:id")
    Optional<Course> findByIdWithUsers(@Param("id") Long id);


    List<Course> findByTitleLike(String title);
}
