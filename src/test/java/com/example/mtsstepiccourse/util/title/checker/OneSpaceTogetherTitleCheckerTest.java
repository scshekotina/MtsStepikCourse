package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@NoArgsConstructor
public class OneSpaceTogetherTitleCheckerTest {

    @Autowired
    private OneSpaceTogetherTitleChecker checker;

    @Test
    public void checkTitle() {
        assertThatCode(() -> checker.checkTitle("ewfw erfe")).doesNotThrowAnyException();
        assertThatCode(() -> checker.checkTitle(" ewfw erfe")).doesNotThrowAnyException();
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("ewfw erfe  "));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("ewfw  erfe"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("ewfw   erfe"));
    }
}