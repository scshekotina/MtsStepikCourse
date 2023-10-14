package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import org.springframework.stereotype.Component;

@Component
public class NotFirstSpaceTitleChecker extends BaseTitleChecker {

    public static final String SPACE_COULDN_T_BE_FIRST = "Space couldn't be first";

    @Override
    public void checkTitle(CharSequence value) {
        if (value.charAt(0) == ' ') {
            throw new DtoValidationException(SPACE_COULDN_T_BE_FIRST);
        }
        super.checkTitle(value);
    }
}
