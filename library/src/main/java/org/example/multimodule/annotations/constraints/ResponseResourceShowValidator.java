package org.example.multimodule.annotations.constraints;

import org.example.multimodule.dto.ResponseResourceShow;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ResponseResourceShowValidator implements ConstraintValidator<ValidResponse, ResponseResourceShow> {
    @Override
    public void initialize(ValidResponse validResponse) {

    }

    @Override
    public boolean isValid(ResponseResourceShow responseResourceShow, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
