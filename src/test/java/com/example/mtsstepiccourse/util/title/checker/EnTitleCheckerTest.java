package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import com.example.mtsstepiccourse.util.title.checker.language.EnTitleChecker;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@NoArgsConstructor
public class EnTitleCheckerTest {

    @Autowired
    private EnTitleChecker checker;

    @Test
    public void checkTitle() {
        assertThatCode(() -> checker.checkTitle("Correct Very Long Title")).doesNotThrowAnyException();
        assertThatCode(() -> checker.checkTitle("Correct Title With for In the Middle")).doesNotThrowAnyException();
        assertThatCode(() -> checker.checkTitle("Correct Title With Ended A")).doesNotThrowAnyException();
        assertThatCode(() -> checker.checkTitle("For a Start Correct Example")).doesNotThrowAnyException();
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("first Word Small"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("Last Word small"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("Middle word Small"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("Mixed Languages Неверный Example"));
    }
}