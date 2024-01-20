package com.rbaun.banking.service.customer;

import com.rbaun.banking.model.customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> findAllCustomers() {
        return null;
    }

    @Override
    public List<Customer> searchCustomers(String searchTerm) {
        return null;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }
}
