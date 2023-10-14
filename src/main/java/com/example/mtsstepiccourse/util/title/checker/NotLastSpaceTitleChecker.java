package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import org.springframework.stereotype.Component;

@Component
public class NotLastSpaceTitleChecker extends BaseTitleChecker {

    public static final String SPACE_COULDN_T_BE_LAST = "Space couldn't be last";

    @Override
    public void checkTitle(CharSequence value) {
        if (value.charAt(value.length() - 1) == ' ') {
            throw new DtoValidationException(SPACE_COULDN_T_BE_LAST);
        }
        super.checkTitle(value);
    }
}
