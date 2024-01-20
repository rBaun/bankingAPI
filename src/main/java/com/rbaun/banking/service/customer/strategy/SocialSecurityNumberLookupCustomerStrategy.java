package com.rbaun.banking.service.customer.strategy;

import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.service.customer.CustomerService;

public class SocialSecurityNumberLookupCustomerStrategy implements LookupCustomerStrategy {
    @Override
    public Customer findCustomer(CustomerService service, LookupCustomerRequest request) {
        return service.getCustomerBySocialSecurityNumber(request.socialSecurityNumber());
    }
}
