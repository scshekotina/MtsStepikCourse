package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class OneSpaceTogetherTitleChecker extends BaseTitleChecker {

    public static final String MORE_THAN_ONE_SPACE_TOGETHER_ARE_DETECTED = "More than one space together are detected";

    @Override
    public void checkTitle(CharSequence value) {
        boolean moreThanOneSpaceTogetherDetected = Pattern.compile(".*\s{2,}.*").matcher(value).matches();
        if (moreThanOneSpaceTogetherDetected) {
            throw new DtoValidationException(MORE_THAN_ONE_SPACE_TOGETHER_ARE_DETECTED);
        }
        super.checkTitle(value);
    }
}
