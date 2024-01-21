package com.rbaun.banking.service.customer.strategy;

public class LookupCustomerStrategyFactory {

    public static LookupCustomerStrategy from(LookupCustomerRequest request) {
        if (request.email() != null) {
            return new EmailLookupCustomerStrategy();
        } else if (request.phoneNumber() != null) {
            return new PhoneNumberLookupCustomerStrategy();
        } else if (request.socialSecurityNumber() != null) {
            return new SocialSecurityNumberLookupCustomerStrategy();
        } else {
            throw new IllegalStateException("LookupCustomerStrategy not found");
        }
    }

}
