package com.rbaun.banking.controller.account.response;

import com.rbaun.banking.model.account.Account;

/**
 * Used for when the @{@link Account} is deleted and returned to the client as a response
 * @param accountNumber - the account number
 */
public record DeleteAccountResponse(
        String accountNumber
) {
    /**
     * Static method to map @{@link Account} to @{@link DeleteAccountResponse}
     * @param accountNumber - the account number
     * @return @{@link DeleteAccountResponse}
     */
    public static DeleteAccountResponse from(String accountNumber) {
        return new DeleteAccountResponse(accountNumber);
    }
}
