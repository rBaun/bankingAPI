package com.rbaun.banking.exception.customer;

public class CustomerInvalidPhoneNumberException extends RuntimeException {

    public CustomerInvalidPhoneNumberException(String message) {
        super(message);
    }

}
