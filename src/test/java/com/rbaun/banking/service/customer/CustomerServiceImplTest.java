package com.rbaun.banking.service.customer;

import com.rbaun.banking.assertion.customer.CustomerAssertions;
import com.rbaun.banking.controller.customer.request.LookupCustomerRequest;
import com.rbaun.banking.exception.customer.CustomerNotFoundException;
import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.repository.customer.CustomerRepository;
import com.rbaun.banking.service.customer.strategy.LookupCustomerStrategy;
import com.rbaun.banking.service.customer.strategy.LookupCustomerStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerAssertions customerAssertions;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllCustomers_GivenCustomersExist_ReturnsAllCustomers() {
        // Setup
        Customer customer1 = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, "123");
        Customer customer2 = new Customer("Jane", "jane@gmail.com", "0987654321", "Address 2", null, "456");
        when(customerRepository.findAll()).thenReturn(List.of(customer1, customer2));

        // Execute
        List<Customer> result = customerService.findAllCustomers();

        // Verify
        assertEquals(2, result.size());
        assertEquals(customer1, result.get(0));
        assertEquals(customer2, result.get(1));
    }

    @Test
    public void searchCustomers_GivenMultipleMatchingCustomersExists_ReturnsMatchingCustomers() {
        // Given
        String searchTerm = "John";
        Customer customer1 = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, "123");
        Customer customer2 = new Customer("John", "john2@gmail.com", "0987654321", "Address 2", null, "456");
        List<Customer> expectedCustomers = Arrays.asList(customer1, customer2);

        // When
        when(customerRepository.findAll(any(Specification.class))).thenReturn(expectedCustomers);
        List<Customer> actualCustomers = customerService.searchCustomers(searchTerm);

        // Then
        assertEquals(expectedCustomers, actualCustomers);
    }

    @Test
    public void searchCustomers_GivenMatchingCustomerExists_ReturnsMatchingCustomer() {
        // Setup
        String searchTerm = "John";
        Customer customer = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, "123");
        when(customerRepository.findAll(any(Specification.class))).thenReturn(List.of(customer));

        // Execute
        List<Customer> result = customerService.searchCustomers(searchTerm);

        // Verify
        assertEquals(1, result.size());
        assertEquals(customer, result.get(0));
    }

    @Test
    public void searchCustomers_GivenNameSelector_ReturnsMatchingCustomer() {
        // Setup
        String searchTerm = "John";
        Customer customer = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, "123");
        when(customerRepository.findAll(any(Specification.class))).thenReturn(List.of(customer));

        // Execute
        List<Customer> result = customerService.searchCustomers(searchTerm);

        // Verify
        assertEquals(1, result.size());
        assertEquals(customer, result.get(0));
    }

    @Test
    public void searchCustomers_GivenEmailSelector_ReturnsMatchingCustomer() {
        // Setup
        String searchTerm = "john@gmail.com";
        Customer customer = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, "123");
        when(customerRepository.findAll(any(Specification.class))).thenReturn(List.of(customer));

        // Execute
        List<Customer> result = customerService.searchCustomers(searchTerm);

        // Verify
        assertEquals(1, result.size());
        assertEquals(customer, result.get(0));
    }

    @Test
    public void searchCustomers_GivenAddressSelector_ReturnsMatchingCustomer() {
        // Setup
        String searchTerm = "Address 1";
        Customer customer = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, "123");
        when(customerRepository.findAll(any(Specification.class))).thenReturn(List.of(customer));

        // Execute
        List<Customer> result = customerService.searchCustomers(searchTerm);

        // Verify
        assertEquals(1, result.size());
        assertEquals(customer, result.get(0));
    }

    @Test
    public void searchCustomers_GivenPhoneNumberSelector_ReturnsMatchingCustomer() {
        // Setup
        String searchTerm = "1234567890";
        Customer customer = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, "123");
        when(customerRepository.findAll(any(Specification.class))).thenReturn(List.of(customer));

        // Execute
        List<Customer> result = customerService.searchCustomers(searchTerm);

        // Verify
        assertEquals(1, result.size());
        assertEquals(customer, result.get(0));
    }

    @Test
    public void getCustomerByEmail_CustomerExists_ReturnsCustomer() {
        // Setup
        String email = "john@gmail.com";
        Customer customer = new Customer("John", email, "1234567890", "Address 1", null, "123");
        when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

        // Execute
        Customer result = customerService.getCustomerByEmail(email);

        // Verify
        assertEquals(customer, result);
    }

    @Test
    public void getCustomerByEmail_CustomerDoesNotExist_ThrowsException() {
        // Setup
        String email = "john@gmail.com";
        when(customerRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerByEmail(email));
    }

    @Test
    public void getCustomerByPhoneNumber_CustomerExists_ReturnsCustomer() {
        // Setup
        String phoneNumber = "1234567890";
        Customer customer = new Customer("John", "john@gmail.com", phoneNumber, "Address 1", null, "123");
        when(customerRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(customer));

        // Execute
        Customer result = customerService.getCustomerByPhoneNumber(phoneNumber);

        // Verify
        assertEquals(customer, result);
    }

    @Test
    public void getCustomerByPhoneNumber_CustomerDoesNotExist_ThrowsException() {
        // Setup
        String phoneNumber = "1234567890";
        when(customerRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerByPhoneNumber(phoneNumber));
    }

    @Test
    public void getCustomerBySocialSecurityNumber_CustomerExists_ReturnsCustomer() {
        // Setup
        String socialSecurityNumber = "123";
        Customer customer = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, socialSecurityNumber);
        when(customerRepository.findBySocialSecurityNumber(socialSecurityNumber)).thenReturn(Optional.of(customer));

        // Execute
        Customer result = customerService.getCustomerBySocialSecurityNumber(socialSecurityNumber);

        // Verify
        assertEquals(customer, result);
    }

    @Test
    public void getCustomerBySocialSecurityNumber_CustomerDoesNotExist_ThrowsException() {
        // Setup
        String socialSecurityNumber = "123";
        when(customerRepository.findBySocialSecurityNumber(socialSecurityNumber)).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerBySocialSecurityNumber(socialSecurityNumber));
    }

    @Test
    public void createCustomer_ValidCustomer_CreatesAndReturnsCustomer() {
        // Setup
        Customer customer = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, "123");
        when(customerRepository.save(customer)).thenReturn(customer);
        // Mock the CustomerAssertions behavior
        doNothing().when(customerAssertions).throwIfEmailInvalid(customer.getEmail());
        doNothing().when(customerAssertions).throwIfPhoneNumberInvalid(customer.getPhoneNumber());

        // Execute
        Customer result = customerService.createCustomer(customer);

        // Verify
        assertEquals(customer, result);
    }

    @Test
    public void updateCustomer_ValidCustomer_UpdatesAndReturnsCustomer() {
        // Setup
        String socialSecurityNumber = "123";
        Customer existingCustomer = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, socialSecurityNumber);
        Customer updatedCustomer = new Customer("Jane", "jane@gmail.com", "0987654321", "Address 2", null, socialSecurityNumber);
        when(customerRepository.findBySocialSecurityNumber(socialSecurityNumber)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);
        doNothing().when(customerAssertions).throwIfEmailInvalid(updatedCustomer.getEmail());
        doNothing().when(customerAssertions).throwIfPhoneNumberInvalid(updatedCustomer.getPhoneNumber());

        // Execute
        Customer result = customerService.updateCustomer(updatedCustomer, socialSecurityNumber);

        // Verify
        assertEquals(updatedCustomer, result);
    }

    @Test
    public void deleteCustomer_ValidCustomer_DeletesCustomer() {
        // Setup
        String socialSecurityNumber = "123";
        Customer customer = new Customer("John", "john@gmail.com", "1234567890", "Address 1", null, socialSecurityNumber);
        when(customerRepository.findBySocialSecurityNumber(socialSecurityNumber)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(customer);

        // Execute
        LookupCustomerRequest request = new LookupCustomerRequest(null, null, socialSecurityNumber);
        LookupCustomerStrategy strategy = LookupCustomerStrategyFactory.from(request);
        customerService.deleteCustomer(strategy, request.getLookupValue());

        // Verify
        verify(customerRepository, times(1)).delete(customer);
    }

}
