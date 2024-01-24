package com.rbaun.banking.exception.customer;

/**
 * Exception for when the social security number is already in use
 */
public class DuplicateSocialSecurityNumberException extends RuntimeException {

    public DuplicateSocialSecurityNumberException(String message) {
        super(message);
    }

}
