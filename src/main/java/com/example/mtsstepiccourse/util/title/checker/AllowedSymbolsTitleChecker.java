package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AllowedSymbolsTitleChecker extends BaseTitleChecker {

    public static final String CHECKED_NOT_ALLOWED_SYMBOLS = "Checked not allowed symbols";

    @Override
    public void checkTitle(CharSequence value) {

        Matcher matcher = Pattern.compile("[a-zA-Zа-яА-Я\s\"',:]+").matcher(value);
        matcher.find();

        if (matcher.start() != 0 || matcher.end() != value.length()) {
            throw new DtoValidationException(CHECKED_NOT_ALLOWED_SYMBOLS);
        }
        super.checkTitle(value);
    }
}
