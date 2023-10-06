package com.example.mtsstepiccourse.util.title.checker;

import org.springframework.stereotype.Component;

@Component
public class NotFirstSpaceTitleChecker extends BaseTitleChecker {
    @Override
    public boolean checkTitle(CharSequence value) {
        if (value.charAt(0) == ' ') {
            return false;
        }
        return super.checkTitle(value);
    }
}
