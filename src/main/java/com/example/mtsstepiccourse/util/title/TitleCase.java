package com.example.mtsstepiccourse.util.title;

import com.example.mtsstepiccourse.dto.TitleCaseMode;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TitleCaseValidator.class)
public @interface TitleCase {
    TitleCaseMode mode() default TitleCaseMode.ANY;

    String message() default "{com.example.mtsstepiccourse.util.titlecase.TitleCase.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
