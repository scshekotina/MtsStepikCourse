package com.example.mtsstepiccourse.util.title.checker.language;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import com.example.mtsstepiccourse.util.title.checker.BaseTitleChecker;
import org.springframework.stereotype.Component;

@Component
public class EnTitleChecker extends BaseTitleChecker {

    public static final String[] LOWER_CASE_WORDS = {"a", "but", "for", "or", "not", "the", "an"};
    public static final String CHECKED_NON_ENGLISH_SYMBOLS = "Checked non-english symbols";
    public static final String FIRST_LETTER_ON_FIRST_OR_LAST_WORD_IS_NOT_UPPER_CASE = "First letter on first or last word is not upper-case";
    public static final String FIRST_LETTER_IN_SOME_WORD_IS_NOT_UPPER_CASE = "First letter in some word is not upper-case";

    @Override
    public void checkTitle(CharSequence value) {
        if (LanguageIdentifier.identifyLanguage(value) != Language.EN) {
            throw new DtoValidationException(CHECKED_NON_ENGLISH_SYMBOLS);
        }

        String[] words = value.toString().split("[\s\n\t\r]");
        char firstLetterOfFirstWord = words[0].toCharArray()[0];
        char firstLetterInLastWord = words[words.length - 1].toCharArray()[0];
        if (Character.isLowerCase(firstLetterOfFirstWord) || Character.isLowerCase(firstLetterInLastWord)) {
            throw new DtoValidationException(FIRST_LETTER_ON_FIRST_OR_LAST_WORD_IS_NOT_UPPER_CASE);
        }
        for (int i = 1; i < words.length-1; i++) {
            if (!isLowerCaseWord(words[i])) {
                char fistLetter = words[i].toCharArray()[0];
                if (Character.isLowerCase(fistLetter)) {
                    throw new DtoValidationException(FIRST_LETTER_IN_SOME_WORD_IS_NOT_UPPER_CASE);
                }
            }
        }
        super.checkTitle(value);
    }

    private boolean isLowerCaseWord(String word) {
        for (String lowerCaseWord : LOWER_CASE_WORDS) {
            if (word.toLowerCase().equals(lowerCaseWord)) {
                return true;
            }
        }
        return false;
    }
}
