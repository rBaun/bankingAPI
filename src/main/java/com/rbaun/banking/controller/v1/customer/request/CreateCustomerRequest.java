package com.rbaun.banking.controller.v1.customer.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * Used for when a customer is created
 * @param firstName - the customer's first name
 * @param lastName - the customer's last name
 * @param address - the customer's address
 * @param email - the customer's email
 * @param phoneNumber - the customer's phone number
 * @param socialSecurityNumber - the customer's social security number
 * @param dateOfBirth - the customer's date of birth
 */
public record CreateCustomerRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String address,
        @NotBlank String email,
        @NotBlank String phoneNumber,
        @NotBlank String socialSecurityNumber,
        LocalDate dateOfBirth
) {
}
