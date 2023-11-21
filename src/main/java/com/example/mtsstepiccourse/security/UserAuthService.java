package com.example.mtsstepiccourse.security;

import com.example.mtsstepiccourse.model.UpdatableAndDeletableEntity;
import com.example.mtsstepiccourse.model.UpdatableAndDeletableEntityWithCreatingData;
import com.example.mtsstepiccourse.repository.UserPrincipalRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class UserAuthService implements UserDetailsService {

    private final UserPrincipalRepository userPrincipalRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPrincipalRepository.findByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getName()))
                                .toList()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void markAsDeleted(UpdatableAndDeletableEntity entity) {
        UserPrincipal user = userPrincipalRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        entity.setDeletingAuthor(user);
        entity.setDeletingDate(LocalDateTime.now());
    }

    public void markAsUpdated(UpdatableAndDeletableEntity entity) {
        UserPrincipal user = userPrincipalRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        entity.setUpdatingAuthor(user);
        entity.setUpdatingDate(LocalDateTime.now());
    }

    public void markAsCreated(UpdatableAndDeletableEntityWithCreatingData entity, UserPrincipal updatedAuthor, LocalDateTime updatingDateTime) {
        entity.setCreatingAuthor(updatedAuthor);
        entity.setCreatingDate(updatingDateTime);
    }

    public void markAsCreated(UpdatableAndDeletableEntityWithCreatingData entity) {
        UserPrincipal user = userPrincipalRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        markAsCreated(entity, user, LocalDateTime.now());
    }
}
