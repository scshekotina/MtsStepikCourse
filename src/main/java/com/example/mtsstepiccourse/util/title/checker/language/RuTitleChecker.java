package com.example.mtsstepiccourse.util.title.checker.language;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import com.example.mtsstepiccourse.util.title.checker.BaseTitleChecker;
import org.springframework.stereotype.Component;

@Component
public class RuTitleChecker extends BaseTitleChecker {

    public static final String CHECKED_NON_RUSSIAN_SYMBOLS = "Checked non-russian symbols";
    public static final String FIRST_WORD_IS_NOT_UPPER_CASE = "First letter in first word is not upper-case";
    public static final String FIRST_UPPER_CASE_LETTER_IN_NON_FIRST_WORD = "First upper-case letter in non first word";

    @Override
    public void checkTitle(CharSequence value) {

        if (LanguageIdentifier.identifyLanguage(value) != Language.RU) {
            throw new DtoValidationException(CHECKED_NON_RUSSIAN_SYMBOLS);
        }

        String[] words = value.toString().split("[\s\n\t\r]");
        char firstLetter = words[0].toCharArray()[0];
        if (Character.isLowerCase(firstLetter)) {
            throw new DtoValidationException(FIRST_WORD_IS_NOT_UPPER_CASE);
        }
        for (int i = 1; i < words.length; i++) {
            if (Character.isUpperCase(words[i].toCharArray()[0])) {
                throw new DtoValidationException(FIRST_UPPER_CASE_LETTER_IN_NON_FIRST_WORD);
            }
        }
        super.checkTitle(value);
    }
}
