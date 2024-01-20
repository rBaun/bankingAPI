package com.rbaun.banking.exception.account;

public class AmountInvalidException extends RuntimeException {

    public AmountInvalidException(String message) {
        super(message);
    }

}
