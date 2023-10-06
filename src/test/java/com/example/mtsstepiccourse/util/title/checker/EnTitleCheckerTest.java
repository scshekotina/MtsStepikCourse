package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.util.title.checker.language.EnTitleChecker;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@NoArgsConstructor
public class EnTitleCheckerTest {

    @Autowired
    private EnTitleChecker checker;

    @Test
    public void checkTitle() {
        assertThat(checker.checkTitle("Correct Very Long Title")).isTrue();
        assertThat(checker.checkTitle("Correct Title With for In the Middle")).isTrue();
        assertThat(checker.checkTitle("Correct Title With Ended A")).isTrue();
        assertThat(checker.checkTitle("For a Start Correct Example")).isTrue();
        assertThat(checker.checkTitle("first Word Small")).isFalse();
        assertThat(checker.checkTitle("Last Word small")).isFalse();
        assertThat(checker.checkTitle("Middle word Small")).isFalse();
        assertThat(checker.checkTitle("Mixed Languages Неверный Example")).isFalse();
    }
}