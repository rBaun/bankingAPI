package com.rbaun.banking.exception.customer;

public class CustomerAlreadyExist extends RuntimeException {

    public CustomerAlreadyExist(String message) {
        super(message);
    }

}
