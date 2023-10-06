package com.example.mtsstepiccourse.util.title.checker.language;

import java.util.regex.Pattern;

public class LanguageIdentifier {

    public static Language identifyLanguage(CharSequence text) {
        if (text == null) {
            return Language.NOT_DEFINED;
        }
        boolean containEngSymbol = Pattern.compile(".*[a-zA-Z]+.*").matcher(text).matches();
        boolean containRuSymbol = Pattern.compile(".*[а-яА-Я]+.*").matcher(text).matches();

        if (containEngSymbol) {
            if (containRuSymbol) {
                return Language.MIXED;
            }
            else {
                return Language.EN;
            }
        } else if (containRuSymbol) {
            return Language.RU;
        }
        return Language.NOT_DEFINED;
    }
}
