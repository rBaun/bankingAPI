package com.rbaun.banking.controller.account.response;

import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.enums.AccountType;

/**
 * Used for when the @{@link Account} is returned to the client as a response
 * @param accountNumber
 * @param accountType
 * @param balance
 */
public record AccountResponse(
        String title,
        String accountNumber,
        AccountType accountType,
        double balance
) {
    /**
     * Constructor to map @{@link Account} to @{@link AccountResponse}
     * @param account - the account to map
     */
    public AccountResponse(Account account) {
        this(account.getTitle(), account.getAccountNumber(), account.getAccountType(), account.getBalance());
    }

    /**
     * Static method to map @{@link Account} to @{@link AccountResponse}
     * @param account - the account to map
     * @return @{@link AccountResponse}
     */
    public static AccountResponse from(Account account) {
        return new AccountResponse(account);
    }
}
