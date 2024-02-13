package com.rbaun.banking.controller.v1.customer.response;

import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.util.DateUtil;

/**
 * Used for when a customer is returned to the client as a response
 * @param name - the customer's first name and last name combined
 * @param email - the customer's email
 * @param phoneNumber - the customer's phone number
 * @param address - the customer's address
 * @param dateOfBirth - the customer's date of birth
 * @param socialSecurityNumber - the customer's social security number
 */
public record CustomerResponse(
        String name,
        String email,
        String phoneNumber,
        String address,
        String dateOfBirth,
        String socialSecurityNumber
) {
    /**
     * Constructor to map @{@link Customer} to @{@link CustomerResponse}
     * @param customer - the customer to map
     */
    public CustomerResponse(Customer customer) {
        this(
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                DateUtil.format(customer.getDateOfBirth()),
                customer.getSocialSecurityNumber()
        );
    }

    /**
     * Static method to map @{@link Customer} to @{@link CustomerResponse}
     * @param customer - the customer to map
     * @return the mapped customer
     */
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer);
    }
}
