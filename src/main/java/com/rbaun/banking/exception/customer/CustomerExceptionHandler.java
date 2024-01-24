package com.rbaun.banking.exception.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handle exceptions thrown by the endpoints in the @{@link com.rbaun.banking.controller.customer.CustomerController}
 */
@ControllerAdvice
public class CustomerExceptionHandler {

    /**
     * Handle @{@link CustomerInvalidEmailException} and return a @{@link ResponseEntity} with a message
     * @param e - the @{@link CustomerInvalidEmailException} to handle
     * @return @{@link ResponseEntity} with a message indicating the email is invalid
     */
    @ExceptionHandler(CustomerInvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleCustomerInvalidEmailException(CustomerInvalidEmailException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Handle @{@link CustomerInvalidPhoneNumberException} and return a @{@link ResponseEntity} with a message
     * @param e - the @{@link CustomerInvalidPhoneNumberException} to handle
     * @return @{@link ResponseEntity} with a message indicating the phone number is invalid
     */
    @ExceptionHandler(CustomerInvalidPhoneNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleCustomerInvalidPhoneNumberException(CustomerInvalidPhoneNumberException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Handle {@link CustomerNotFoundException} and return a {@link ResponseEntity} with a message
     * @param e - the {@link CustomerNotFoundException} to handle
     * @return {@link ResponseEntity} with a message indicating the customer was not found
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Handle {@link DuplicateEmailException} and return a {@link ResponseEntity} with a message
     * @param e - the {@link DuplicateEmailException} to handle
     * @return {@link ResponseEntity} with a message indicating the email already exists
     */
    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Handle {@link DuplicatePhoneNumberException} and return a {@link ResponseEntity} with a message
     * @param e - the {@link DuplicatePhoneNumberException} to handle
     * @return {@link ResponseEntity} with a message indicating the phone number already exists
     */
    @ExceptionHandler(DuplicatePhoneNumberException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDuplicatePhoneNumberException(DuplicatePhoneNumberException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /**
     * Handle {@link DuplicateSocialSecurityNumberException} and return a {@link ResponseEntity} with a message
     * @param e - the {@link DuplicateSocialSecurityNumberException} to handle
     * @return {@link ResponseEntity} with a message indicating the social security number already exists
     */
    @ExceptionHandler(DuplicateSocialSecurityNumberException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDuplicateSocialSecurityNumberException(DuplicateSocialSecurityNumberException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
