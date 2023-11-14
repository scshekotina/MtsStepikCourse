package com.example.mtsstepiccourse.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration{

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails student = User
                .withUsername("student")
                .passwordEncoder(this.passwordEncoder()::encode)
                .password("123")
                .roles("STUDENT")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password("admin")
                .roles("ADMIN")
                .passwordEncoder(this.passwordEncoder()::encode)
                .build();
        return new InMemoryUserDetailsManager(student, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
