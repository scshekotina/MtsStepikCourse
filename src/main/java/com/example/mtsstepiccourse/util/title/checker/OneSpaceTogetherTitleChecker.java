package com.example.mtsstepiccourse.util.title.checker;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class OneSpaceTogetherTitleChecker extends BaseTitleChecker {
    @Override
    public boolean checkTitle(CharSequence value) {
        boolean moreThanOneSpaceTogetherDetected = Pattern.compile(".*\s{2,}.*").matcher(value).matches();
        if (moreThanOneSpaceTogetherDetected) {
            return false;
        }
        return super.checkTitle(value);
    }
}
