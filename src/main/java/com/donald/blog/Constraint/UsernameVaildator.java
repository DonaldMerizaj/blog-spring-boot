package com.donald.blog.Constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameVaildator implements ConstraintValidator<ValidUsername, Object> {

    @Override
    public void initialize(ValidUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        return value.toString().length() >= 5;
    }
}
