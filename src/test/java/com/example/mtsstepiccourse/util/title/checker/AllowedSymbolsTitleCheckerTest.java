package com.example.mtsstepiccourse.util.title.checker;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@NoArgsConstructor
public class AllowedSymbolsTitleCheckerTest {

    @Autowired
    private AllowedSymbolsTitleChecker checker;

    @Test
    public void checkTitle() {
        assertThat(checker.checkTitle("юЮю rrr,")).isTrue();
        assertThat(checker.checkTitle("fF: пп")).isTrue();
        assertThat(checker.checkTitle("юfю\"fюf")).isTrue();
        assertThat(checker.checkTitle("'ююю'")).isTrue();
        assertThat(checker.checkTitle("ffff@sfg")).isFalse();
        assertThat(checker.checkTitle("ffff1sfg")).isFalse();
        assertThat(checker.checkTitle("ffff.sfg")).isFalse();
        assertThat(checker.checkTitle("ffff\\sfg")).isFalse();
    }
}