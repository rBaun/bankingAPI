package com.rbaun.banking.controller.account.response;

import com.rbaun.banking.model.account.Account;

public record AccountBalanceResponse(
        String accountNumber,
        double balance) {
    public AccountBalanceResponse(Account account) {
        this(account.getAccountNumber(), account.getBalance());
    }
}