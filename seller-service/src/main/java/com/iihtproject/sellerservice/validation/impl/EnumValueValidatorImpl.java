package com.iihtproject.sellerservice.validation.impl;

import com.iihtproject.sellerservice.validation.EnumValueValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValueValidatorImpl implements ConstraintValidator<EnumValueValidator,CharSequence> {
    private List<String> acceptedValues;
    @Override
    public void initialize(EnumValueValidator annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString().toUpperCase());
    }
}
