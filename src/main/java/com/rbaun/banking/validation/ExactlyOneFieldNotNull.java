package com.rbaun.banking.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Validation annotation to ensure that exactly one field is not null
 */
@Documented
@Constraint(validatedBy = ExactlyOneFieldNotNullValidator.class)
@Target({ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ExactlyOneFieldNotNull {
    String message() default "Exactly one field must be not null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
