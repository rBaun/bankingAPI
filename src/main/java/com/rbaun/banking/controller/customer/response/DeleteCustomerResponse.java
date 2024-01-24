package com.rbaun.banking.controller.customer.response;

import com.rbaun.banking.controller.customer.request.LookupCustomerRequest;

/**
 * Used for when a customer is deleted
 * @param email - the customer's email
 * @param phoneNumber - the customer's phone number
 * @param socialSecurityNumber - the customer's social security number
 */
public record DeleteCustomerResponse(
        String email,
        String phoneNumber,
        String socialSecurityNumber
) {
    /**
     * Static method to map @{@link LookupCustomerRequest} to @{@link DeleteCustomerResponse}
     * @param request - the request to map
     * @return the mapped request
     */
    public static DeleteCustomerResponse from(LookupCustomerRequest request) {
        return new DeleteCustomerResponse(request.email(), request.phoneNumber(), request.socialSecurityNumber());
    }
}
