package com.rbaun.banking.controller.v1.account.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TransferRequest(
    @NotBlank(message = "Need to know which account number to transfer from") String fromAccountNumber,
    @NotBlank(message = "Need to know which account number to transfer to") String toAccountNumber,
    @Min(value = 1, message = "Amount is below minimum") Double amount
) {
}
