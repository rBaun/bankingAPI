package com.rbaun.banking.controller.customer.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * Used for when a customer is updated
 * @param firstName - the customer's first name
 * @param lastName - the customer's last name
 * @param address - the customer's address
 * @param email - the customer's email
 * @param phoneNumber - the customer's phone number
 * @param socialSecurityNumberToUpdate - the customer's social security number
 * @param dateOfBirth - the customer's date of birth
 */
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
