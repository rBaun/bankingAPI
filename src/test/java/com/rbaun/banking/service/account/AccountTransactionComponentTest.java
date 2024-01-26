package com.rbaun.banking.service.account;

import com.rbaun.banking.exception.account.AmountInvalidException;
import com.rbaun.banking.exception.account.InsufficientFundsException;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.enums.AccountType;
import com.rbaun.banking.model.enums.TransactionType;
import com.rbaun.banking.repository.account.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AccountTransactionComponentTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountTransactionComponent accountTransactionComponent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deposit_ValidAmount_IncreasesBalance() {
        // Setup account
        double initialBalance = 0.0;
        double amount = 100.0;
        Account account = new Account("title", "123", AccountType.CREDIT, initialBalance);

        // Deposit the amount to the account
        when(accountRepository.save(account)).thenReturn(account);
        Account accountAfterDeposit = accountTransactionComponent.deposit(account, amount);

        // Verify that the balance has increased by the amount
        assertEquals(initialBalance + amount, accountAfterDeposit.getBalance());

        // Verify account saved once
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void deposit_ValidAmount_CreatesTransaction() {
        // Setup account
        double initialBalance = 0.0;
        double amount = 100.0;
        Account account = new Account("title", "123", AccountType.CREDIT, initialBalance);
        int transactionListSize = account.getTransactionList().size();

        // Deposit the amount to the account
        when(accountRepository.save(account)).thenReturn(account);
        Account accountAfterDeposit = accountTransactionComponent.deposit(account, amount);
        transactionListSize++;

        // Verify transaction was added to account for the correct type and amount
        assertEquals(transactionListSize, accountAfterDeposit.getTransactionList().size());
        assertEquals(amount, accountAfterDeposit.getTransactionList().get(transactionListSize - 1).getAmount());
        assertEquals(TransactionType.DEPOSIT, accountAfterDeposit.getTransactionList().get(transactionListSize - 1).getType());

        // Verify account saved once
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void deposit_InvalidAmount_ThrowsAmountInvalidException() {
        // Setup account
        double initialBalance = 0.0;
        double amount = -100.0;
        Account account = new Account("title", "123", AccountType.CREDIT, initialBalance);

        // Attempt to deposit the amount to the account and expect an exception
        assertThrows(AmountInvalidException.class, () -> accountTransactionComponent.deposit(account, amount));
    }

    @Test
    public void withdraw_ValidAmount_DecreasesBalance() {
        // Setup account
        double initialBalance = 100.0;
        double amount = 50.0;
        Account account = new Account("title", "123", AccountType.CREDIT, initialBalance);

        // Withdraw the amount from the account
        when(accountRepository.save(account)).thenReturn(account);
        Account accountAfterWithdrawal = accountTransactionComponent.withdraw(account, amount);

        // Verify that the balance has decreased by the amount
        assertEquals(initialBalance - amount, accountAfterWithdrawal.getBalance());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void withdraw_ValidAmount_CreatesTransaction() {
        // Setup account
        double initialBalance = 100.0;
        double amount = 50.0;
        Account account = new Account("title", "123", AccountType.CREDIT, initialBalance);
        int transactionListSize = account.getTransactionList().size();

        // Withdraw the amount from the account
        when(accountRepository.save(account)).thenReturn(account);
        Account accountAfterWithdrawal = accountTransactionComponent.withdraw(account, amount);
        transactionListSize++;

        // Verify transaction was added to account for the correct type and amount
        assertEquals(transactionListSize, accountAfterWithdrawal.getTransactionList().size());
        assertEquals(amount, accountAfterWithdrawal.getTransactionList().get(transactionListSize - 1).getAmount());
        assertEquals(TransactionType.WITHDRAWAL, accountAfterWithdrawal.getTransactionList().get(transactionListSize - 1).getType());

        // Verify account saved once
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void withdraw_InvalidAmount_ThrowsAmountInvalidException() {
        // Setup account
        double initialBalance = 100.0;
        double amount = -50.0;
        Account account = new Account("title", "123", AccountType.CREDIT, initialBalance);

        // Attempt to withdraw the amount from the account and expect an exception
        assertThrows(AmountInvalidException.class, () -> accountTransactionComponent.withdraw(account, amount));
    }

    @Test
    public void withdraw_AmountGreaterThanBalance_ThrowsInsufficientFundsException() {
        // Setup account
        double initialBalance = 100.0;
        double amount = 200.0;
        Account account = new Account("title", "123", AccountType.CREDIT, initialBalance);

        // Attempt to withdraw the amount from the account and expect an exception
        assertThrows(InsufficientFundsException.class, () -> accountTransactionComponent.withdraw(account, amount));
    }
}
