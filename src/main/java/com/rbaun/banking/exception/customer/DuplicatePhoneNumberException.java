package com.rbaun.banking.exception.customer;

/**
 * Exception for when the phone number is already in use
 */
public class DuplicatePhoneNumberException extends RuntimeException {

    public DuplicatePhoneNumberException(String message) {
        super(message);
    }

}
