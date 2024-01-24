package com.rbaun.banking.controller.customer.request;

import com.rbaun.banking.validation.ExactlyOneFieldNotNull;
import io.swagger.v3.oas.annotations.Hidden;

/**
 * Used to search for a customer by email, phone number, or social security number.<br>
 * <b>Only one</b> of these fields can be used at a time.
 * @param email - the customer's email
 * @param phoneNumber - the customer's phone number
 * @param socialSecurityNumber - the customer's social security number
 */
@ExactlyOneFieldNotNull
public record LookupCustomerRequest(
        String email,
        String phoneNumber,
        String socialSecurityNumber
) {
    /**
     * Get the value to find the customer by
     * @return the email, phone number, or social security number that is not null
     */
    @Hidden
    public String getLookupValue() {
        if (email() != null) {
            return email();
        } else if (phoneNumber() != null) {
            return phoneNumber();
        } else {
            return socialSecurityNumber();
        }
    }
}
