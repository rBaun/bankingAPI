package com.rbaun.banking.exception.customer;

/**
 * Exception for when the given phone number is invalid
 */
public class CustomerInvalidPhoneNumberException extends RuntimeException {

    public CustomerInvalidPhoneNumberException(String message) {
        super(message);
    }

}
