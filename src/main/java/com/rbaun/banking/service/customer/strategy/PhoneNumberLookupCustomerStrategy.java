package com.rbaun.banking.service.customer.strategy;

import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.service.customer.CustomerService;

public class PhoneNumberLookupCustomerStrategy implements LookupCustomerStrategy {

    @Override
    public Customer findCustomer(CustomerService service, String phoneNumber) {
        return service.getCustomerByPhoneNumber(phoneNumber);
    }

}
