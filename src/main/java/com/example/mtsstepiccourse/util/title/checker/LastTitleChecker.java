package com.example.mtsstepiccourse.util.title.checker;

import org.springframework.stereotype.Component;

@Component
public class LastTitleChecker extends BaseTitleChecker {
    @Override
    public boolean checkTitle(CharSequence value) {
        return true;
    }
}
