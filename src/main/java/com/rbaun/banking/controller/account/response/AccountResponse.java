package com.rbaun.banking.controller.account.response;

import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.enums.AccountType;

public record AccountResponse(
        String accountNumber,
        AccountType accountType,
        double balance
) {
    public AccountResponse(Account account) {
        this(account.getAccountNumber(), account.getAccountType(), account.getBalance());
    }

    public static AccountResponse from(Account account) {
        return new AccountResponse(account);
    }
}
