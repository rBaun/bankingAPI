package com.rbaun.banking.exception.customer;

public enum CustomerErrorMessage {
    CUSTOMER_NOT_FOUND("Customer not found"),
    CUSTOMER_INVALID_EMAIL("Customer email is invalid"),
    CUSTOMER_INVALID_PHONE_NUMBER("Customer phone number is invalid"),
    CUSTOMER_ALREADY_EXISTS("Customer already exists"),
    CUSTOMER_DUPLICATE_EMAIL("Customer email already exists"),
    CUSTOMER_DUPLICATE_PHONE_NUMBER("Customer phone number already exists"),
    CUSTOMER_DUPLICATE_SOCIAL_SECURITY_NUMBER("Customer social security number already exists");

    private final String message;

    CustomerErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
