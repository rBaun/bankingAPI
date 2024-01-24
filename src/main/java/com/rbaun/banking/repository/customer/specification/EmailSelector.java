package com.rbaun.banking.repository.customer.specification;

import com.rbaun.banking.model.customer.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specification for searching for customers by email
 */
public class EmailSelector implements Specification<Customer> {

    private final String searchTerm;

    public EmailSelector(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String lowerCaseSearchTerm = "%" + searchTerm.toLowerCase() + "%";

        return criteriaBuilder.and(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), lowerCaseSearchTerm),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%@%")
        );
    }
}
