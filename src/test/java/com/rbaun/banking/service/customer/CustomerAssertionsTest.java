package com.rbaun.banking.service.customer;

import com.rbaun.banking.assertion.customer.CustomerAssertions;
import com.rbaun.banking.exception.customer.CustomerInvalidEmailException;
import com.rbaun.banking.exception.customer.CustomerInvalidPhoneNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerAssertionsTest {

    private CustomerAssertions customerAssertions;

    @BeforeEach
    public void setup() {
        customerAssertions = new CustomerAssertions();
    }

    @Test
    public void createCustomer_EmailInvalid_ThrowsEmailInvalidException() {
        assertThrows(CustomerInvalidEmailException.class, () -> customerAssertions.throwIfEmailInvalid("invalidEmail"));
    }

    @Test
    public void createCustomer_EmailValid_NoExceptionThrown() {
        assertDoesNotThrow(() -> customerAssertions.throwIfEmailInvalid("validEmail@example.com"));
    }

    @Test
    public void createCustomer_PhoneNumberInvalid_ThrowsPhoneNumberInvalidException() {
        assertThrows(CustomerInvalidPhoneNumberException.class, () -> customerAssertions.throwIfPhoneNumberInvalid("invalidPhoneNumber"));
    }

    @Test
    public void createCustomer_PhoneNumberValid_NoExceptionThrown() {
        assertDoesNotThrow(() -> customerAssertions.throwIfPhoneNumberInvalid("1234567890"));
    }

}
