package com.rbaun.banking.controller.customer.response;

import com.rbaun.banking.controller.customer.request.LookupCustomerRequest;

public record DeleteCustomerResponse(
        String email,
        String phoneNumber,
        String socialSecurityNumber
) {
    public static DeleteCustomerResponse from(LookupCustomerRequest request) {
        return new DeleteCustomerResponse(request.email(), request.phoneNumber(), request.socialSecurityNumber());
    }
}
