package com.example.mtsstepiccourse.util.title.checker;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AllowedSymbolsTitleChecker extends BaseTitleChecker {
    @Override
    public boolean checkTitle(CharSequence value) {

        Matcher matcher = Pattern.compile("[a-zA-Zа-яА-Я\s\"',:]+").matcher(value);
        matcher.find();

        if (matcher.start() != 0 || matcher.end() != value.length()) {
            return false;
        }
        return super.checkTitle(value);
    }
}
