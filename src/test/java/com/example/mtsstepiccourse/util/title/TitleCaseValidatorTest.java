package com.example.mtsstepiccourse.util.title;

import com.example.mtsstepiccourse.dto.TitleCaseMode;
import jakarta.validation.*;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@NoArgsConstructor
class TitleCaseValidatorTest {

    @Autowired
    private TitleCaseValidator validator;

    @Test
    void isValidRu() {
        validator.initialize(getTitleCase(TitleCaseMode.RU));
        assertThat(validator.isValid("Русский текст с большой буквы", null)).isTrue();
        assertThat(validator.isValid("Русский", null)).isTrue();
        assertThat(validator.isValid("Русский с разрешенными символами:,\"'", null)).isTrue();
        assertThat(validator.isValid("Смешение languages", null)).isFalse();
        assertThat(validator.isValid("русский текст с маленькой буквы", null)).isFalse();
        assertThat(validator.isValid(" Первый пробел", null)).isFalse();
        assertThat(validator.isValid("Последний пробел ", null)).isFalse();
        assertThat(validator.isValid("Много пробелов  подряд", null)).isFalse();
        assertThat(validator.isValid("Запрещенные символы!", null)).isFalse();
        assertThat(validator.isValid("Запрещенные цифры1", null)).isFalse();
        assertThat(validator.isValid("Запрещенные символы\n", null)).isFalse();
    }

    @Test
    void isValidEn() {
        validator.initialize(getTitleCase(TitleCaseMode.EN));
        assertThat(validator.isValid("English Correct Text", null)).isTrue();
        assertThat(validator.isValid("English Text With or an The", null)).isTrue();
        assertThat(validator.isValid("English With Symbs:,\"'", null)).isTrue();
        assertThat(validator.isValid("Смешение languages", null)).isFalse();
        assertThat(validator.isValid("english First Small Letter", null)).isFalse();
        assertThat(validator.isValid("English Last Small letter", null)).isFalse();
        assertThat(validator.isValid("English small Letter In the Middle", null)).isFalse();
        assertThat(validator.isValid(" First Space", null)).isFalse();
        assertThat(validator.isValid("Last Space ", null)).isFalse();
        assertThat(validator.isValid("Lots of   Spaces", null)).isFalse();
        assertThat(validator.isValid("Prohibited Symbs!", null)).isFalse();
        assertThat(validator.isValid("ProhibitedDidgits1", null)).isFalse();
        assertThat(validator.isValid("Prohibited Symbs\n", null)).isFalse();
    }

    @Test
    void isValidAny() {
        validator.initialize(getTitleCase(TitleCaseMode.ANY));
        assertThat(validator.isValid("English Correct Text", null)).isTrue();
        assertThat(validator.isValid("English Text With or an The", null)).isTrue();
        assertThat(validator.isValid("English With Symbs:,\"'", null)).isTrue();
        assertThat(validator.isValid("Русский текст с большой буквы", null)).isTrue();
        assertThat(validator.isValid("Русский", null)).isTrue();
        assertThat(validator.isValid("Русский с разрешенными символами:,\"'", null)).isTrue();

        assertThat(validator.isValid("Смешение languages", null)).isFalse();

        assertThat(validator.isValid("русский текст с маленькой буквы", null)).isFalse();
        assertThat(validator.isValid(" Первый пробел", null)).isFalse();
        assertThat(validator.isValid("Последний пробел ", null)).isFalse();
        assertThat(validator.isValid("Много пробелов  подряд", null)).isFalse();
        assertThat(validator.isValid("Запрещенные символы!", null)).isFalse();
        assertThat(validator.isValid("Запрещенные цифры1", null)).isFalse();
        assertThat(validator.isValid("Запрещенные символы\n", null)).isFalse();

        assertThat(validator.isValid("english First Small Letter", null)).isFalse();
        assertThat(validator.isValid("English Last Small letter", null)).isFalse();
        assertThat(validator.isValid("English small Letter In the Middle", null)).isFalse();
        assertThat(validator.isValid(" First Space", null)).isFalse();
        assertThat(validator.isValid("Last Space ", null)).isFalse();
        assertThat(validator.isValid("Lots of   Spaces", null)).isFalse();
        assertThat(validator.isValid("Prohibited Symbs!", null)).isFalse();
        assertThat(validator.isValid("ProhibitedDidgits1", null)).isFalse();
        assertThat(validator.isValid("Prohibited Symbs\n", null)).isFalse();

        assertThat(validator.isValid("Текст По Правилам Английского", null)).isFalse();
        assertThat(validator.isValid("Text in russian style", null)).isFalse();

    }

    private TitleCase getTitleCase(TitleCaseMode mode) {
        return new TitleCase(){

            @Override
            public TitleCaseMode mode() {
                return mode;
            }

            @Override
            public String message() {
                return "Title should write in title case mode";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }
        };
    }

}