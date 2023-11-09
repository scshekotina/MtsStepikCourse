package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"courses"})
    @Query("select u from User u where u.id=:id")
    Optional<User> findByIdWithCourses(@Param("id") Long id);

    List<User> findAllByDeletingDateIsNullOrderByUsername();

}
