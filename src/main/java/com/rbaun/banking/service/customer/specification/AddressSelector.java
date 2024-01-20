package com.rbaun.banking.service.customer.specification;

import com.rbaun.banking.model.customer.Customer;

public class AddressSelector implements CustomerSpecification {

    private final String searchTerm;

    public AddressSelector(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public boolean isSatisfiedBy(Customer customer) {
        return customer.getAddress().toLowerCase().contains(searchTerm.toLowerCase());
    }
}
