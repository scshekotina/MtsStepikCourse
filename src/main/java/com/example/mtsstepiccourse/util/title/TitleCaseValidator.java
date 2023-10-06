package com.example.mtsstepiccourse.util.title;

import com.example.mtsstepiccourse.dto.TitleCaseMode;
import com.example.mtsstepiccourse.util.title.checker.*;
import com.example.mtsstepiccourse.util.title.checker.language.EnTitleChecker;
import com.example.mtsstepiccourse.util.title.checker.language.Language;
import com.example.mtsstepiccourse.util.title.checker.language.LanguageIdentifier;
import com.example.mtsstepiccourse.util.title.checker.language.RuTitleChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class TitleCaseValidator implements ConstraintValidator<TitleCase, CharSequence> {

    private TitleCaseMode mode;

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        TitleChecker checker = new ProhibitedSymbolsTitleChecker();
        TitleChecker secondChecker = new OneSpaceTogetherTitleChecker();
        TitleChecker thirdChecker = new NotFirstSpaceTitleChecker();
        TitleChecker forthChecker = new NotLastSpaceTitleChecker();
        TitleChecker fivChecker = new AllowedSymbolsTitleChecker();

        checker.setNext(secondChecker);
        secondChecker.setNext(thirdChecker);
        thirdChecker.setNext(forthChecker);
        forthChecker.setNext(fivChecker);

        TitleChecker lastTitleChecker = new LastTitleChecker();

        Language titleLanguage = LanguageIdentifier.identifyLanguage(value);
        switch (titleLanguage) {
            case MIXED:
                return false;
            case NOT_DEFINED:
                fivChecker.setNext(lastTitleChecker);
                break;
            case RU:
                if (mode == TitleCaseMode.EN) {
                    return false;
                }
                TitleChecker ruTitleChecker = new RuTitleChecker();
                fivChecker.setNext(ruTitleChecker);
                ruTitleChecker.setNext(lastTitleChecker);
                break;
            case EN:
                if (mode == TitleCaseMode.RU) {
                    return false;
                }
                EnTitleChecker enTitleChecker = new EnTitleChecker();
                fivChecker.setNext(enTitleChecker);
                enTitleChecker.setNext(lastTitleChecker);
                break;
            }
            return checker.checkTitle(value);
    }

    @Override
    public void initialize(TitleCase constraintAnnotation) {
        this.mode = constraintAnnotation.mode();
    }

}
