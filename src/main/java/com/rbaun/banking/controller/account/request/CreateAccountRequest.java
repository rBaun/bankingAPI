package com.rbaun.banking.controller.account.request;

import com.rbaun.banking.model.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(
        @NotBlank(message = "Account number is required") String accountNumber,
        @NotNull(message = "Account type is required") AccountType accountType,
        double balance
) {}