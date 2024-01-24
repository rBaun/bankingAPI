package com.rbaun.banking.controller.account.request;

import com.rbaun.banking.model.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO containing the information required to create a new banking account for a customer
 * @param accountNumber - the account number
 * @param accountType - the account type {@link AccountType}
 */
public record CreateAccountRequest(
        @NotBlank(message = "Account number is required") String accountNumber,
        @NotNull(message = "Account type is required") AccountType accountType,
        double balance
) {
}