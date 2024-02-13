package com.rbaun.banking.validation;

import com.rbaun.banking.controller.v1.customer.request.LookupCustomerRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class ExactlyOneFieldNotNullValidator implements ConstraintValidator<ExactlyOneFieldNotNull, LookupCustomerRequest> {

    @Override
    public void initialize(ExactlyOneFieldNotNull constraintAnnotation) {
    }

    @Override
    public boolean isValid(LookupCustomerRequest request, ConstraintValidatorContext context) {
        int nonNullCount = 0;

        // Check if exactly one field is not null
        for (var accessor : request.getClass().getRecordComponents()) {
            try {
                if (accessor.getAccessor().invoke(request) != null) {
                    nonNullCount++;
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return nonNullCount == 1;
    }

}
