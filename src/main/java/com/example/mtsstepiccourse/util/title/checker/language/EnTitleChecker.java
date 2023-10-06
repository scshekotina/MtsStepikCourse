package com.example.mtsstepiccourse.util.title.checker.language;

import com.example.mtsstepiccourse.util.title.checker.BaseTitleChecker;
import org.springframework.stereotype.Component;

@Component
public class EnTitleChecker extends BaseTitleChecker {

    public static final String[] LOWER_CASE_WORDS = {"a", "but", "for", "or", "not", "the", "an"};

    @Override
    public boolean checkTitle(CharSequence value) {
        if (LanguageIdentifier.identifyLanguage(value) != Language.EN) {
            return false;
        }

        String[] words = value.toString().split("[\s\n\t\r]");
        char firstLetterOfFirstWord = words[0].toCharArray()[0];
        char firstLetterInLastWord = words[words.length - 1].toCharArray()[0];
        if (Character.isLowerCase(firstLetterOfFirstWord) || Character.isLowerCase(firstLetterInLastWord)) {
            return false;
        }
        for (int i = 1; i < words.length-1; i++) {
            if (!isLowerCaseWord(words[i])) {
                char fistLetter = words[i].toCharArray()[0];
                if (Character.isLowerCase(fistLetter)) {
                    return false;
                }
            }
        }
        return super.checkTitle(value);
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
