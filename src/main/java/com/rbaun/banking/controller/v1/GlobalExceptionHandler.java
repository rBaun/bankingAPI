package com.rbaun.banking.controller.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle @{@link BindException} and return a @{@link ResponseEntity} with a list of error messages
     * @param ex - the @{@link BindException} to handle
     * @return @{@link ResponseEntity} with a list of error messages indicating which fields failed validation
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleBindException(BindException ex) {
        List<String> errorMessages = ex.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(errorMessages.toString());
    }
}
