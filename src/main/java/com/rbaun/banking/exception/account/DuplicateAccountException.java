package com.rbaun.banking.exception.account;

/**
 * Exception for when an account already exists
 */
public class DuplicateAccountException extends RuntimeException {

    public DuplicateAccountException(String message) {
        super(message);
    }

}
