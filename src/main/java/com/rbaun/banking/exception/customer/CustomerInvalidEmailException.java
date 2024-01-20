package com.rbaun.banking.exception.customer;

public class CustomerInvalidEmailException extends RuntimeException {

    public CustomerInvalidEmailException(String message) {
        super(message);
    }

}
