package com.rbaun.banking.assertion.customer;

import com.rbaun.banking.exception.customer.CustomerErrorMessage;
import com.rbaun.banking.exception.customer.CustomerInvalidEmailException;
import com.rbaun.banking.exception.customer.CustomerInvalidPhoneNumberException;
import com.rbaun.banking.util.EmailUtil;
import com.rbaun.banking.util.PhoneNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerAssertions {
    private static final Logger logger = LoggerFactory.getLogger(CustomerAssertions.class);

    /**
     * Make sure that the email is valid and follows the correct format
     * @param email the email to validate
     * @throws CustomerInvalidEmailException if the email is invalid
     */
    public void throwIfEmailInvalid(String email) {
        if (!EmailUtil.isValidEmailAddress(email)) {
            logger.error("Customer email {} is invalid", email);
            throw new CustomerInvalidEmailException(CustomerErrorMessage.CUSTOMER_INVALID_EMAIL.getMessage());
        }
    }

    /**
     * Make sure that the phone number is valid and follows the correct format
     * @param phoneNumber the phone number to validate
     * @throws CustomerInvalidPhoneNumberException if the phone number is invalid
     */
    public void throwIfPhoneNumberInvalid(String phoneNumber) {
        if (!PhoneNumberUtil.isValid(phoneNumber)) {
            logger.error("Customer phone number {} is invalid", phoneNumber);
            throw new CustomerInvalidPhoneNumberException(CustomerErrorMessage.CUSTOMER_INVALID_PHONE_NUMBER.getMessage());
        }
    }
}
