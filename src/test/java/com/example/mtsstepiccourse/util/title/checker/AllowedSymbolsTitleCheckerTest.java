package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@NoArgsConstructor
public class AllowedSymbolsTitleCheckerTest {

    @Autowired
    private AllowedSymbolsTitleChecker checker;

    @Test
    public void checkTitle() {
        assertThatCode(() -> checker.checkTitle("юЮю rrr,")).doesNotThrowAnyException();
        assertThatCode(() -> checker.checkTitle("fF: пп")).doesNotThrowAnyException();
        assertThatCode(() -> checker.checkTitle("юfю\"fюf")).doesNotThrowAnyException();
        assertThatCode(() -> checker.checkTitle("'ююю'")).doesNotThrowAnyException();
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("ffff@sfg"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("ffff1sfg"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("ffff.sfg"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("ffff\\sfg"));
    }
}