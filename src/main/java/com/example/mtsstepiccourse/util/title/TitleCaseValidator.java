package com.example.mtsstepiccourse.util.title;

import com.example.mtsstepiccourse.dto.TitleCaseMode;
import com.example.mtsstepiccourse.exception.DtoValidationException;
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

    public static final String MORE_THAN_ONE_LANGUAGE_IS_PROHIBITED_IN_TITLE = "More than one language is prohibited in title";
    public static final String RUSSIAN_SYMBOLS_IN_ENGLISH_TITLE = "Russian symbols in english title!";
    public static final String ENGLISH_SYMBOLS_IN_RUSSIAN_TITLE = "English symbols in russian title!";
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

        Language titleLanguage = LanguageIdentifier.identifyLanguage(value);
        switch (titleLanguage) {
            case MIXED:
                if (context != null) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(MORE_THAN_ONE_LANGUAGE_IS_PROHIBITED_IN_TITLE).addConstraintViolation();
                }
                return false;
            case NOT_DEFINED:
                break;
            case RU:
                if (mode == TitleCaseMode.EN) {
                    if (context != null) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(RUSSIAN_SYMBOLS_IN_ENGLISH_TITLE).addConstraintViolation();
                    }
                    return false;
                }
                TitleChecker ruTitleChecker = new RuTitleChecker();
                fivChecker.setNext(ruTitleChecker);
                break;
            case EN:
                if (mode == TitleCaseMode.RU) {
                    if (context != null) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(ENGLISH_SYMBOLS_IN_RUSSIAN_TITLE).addConstraintViolation();
                    }
                    return false;
                }
                EnTitleChecker enTitleChecker = new EnTitleChecker();
                fivChecker.setNext(enTitleChecker);
                break;
            }
            try {
                checker.checkTitle(value);
                return true;
            } catch (DtoValidationException e) {
                if (context != null) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
                }
                return false;
            }

    }

    @Override
    public void initialize(TitleCase constraintAnnotation) {
        this.mode = constraintAnnotation.mode();
    }

}
