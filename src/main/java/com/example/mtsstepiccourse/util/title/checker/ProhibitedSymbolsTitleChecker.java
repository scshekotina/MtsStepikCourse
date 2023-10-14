package com.example.mtsstepiccourse.util.title.checker;

import com.example.mtsstepiccourse.exception.DtoValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ProhibitedSymbolsTitleChecker extends BaseTitleChecker {
    @Override
    public void checkTitle(CharSequence value) {
        boolean prohibitedSymbolsDetected = Pattern.compile(".*[\n\t\r].*").matcher(value).matches();
        if (prohibitedSymbolsDetected) {
            throw new DtoValidationException("Prhibited symbols are detected (\\n\\t\\n)");
        }
        super.checkTitle(value);
    }
}
