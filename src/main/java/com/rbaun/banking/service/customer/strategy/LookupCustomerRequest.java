package com.rbaun.banking.service.customer.strategy;

import com.rbaun.banking.service.customer.strategy.LookupCustomerStrategy;
import com.rbaun.banking.service.customer.strategy.EmailLookupCustomerStrategy;
import com.rbaun.banking.service.customer.strategy.PhoneNumberLookupCustomerStrategy;
import com.rbaun.banking.service.customer.strategy.SocialSecurityNumberLookupCustomerStrategy;
import com.rbaun.banking.validation.ExactlyOneFieldNotNull;

@ExactlyOneFieldNotNull
public record LookupCustomerRequest(
        String email,
        String phoneNumber,
        String socialSecurityNumber
) {
    public LookupCustomerStrategy getLookupCustomerStrategy() {
        if (email() != null) {
            return new EmailLookupCustomerStrategy();
        } else if (phoneNumber() != null) {
            return new PhoneNumberLookupCustomerStrategy();
        } else if (socialSecurityNumber() != null) {
            return new SocialSecurityNumberLookupCustomerStrategy();
        } else {
            throw new IllegalStateException("LookupCustomerStrategy not found");
        }
    }
}
