package com.rbaun.banking.service.customer.strategy;

import com.rbaun.banking.validation.ExactlyOneFieldNotNull;

@ExactlyOneFieldNotNull
public record LookupCustomerRequest(
        String email,
        String phoneNumber,
        String socialSecurityNumber
) {
}
