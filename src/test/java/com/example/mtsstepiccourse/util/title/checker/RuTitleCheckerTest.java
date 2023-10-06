package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.util.title.checker.language.RuTitleChecker;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@NoArgsConstructor
public class RuTitleCheckerTest {

    @Autowired
    private RuTitleChecker checker;

    @Test
    public void checkTitle() {
        assertThat(checker.checkTitle("Это корректный заголовок")).isTrue();
        assertThat(checker.checkTitle("а это некорректный")).isFalse();
        assertThat(checker.checkTitle("Это тоже Некорректный")).isFalse();
        assertThat(checker.checkTitle("А это коРРЕКТНЫЙ")).isTrue();
        assertThat(checker.checkTitle("а это неКОРРЕКТНЫЙ")).isFalse();
        assertThat(checker.checkTitle("Смешение en языков")).isFalse();
    }
}