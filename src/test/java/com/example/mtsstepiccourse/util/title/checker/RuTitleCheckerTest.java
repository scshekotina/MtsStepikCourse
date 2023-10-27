package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import com.example.mtsstepiccourse.util.title.checker.language.RuTitleChecker;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@NoArgsConstructor
@ActiveProfiles("test")
public class RuTitleCheckerTest {

    @Autowired
    private RuTitleChecker checker;

    @Test
    public void checkTitle() {
        assertThatCode(() -> checker.checkTitle("Это корректный заголовок")).doesNotThrowAnyException();
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("а это некорректный"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("Это тоже Некорректный"));
        assertThatCode(() -> checker.checkTitle("А это коРРЕКТНЫЙ")).doesNotThrowAnyException();
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("а это неКОРРЕКТНЫЙ"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("Смешение en языков"));
    }
}