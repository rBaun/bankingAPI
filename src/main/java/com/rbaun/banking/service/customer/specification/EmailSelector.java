package com.rbaun.banking.service.customer.specification;

import com.rbaun.banking.model.customer.Customer;

public class EmailSelector implements CustomerSpecification {

    private final String searchTerm;

    public EmailSelector(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public boolean isSatisfiedBy(Customer customer) {
        if (!customer.getEmail().contains("@")) return false;

        return customer.getEmail().toLowerCase().contains(searchTerm.toLowerCase());
    }
}
