package com.rbaun.banking.exception.account;

/**
 * Enum for account error messages
 */
public enum AccountErrorMessage {
    ACCOUNT_NOT_FOUND("Account not found"),
    ACCOUNT_NOT_FOUND_ON_CUSTOMER("Account not found on customer"),
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
