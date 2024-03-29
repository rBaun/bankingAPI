package com.rbaun.banking.service.customer;

import com.rbaun.banking.assertion.customer.CustomerAssertions;
import com.rbaun.banking.exception.customer.*;
import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.repository.customer.CustomerRepository;
import com.rbaun.banking.repository.customer.specification.AddressSelector;
import com.rbaun.banking.repository.customer.specification.EmailSelector;
import com.rbaun.banking.repository.customer.specification.NameSelector;
import com.rbaun.banking.repository.customer.specification.PhoneNumberSelector;
import com.rbaun.banking.service.customer.strategy.LookupCustomerStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAssertions customerAssertions;

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String searchTerm) {
        Specification<Customer> searchSpec = new NameSelector(searchTerm)
                .or(new EmailSelector(searchTerm))
                .or(new AddressSelector(searchTerm))
                .or(new PhoneNumberSelector(searchTerm));

        return customerRepository.findAll(searchSpec);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customerFoundByEmail = customerRepository.findByEmail(email);
        if (customerFoundByEmail.isEmpty()) {
            logger.error("Customer with email {} not found", email);
            throw new CustomerNotFoundException(CustomerErrorMessage.CUSTOMER_NOT_FOUND.getMessage());
        }

        return customerFoundByEmail.get();
    }

    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        Optional<Customer> customerFoundByPhoneNumber = customerRepository.findByPhoneNumber(phoneNumber);
        if (customerFoundByPhoneNumber.isEmpty()) {
            logger.error("Customer with phone number {} not found", phoneNumber);
            throw new CustomerNotFoundException(CustomerErrorMessage.CUSTOMER_NOT_FOUND.getMessage());
        }

        return customerFoundByPhoneNumber.get();
    }

    @Override
    public Customer getCustomerBySocialSecurityNumber(String socialSecurityNumber) {
        Optional<Customer> customerFoundBySocialSecurityNumber = customerRepository.findBySocialSecurityNumber(socialSecurityNumber);
        if (customerFoundBySocialSecurityNumber.isEmpty()) {
            logger.error("Customer with social security number {} not found", socialSecurityNumber);
            throw new CustomerNotFoundException(CustomerErrorMessage.CUSTOMER_NOT_FOUND.getMessage());
        }
        logger.info("Found customer with matching social security number {}", customerFoundBySocialSecurityNumber.get());

        return customerFoundBySocialSecurityNumber.get();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customerAssertions.throwIfEmailInvalid(customer.getEmail());
        customerAssertions.throwIfPhoneNumberInvalid(customer.getPhoneNumber());
        throwIfEmailAlreadyRegistered(customer.getEmail());
        throwIfPhoneNumberAlreadyRegistered(customer.getPhoneNumber());
        throwIfSocialSecurityNumberAlreadyRegistered(customer.getSocialSecurityNumber());

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, String socialSecurityNumber) {
        Customer customerToUpdate = getCustomerBySocialSecurityNumber(socialSecurityNumber);
        if (!Objects.equals(customerToUpdate.getEmail(), customer.getEmail())) {
            customerAssertions.throwIfEmailInvalid(customer.getEmail());
            throwIfEmailAlreadyRegistered(customer.getEmail());
        }
        if (!Objects.equals(customerToUpdate.getPhoneNumber(), customer.getPhoneNumber())) {
            customerAssertions.throwIfPhoneNumberInvalid(customer.getPhoneNumber());
            throwIfPhoneNumberAlreadyRegistered(customer.getPhoneNumber());
        }

        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setDateOfBirth(customer.getDateOfBirth());

        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteCustomer(LookupCustomerStrategy strategy, String value) {
        Customer customer = strategy.findCustomer(this, value);
        customerRepository.delete(customer);
    }

    @Override
    public Customer findCustomerByUsername(String username) {
        Optional<Customer> customerFoundByUsername = customerRepository.findByUsername(username);
        if (customerFoundByUsername.isEmpty()) {
            logger.info("{} has not registered as a customer yet", username);
            return null;
        }
        Customer customer = customerFoundByUsername.get();
        logger.info("{} has registered as a customer: {}", username, customer);

        return customerFoundByUsername.get();
    }

    private void throwIfEmailAlreadyRegistered(String email) {
        if (customerRepository.findByEmail(email).isPresent()) {
            logger.error("Customer with email {} already exists", email);
            throw new DuplicateEmailException(CustomerErrorMessage.CUSTOMER_DUPLICATE_EMAIL.getMessage());
        }
    }

    private void throwIfPhoneNumberAlreadyRegistered(String phoneNumber) {
        if (customerRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            logger.error("Customer with phone number {} already exists", phoneNumber);
            throw new DuplicatePhoneNumberException(CustomerErrorMessage.CUSTOMER_DUPLICATE_PHONE_NUMBER.getMessage());
        }
    }

    private void throwIfSocialSecurityNumberAlreadyRegistered(String socialSecurityNumber) {
        if (customerRepository.findBySocialSecurityNumber(socialSecurityNumber).isPresent()) {
            logger.error("Customer with social security number {} already exists", socialSecurityNumber);
            throw new DuplicateSocialSecurityNumberException(CustomerErrorMessage.CUSTOMER_DUPLICATE_SOCIAL_SECURITY_NUMBER.getMessage());
        }
    }

}
