package com.rbaun.banking.controller.account.response;

import com.rbaun.banking.model.account.Account;

public record DeleteAccountResponse(
        String accountNumber
) {
    public static DeleteAccountResponse from(String accountNumber) {
        return new DeleteAccountResponse(accountNumber);
    }

    public DeleteAccountResponse(Account account) {
        this(account.getAccountNumber());
    }
}
