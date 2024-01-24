package com.rbaun.banking.exception.customer;

/**
 * Exception for when a customer is not found
 */
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }

}
