package com.rbaun.banking.service.customer;

import com.rbaun.banking.model.customer.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();
    List<Customer> searchCustomers(String searchTerm);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);

}
