package com.example.mtsstepiccourse.util.title;

import com.example.mtsstepiccourse.model.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserUtil {
    private static final User user = new User();
    static {
        user.setId(1L);
        user.setUsername("admin");
    }

    public static User getCurrentUser() {
        return user;
    }
}
