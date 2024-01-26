package com.rbaun.banking.repository.customer;

import com.rbaun.banking.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Repository for the customer entity
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    /**
     * Find a customer by the email
     * @param email the email to search for
     * @return the customer matching the email
     */
    Optional<Customer> findByEmail(String email);

    /**
     * Find a customer by the phone number
     * @param phoneNumber the phone number to search for
     * @return the customer matching the phone number
     */
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    /**
     * Find a customer by the social security number
     * @param socialSecurityNumber the social security number to search for
     * @return the customer matching the social security number
     */
    Optional<Customer> findBySocialSecurityNumber(String socialSecurityNumber);

    /**
     * Find a customer by the username
     * @param username the username to search for
     * @return the customer matching the username
     */
    Optional<Customer> findByUsername(String username);

}
