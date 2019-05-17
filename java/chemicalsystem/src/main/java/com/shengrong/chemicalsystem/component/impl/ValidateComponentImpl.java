package com.shengrong.chemicalsystem.component.impl;

import com.shengrong.chemicalsystem.component.ValidateComponent;
import com.shengrong.chemicalsystem.ecxeption.ChemicalException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@Component
public class ValidateComponentImpl implements ValidateComponent {

    private final Validator validator;

    public ValidateComponentImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> void validate(T t, Class<?>... groups) throws ChemicalException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t, groups);
        if (constraintViolations.size() > 0) {
            throw new ChemicalException();
        }
    }
}
