package com.rbaun.banking.controller.customer;

import com.rbaun.banking.controller.customer.endpoint.CustomerAPI;
import com.rbaun.banking.controller.customer.request.CreateCustomerRequest;
import com.rbaun.banking.controller.customer.request.UpdateCustomerRequest;
import com.rbaun.banking.controller.customer.response.CustomerResponse;
import com.rbaun.banking.controller.customer.response.DeleteCustomerResponse;
import com.rbaun.banking.service.customer.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController implements CustomerAPI {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getCustomerBySearchTerm(String searchTerm) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerResponse> createCustomer(CreateCustomerRequest createCustomerRequest) {
        return null;
    }

    @Override
    public ResponseEntity<DeleteCustomerResponse> deleteCustomer(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerResponse> updateCustomer(Long id, UpdateCustomerRequest updateCustomerRequest) {
        return null;
    }

}
