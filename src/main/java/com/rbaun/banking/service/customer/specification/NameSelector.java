package com.rbaun.banking.service.customer.specification;

import com.rbaun.banking.model.customer.Customer;

public class NameSelector implements CustomerSpecification {

    private final String searchTerm;

    public NameSelector(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public boolean isSatisfiedBy(Customer customer) {
        return customer.getName().toLowerCase().contains(searchTerm.toLowerCase());
    }
}
