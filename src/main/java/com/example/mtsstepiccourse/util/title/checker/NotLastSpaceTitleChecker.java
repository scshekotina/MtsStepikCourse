package com.example.mtsstepiccourse.util.title.checker;

import org.springframework.stereotype.Component;

@Component
public class NotLastSpaceTitleChecker extends BaseTitleChecker {
    @Override
    public boolean checkTitle(CharSequence value) {
        if (value.charAt(value.length() - 1) == ' ') {
            return false;
        }
        return super.checkTitle(value);
    }
}
