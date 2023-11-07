package com.example.mtsstepiccourse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserToEditDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
}
