package com.rbaun.banking.controller.v1;

import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController implements BaseControllerAPI {

    @Autowired
    private CustomerService customerService;

    @Override
    public String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Customer getLoggedInCustomer() {
        String userName = getLoggedInUsername();

        return customerService.findCustomerByUsername(userName);
    }

}
