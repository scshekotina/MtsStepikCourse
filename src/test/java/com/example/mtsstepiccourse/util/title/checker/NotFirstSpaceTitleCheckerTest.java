package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
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
public class NotFirstSpaceTitleCheckerTest {

    @Autowired
    private NotFirstSpaceTitleChecker checker;

    @Test
    public void checkTitle() {
        assertThatCode(() -> checker.checkTitle("ewfw erfe")).doesNotThrowAnyException();
        assertThatCode(() -> checker.checkTitle("frserf\n erw fg ")).doesNotThrowAnyException();
        assertThrows(DtoValidationException.class, () -> checker.checkTitle(" frserf\terw fg"));
        assertThrows(DtoValidationException.class, () -> checker.checkTitle("  frserf\r erw fg"));
    }
}