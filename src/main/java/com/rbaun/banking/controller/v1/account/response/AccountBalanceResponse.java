package com.rbaun.banking.controller.v1.account.response;

import com.rbaun.banking.model.account.Account;

/**
 * Used for when account balance is returned to the client as a response
 * @param accountNumber - the account number
 * @param balance - the account balance
 */
public record AccountBalanceResponse(
        String accountNumber,
        double balance) {

    /**
     * Constructor to map @{@link Account} to @{@link AccountBalanceResponse}
     * @param account - the account to map
     */
    public AccountBalanceResponse(Account account) {
        this(account.getAccountNumber(), account.getBalance());
    }

    /**
     * Static method to map @{@link Account} to @{@link AccountBalanceResponse}
     * @param account - the account to map
     * @return @{@link AccountBalanceResponse}
     */
    public static AccountBalanceResponse from(Account account) {
        return new AccountBalanceResponse(account);
    }
}
