package com.rbaun.banking.service.customer.specification;

import com.rbaun.banking.model.customer.Customer;

public class PhoneNumberSelector implements CustomerSpecification {

    private final String searchTerm;

    public PhoneNumberSelector(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public boolean isSatisfiedBy(Customer customer) {
        return customer.getPhoneNumber().contains(searchTerm);
    }
}
