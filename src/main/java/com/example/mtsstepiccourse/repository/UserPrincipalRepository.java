package com.example.mtsstepiccourse.repository;

import com.example.mtsstepiccourse.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPrincipalRepository extends JpaRepository<UserPrincipal, Long> {

    Optional<UserPrincipal> findByUsername(String username);

}
