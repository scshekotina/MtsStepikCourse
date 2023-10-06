package com.example.mtsstepiccourse.util.title.checker.language;

import com.example.mtsstepiccourse.util.title.checker.BaseTitleChecker;
import org.springframework.stereotype.Component;

@Component
public class RuTitleChecker extends BaseTitleChecker {
    @Override
    public boolean checkTitle(CharSequence value) {

        if (LanguageIdentifier.identifyLanguage(value) != Language.RU) {
            return false;
        }

        String[] words = value.toString().split("[\s\n\t\r]");
        char firstLetter = words[0].toCharArray()[0];
        if (Character.isLowerCase(firstLetter)) {
            return false;
        }
        for (int i = 1; i < words.length; i++) {
            if (Character.isUpperCase(words[i].toCharArray()[0])) {
                return false;
            }
        }
        return super.checkTitle(value);
    }
}
