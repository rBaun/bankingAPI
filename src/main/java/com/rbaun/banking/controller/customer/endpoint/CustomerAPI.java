package com.rbaun.banking.controller.customer.endpoint;

import com.rbaun.banking.controller.customer.request.CreateCustomerRequest;
import com.rbaun.banking.service.customer.strategy.LookupCustomerRequest;
import com.rbaun.banking.controller.customer.request.UpdateCustomerRequest;
import com.rbaun.banking.controller.customer.response.CustomerResponse;
import com.rbaun.banking.controller.customer.response.DeleteCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CustomerAPI.BASE_URL)
public interface CustomerAPI {

        String BASE_URL = "/customers";
        String GET_ALL_CUSTOMERS_URL = "/all";
        String GET_CUSTOMERS_BY_SEARCH_TERM_URL = "/{searchTerm}";
        String CREATE_CUSTOMER_URL = "/create";
        String DELETE_CUSTOMER_URL = "/delete/{id}";
        String UPDATE_CUSTOMER_URL = "/update/{id}";

        /**
         * Get all customers
         *
         * @return List of all customers
         */
        @GetMapping(GET_ALL_CUSTOMERS_URL)
        ResponseEntity<List<CustomerResponse>> getAllCustomers();

        /**
         * Get customers by search term
         *
         * @param searchTerm Search term
         * @return List of customers matching the search term
         */
        @GetMapping(GET_CUSTOMERS_BY_SEARCH_TERM_URL)
        ResponseEntity<List<CustomerResponse>> getCustomersBySearchTerm(@PathVariable String searchTerm);

        /**
         * Create a new customer
         *
         * @param request Customer to create
         * @return Customer created
         */
        @PostMapping(CREATE_CUSTOMER_URL)
        ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request);


        /**
         * Delete customer by email, phoneNumber or socialSecurityNumber
         *
         * @param request Customer to delete
         * @return Customer deleted
         */
        @DeleteMapping(DELETE_CUSTOMER_URL)
        ResponseEntity<DeleteCustomerResponse> deleteCustomer(@RequestBody LookupCustomerRequest request);


        /**
         * Update customer by id
         *
         * @param request Customer to update
         * @return Customer updated
         */
        @PutMapping(UPDATE_CUSTOMER_URL)
        ResponseEntity<CustomerResponse> updateCustomer(@Valid @RequestBody UpdateCustomerRequest request);
}
