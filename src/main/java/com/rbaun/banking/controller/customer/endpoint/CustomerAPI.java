package com.rbaun.banking.controller.customer.endpoint;

import com.rbaun.banking.controller.customer.request.CreateCustomerRequest;
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
        ResponseEntity<List<CustomerResponse>> getCustomerBySearchTerm(@PathVariable String searchTerm);

        /**
         * Create a new customer
         *
         * @param createCustomerRequest Customer to create
         * @return Customer created
         */
        @PostMapping(CREATE_CUSTOMER_URL)
        ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest);


        /**
         * Delete customer by id
         *
         * @param id Customer id
         * @return Customer deleted
         */
        @DeleteMapping(DELETE_CUSTOMER_URL)
        ResponseEntity<DeleteCustomerResponse> deleteCustomer(@PathVariable Long id);


        /**
         * Update customer by id
         *
         * @param id Customer id
         * @param updateCustomerRequest Customer to update
         * @return Customer updated
         */
        @PutMapping(UPDATE_CUSTOMER_URL)
        ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest);
}
