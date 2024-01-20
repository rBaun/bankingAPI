package com.rbaun.banking.exception.customer;

public class DuplicateSocialSecurityNumberException extends RuntimeException {

    public DuplicateSocialSecurityNumberException(String message) {
        super(message);
    }

}
