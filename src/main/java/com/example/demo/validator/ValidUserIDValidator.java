package com.example.demo.validator;
import com.example.demo.entity.User;
import com.example.demo.validator.annotation.ValidUserID;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIDValidator implements ConstraintValidator<ValidUserID, User> {
    @Override
    public boolean isValid (User user, ConstraintValidatorContext context) {
        if (user == null)
            return true;
        return user.getId() != null;
    }
}