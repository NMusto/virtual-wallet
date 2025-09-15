package com.virtual_wallet.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValueOfEnum annotation) {
        this.enumClass = annotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equalsIgnoreCase(value));
    }
}
