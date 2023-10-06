package com.example.mtsstepiccourse.util.title.checker;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@NoArgsConstructor
public class ProhibitedSymbolsTitleCheckerTest {

    @Autowired
    private ProhibitedSymbolsTitleChecker checker;

    @Test
    public void checkTitle() {
        assertThat(checker.checkTitle("ewfw erfe")).isTrue();
        assertThat(checker.checkTitle("frserf\n erw fg")).isFalse();
        assertThat(checker.checkTitle("frserf\terw fg")).isFalse();
        assertThat(checker.checkTitle("frserf\r erw fg")).isFalse();
        assertThat(checker.checkTitle("\nfrserf erw fg")).isFalse();
        assertThat(checker.checkTitle("frserf erw fg\t")).isFalse();
    }
}