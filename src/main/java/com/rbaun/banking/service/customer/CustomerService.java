package com.rbaun.banking.service.customer;

import com.rbaun.banking.service.customer.strategy.LookupCustomerRequest;
import com.rbaun.banking.model.customer.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();
    List<Customer> searchCustomers(String searchTerm);
    Customer getCustomerByEmail(String email);
    Customer getCustomerByPhoneNumber(String phoneNumber);
    Customer getCustomerBySocialSecurityNumber(String socialSecurityNumber);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer, String socialSecurityNumber);
    void deleteCustomer(LookupCustomerRequest request);

}
