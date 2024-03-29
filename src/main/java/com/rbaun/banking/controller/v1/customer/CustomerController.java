package com.rbaun.banking.controller.v1.customer;

import com.rbaun.banking.controller.v1.BaseController;
import com.rbaun.banking.controller.v1.customer.request.CreateCustomerRequest;
import com.rbaun.banking.controller.v1.customer.request.LookupCustomerRequest;
import com.rbaun.banking.controller.v1.customer.request.UpdateCustomerRequest;
import com.rbaun.banking.controller.v1.customer.response.CustomerResponse;
import com.rbaun.banking.controller.v1.customer.response.DeleteCustomerResponse;
import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.service.customer.CustomerService;
import com.rbaun.banking.service.customer.strategy.LookupCustomerStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController extends BaseController implements CustomerControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<CustomerResponse> getCustomer() {
        logger.info("Finding Customer for {}", getLoggedInUsername());
        CustomerResponse customer = CustomerResponse.from(getLoggedInCustomer());
        logger.info("Finished looking for Customer: {}", customer);

        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        logger.info("{} requested a list of all customers", getLoggedInUsername());
        List<CustomerResponse> customerList = customerService.findAllCustomers().stream().map(CustomerResponse::new).toList();
        logger.info("Found {} customers to return", customerList.size());

        return ResponseEntity.ok(customerList);
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getCustomersBySearchTerm(String searchTerm) {
        logger.info("{} requested to find customers with search term: {}", getLoggedInUsername(), searchTerm);
        List<CustomerResponse> customerList = customerService.searchCustomers(searchTerm).stream().map(CustomerResponse::new).toList();
        logger.info("Found {} customers to return", customerList.size());

        return ResponseEntity.ok(customerList);
    }

    @Override
    public ResponseEntity<CustomerResponse> createCustomer(CreateCustomerRequest request) {
        logger.info("{} requested to create customer: {}", getLoggedInUsername(), request);
        Customer customer = customerService.createCustomer(new Customer(request, getLoggedInUsername()));
        logger.info("Created customer: {}", customer);

        return ResponseEntity.ok(CustomerResponse.from(customer));
    }

    @Override
    public ResponseEntity<DeleteCustomerResponse> deleteCustomer(LookupCustomerRequest request) {
        logger.info("{} requested to delete customer: {}", getLoggedInUsername(), request);
        customerService.deleteCustomer(LookupCustomerStrategyFactory.from(request), request.getLookupValue());
        logger.info("Deleted customer: {}", request);

        return ResponseEntity.ok(DeleteCustomerResponse.from(request));
    }

    @Override
    public ResponseEntity<CustomerResponse> updateCustomer(UpdateCustomerRequest request) {
        logger.info("{} requested to update customer: {}", getLoggedInUsername(), request);
        Customer customer = customerService.updateCustomer(new Customer(request), request.socialSecurityNumberToUpdate());
        logger.info("Updated customer: {}", customer);

        return ResponseEntity.ok(CustomerResponse.from(customer));
    }

}
