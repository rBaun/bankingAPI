package com.rbaun.banking.exception.account;

/**
 * Exception for when an account does not have enough funds
 */
public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {
        super(message);
    }

}
