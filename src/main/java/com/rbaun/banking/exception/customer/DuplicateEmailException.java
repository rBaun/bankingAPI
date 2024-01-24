package com.rbaun.banking.exception.customer;

/**
 * Exception for when the email is already in use
 */
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }

}
