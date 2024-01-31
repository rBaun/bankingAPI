package com.rbaun.banking.controller.account.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record WithdrawRequest(
    @NotBlank(message = "Need to know which account number to withdraw from") String accountNumber,
    @Min(value = 1, message = "Amount is below minimum") Double amount
) {
}
