package com.rbaun.banking.exception.account;

/**
 * Exception for when the given amount is invalid
 */
public class AmountInvalidException extends RuntimeException {

    public AmountInvalidException(String message) {
        super(message);
    }

}
