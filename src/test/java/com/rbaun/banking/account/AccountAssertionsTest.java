package com.rbaun.banking.account;

import com.rbaun.banking.assertion.account.AccountAssertions;
import com.rbaun.banking.exception.account.AccountNotFoundException;
import com.rbaun.banking.exception.account.AmountInvalidException;
import com.rbaun.banking.exception.account.InsufficientFundsException;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.model.enums.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountAssertionsTest {

    private AccountAssertions accountAssertions;
    private Customer customer;
    private Account account;

    @BeforeEach
    public void setup() {
        accountAssertions = new AccountAssertions();
        customer = new Customer();
        account = new Account();
    }

    @Test
    public void accountTransaction_CustomerDoesNotOwnAccount_ThrowsAccountNotFoundException() {
        assertThrows(AccountNotFoundException.class, () -> accountAssertions.throwIfSignedInCustomerDoesNotOwnAccount("123", customer));
    }

    @Test
    public void accountTransaction_CustomerDoesNotOwnAccount_NoExceptionThrown() {
        customer.addAccount(new Account("title", "123", AccountType.CREDIT, 0));
        assertDoesNotThrow(() -> accountAssertions.throwIfSignedInCustomerDoesNotOwnAccount("123", customer));
    }

    @Test
    public void accountTransaction_AmountIsBelowMinimum_ThrowsAmountInvalidException() {
        assertThrows(AmountInvalidException.class, () -> accountAssertions.throwIfAmountIsBelowMinimum(0));
    }

    @Test
    public void accountTransaction_AmountIsAboveMinimum_NoExceptionThrown() {
        assertDoesNotThrow(() -> accountAssertions.throwIfAmountIsBelowMinimum(1));
    }

    @Test
    public void accountTransaction_AccountHasInsufficientFunds_ThrowsInsufficientFundsException() {
        account.setBalance(0);
        assertThrows(InsufficientFundsException.class, () -> accountAssertions.throwIfAccountHasInsufficientFunds(1, account));
    }

    @Test
    public void accountTransaction_AccountHasSufficientFunds_NoExceptionThrown() {
        account.setBalance(100);
        assertDoesNotThrow(() -> accountAssertions.throwIfAccountHasInsufficientFunds(1, account));
    }
}
