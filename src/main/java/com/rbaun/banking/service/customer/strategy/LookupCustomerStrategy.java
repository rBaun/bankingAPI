package com.rbaun.banking.service.customer.strategy;

import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.service.customer.CustomerService;

public interface LookupCustomerStrategy {
    Customer findCustomer(CustomerService service, String value);
}
