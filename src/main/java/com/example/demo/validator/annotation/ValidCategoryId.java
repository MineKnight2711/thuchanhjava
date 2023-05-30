package com.example.demo.validator.annotation;
import java.lang.annotation.*;

import com.example.demo.validator.ValidCategoryIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD})
@Retention (RUNTIME)
@Constraint(validatedBy = ValidCategoryIdValidator.class)
@Documented
public @interface ValidCategoryId {
    String message() default "Danh mục không hợp lệ";
    Class<?>[] groups () default {};
    Class<? extends Payload> [] payload() default {};
}
