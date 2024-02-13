package com.rbaun.banking.controller.v1.account.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record DepositRequest(
    @NotBlank(message = "Need to know which account number to deposit to") String accountNumber,
    @Min(value = 1, message = "Amount is below minimum") Double amount
) {
}
