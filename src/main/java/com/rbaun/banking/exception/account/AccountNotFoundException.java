package com.rbaun.banking.exception.account;

/**
 * Exception for when an account is not found
 */
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
    }

}
