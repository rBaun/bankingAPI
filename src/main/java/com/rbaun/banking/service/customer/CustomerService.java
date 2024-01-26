package com.rbaun.banking.service.customer;

import com.rbaun.banking.exception.customer.*;
import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.service.customer.strategy.LookupCustomerStrategy;

import java.util.List;

/**
 * Service class for {@link Customer} objects that contains all the business logic for the customer entity
 */
public interface CustomerService {

    /**
     * Get a list of all the customers registered
     * @return List of all the customers, empty list if no customers are registered
     */
    List<Customer> findAllCustomers();

    /**
     * Search for customers by the search term
     * @param searchTerm the search term to search for
     * @return List of all the customers matching the search term, empty list if no customers are found
     */
    List<Customer> searchCustomers(String searchTerm);

    /**
     * Get a customer by the email
     * @param email the email of the customer to get
     * @return the customer matching the email
     * @throws CustomerNotFoundException if the customer is not found
     */
    Customer getCustomerByEmail(String email);

    /**
     * Get a customer by the phone number
     * @param phoneNumber the phone number of the customer to get
     * @return the customer matching the phone number
     * @throws CustomerNotFoundException if the customer is not found
     */
    Customer getCustomerByPhoneNumber(String phoneNumber);

    /**
     * Get a customer by the social security number
     * @param socialSecurityNumber the social security number of the customer to get
     * @return the customer matching the social security number
     * @throws CustomerNotFoundException if the customer is not found
     */
    Customer getCustomerBySocialSecurityNumber(String socialSecurityNumber);

    /**
     * Create a customer
     * @param customer the customer to create
     * @return the created customer
     * @throws CustomerInvalidPhoneNumberException if the phone number format is invalid
     * @throws CustomerInvalidEmailException if the email format is invalid
     * @throws DuplicateEmailException if the email is already registered
     * @throws DuplicatePhoneNumberException if the phone number is already registered
     * @throws DuplicateSocialSecurityNumberException if the social security number is already registered
     */
    Customer createCustomer(Customer customer);

    /**
     * Update a customer
     * @param customer the customer to update
     * @param socialSecurityNumber the social security number of the customer to update
     * @return the updated customer
     * @throws CustomerNotFoundException if the customer is not found
     * @throws CustomerInvalidEmailException if the email format is invalid
     * @throws DuplicateEmailException if the new email is already registered
     * @throws CustomerInvalidPhoneNumberException if the phone number format is invalid
     * @throws DuplicatePhoneNumberException if the new phone number is already registered
     */
    Customer updateCustomer(Customer customer, String socialSecurityNumber);

    /**
     * Delete a customer by the social security number
     * @param strategy the strategy to use to find the customer
     * @param value the email, phone number or social security number of the customer to delete
     */
    void deleteCustomer(LookupCustomerStrategy strategy, String value);

    /**
     * Find a customer by the username
     * @param username the username of the customer to find
     * @return the customer matching the username
     */
    Customer findCustomerByUsername(String username);
}
