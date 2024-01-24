package com.rbaun.banking.repository.customer.specification;

import com.rbaun.banking.model.customer.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specification for searching for customers by phone number
 */
public class PhoneNumberSelector implements Specification<Customer> {

    private final String searchTerm;

    public PhoneNumberSelector(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public Predicate toPredicate(Root<Customer> customer, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(criteriaBuilder.lower(customer.get("phoneNumber")), "%" + searchTerm.toLowerCase() + "%");
    }
}
