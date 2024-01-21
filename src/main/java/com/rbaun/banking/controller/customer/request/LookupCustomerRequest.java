package com.rbaun.banking.controller.customer.request;

import com.rbaun.banking.validation.ExactlyOneFieldNotNull;
import io.swagger.v3.oas.annotations.Hidden;

@ExactlyOneFieldNotNull
public record LookupCustomerRequest(
        String email,
        String phoneNumber,
        String socialSecurityNumber
) {
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
