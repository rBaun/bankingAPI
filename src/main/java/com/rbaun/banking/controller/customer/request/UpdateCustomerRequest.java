package com.rbaun.banking.controller.customer.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateCustomerRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String address,
        @NotBlank String email,
        @NotBlank String phoneNumber,
        @NotBlank String socialSecurityNumberToUpdate,
        LocalDate dateOfBirth
) {
}
