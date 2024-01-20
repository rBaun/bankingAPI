package com.rbaun.banking.controller.customer.response;

import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.util.DateUtil;

public record CustomerResponse(
        String name,
        String email,
        String phoneNumber,
        String address,
        String dateOfBirth,
        String socialSecurityNumber
) {
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

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer);
    }
}
