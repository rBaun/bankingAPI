package com.rbaun.banking.exception.account;

public enum AccountErrorMessage {
    ACCOUNT_NOT_FOUND("Account not found"),
    INSUFFICIENT_FUNDS("Insufficient funds"),
    AMOUNT_BELOW_MINIMUM("Amount is below the minimum allowed transaction amount");

    private final String message;

    AccountErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
