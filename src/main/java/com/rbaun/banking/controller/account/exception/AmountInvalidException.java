package com.rbaun.banking.controller.account.exception;

public class AmountInvalidException extends RuntimeException {

    public AmountInvalidException(String message) {
        super(message);
    }

}
