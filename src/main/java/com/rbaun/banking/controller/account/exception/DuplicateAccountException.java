package com.rbaun.banking.controller.account.exception;

public class DuplicateAccountException extends RuntimeException {

    public DuplicateAccountException(String message) {
        super(message);
    }

}
