package com.example.mtsstepiccourse.util.title.checker;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ProhibitedSymbolsTitleChecker extends BaseTitleChecker {
    @Override
    public boolean checkTitle(CharSequence value) {
        boolean prohibitedSymbolsDetected = Pattern.compile(".*[\n\t\r].*").matcher(value).matches();
        if (prohibitedSymbolsDetected) {
            return false;
        }
        return super.checkTitle(value);
    }
}
