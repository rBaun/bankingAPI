package com.rbaun.banking.exception.customer;

/**
 * Exception for when the given email is invalid
 */
public class CustomerInvalidEmailException extends RuntimeException {

    public CustomerInvalidEmailException(String message) {
        super(message);
    }

}
