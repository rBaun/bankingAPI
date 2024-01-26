package com.rbaun.banking.service.account;

import com.rbaun.banking.exception.account.AccountNotFoundException;
import com.rbaun.banking.exception.account.DuplicateAccountException;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.account.Transaction;
import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.model.enums.AccountType;
import com.rbaun.banking.repository.account.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountTransactionComponent accountTransactionComponent;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAccount_ValidInput_CreatesAccount() {
        // Setup account
        String accountNumber = "123";
        String title = "Account name";
        Account account = new Account(title, accountNumber, AccountType.CREDIT, 0.0);
        Customer customer = new Customer();
        customer.setId(1L);
        account.setCustomer(customer);

        // Mock the behavior of accountRepository to return an empty Optional when findByAccountNumber is called
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        // Mock the behavior of accountRepository to return our account when save is called
        when(accountRepository.save(account)).thenReturn(account);

        // Call the createAccount method
        Account createdAccount = accountService.createAccount(account, customer);

        // Verify that the created account has the correct account number, account type, and balance
        assertEquals(title, createdAccount.getTitle());
        assertEquals(accountNumber, createdAccount.getAccountNumber());
        assertEquals(AccountType.CREDIT, createdAccount.getAccountType());
        assertEquals(0.0, createdAccount.getBalance());
        assertEquals(customer, createdAccount.getCustomer());
    }

    @Test
    public void createAccount_AccountNumberExist_throwsDuplicateAccountException() {
        // Setup account
        String accountNumber = "123";
        Account account = new Account("title",accountNumber, AccountType.CREDIT, 0.0);

        // Mock the behavior of accountRepository to return an account when findByAccountNumber is called
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        // Call the createAccount method and expect a DuplicateAccountException
        assertThrows(DuplicateAccountException.class, () -> accountService.createAccount(account, new Customer()));
    }

    @Test
    public void getAccountById_ValidId_ReturnsAccount() {
        // Setup account
        Long id = 1L;
        Account account = new Account("title", "123", AccountType.CREDIT, 0.0);
        account.setId(id);

        // Mock the behavior of accountRepository to return our account when findById is called
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        // Call the getAccountById method
        Account retrievedAccount = accountService.getAccountById(id);

        // Verify that the retrieved account has the correct ID
        assertEquals(id, retrievedAccount.getId());
    }

    @Test
    public void getAccountById_InvalidId_ThrowsAccountNotFoundException() {
        // Setup account
        Long id = 1L;

        // Mock the behavior of accountRepository to return an empty Optional when findById is called
        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        // Call the getAccountById method and expect an AccountNotFoundException
        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(id));
    }

    @Test
    public void getAccountByAccountNumber_ValidAccountNumber_ReturnsAccount() {
        // Setup account
        String accountNumber = "123";
        Account account = new Account("title", accountNumber, AccountType.CREDIT, 0.0);

        // Mock the behavior of accountRepository to return our account when findByAccountNumber is called
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        // Call the getAccountByAccountNumber method
        Account retrievedAccount = accountService.getAccountByAccountNumber(accountNumber);

        // Verify that the retrieved account has the correct account number
        assertEquals(accountNumber, retrievedAccount.getAccountNumber());
    }

    @Test
    public void getAccountByAccountNumber_AccountNumberNotExist_ThrowsAccountNotFoundException() {
        // Setup account
        String accountNumber = "123";

        // Mock the behavior of accountRepository to return an empty Optional when findByAccountNumber is called
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        // Call the getAccountByAccountNumber method and expect an AccountNotFoundException
        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountByAccountNumber(accountNumber));
    }

    @Test
    public void deleteAccount_ValidAccountNumber_AccountDeleted() {
        // Setup account
        String accountNumber = "123";
        Account account = new Account("title", accountNumber, AccountType.CREDIT, 0.0);

        // Mock the behavior of accountRepository to return our account when findByAccountNumber is called
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        // Call the deleteAccountByAccountNumber method
        accountService.deleteAccountByAccountNumber(accountNumber);

        // Verify that the deleteByAccountNumber method was called on the accountRepository
        verify(accountRepository, times(1)).deleteByAccountNumber(accountNumber);
    }

    @Test
    public void getAllAccounts_AccountsExist_ReturnsAllAccounts() {
        // Setup accounts
        Account account1 = new Account("title", "123", AccountType.CREDIT, 0.0);
        Account account2 = new Account("title2", "456", AccountType.SAVINGS, 0.0);
        List<Account> accounts = List.of(account1, account2);

        // Mock the behavior of accountRepository to return our accounts when findAll is called
        when(accountRepository.findAll()).thenReturn(accounts);

        // Call the getAllAccounts method
        List<Account> retrievedAccounts = accountService.getAllAccounts();

        // Verify that the retrieved accounts are the same as our accounts
        assertEquals(accounts, retrievedAccounts);
    }

    @Test
    public void deposit_AccountExist_IncreasesBalance() {
        // Setup account
        String accountNumber = "123";
        double initialBalance = 0.0;
        double amount = 100.0;
        Account account = new Account("title", accountNumber, AccountType.CREDIT, initialBalance);

        // Mock the behavior of accountRepository
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(java.util.Optional.of(account));

        // Mock the behavior of accountTransactionComponent and accountRepository
        when(accountTransactionComponent.deposit(account, amount)).thenAnswer(invocation -> {
            Account argAccount = invocation.getArgument(0);
            double argAmount = invocation.getArgument(1);
            argAccount.setBalance(argAccount.getBalance() + argAmount);
            return argAccount;
        });
        when(accountRepository.save(account)).thenReturn(account);

        // Deposit the amount to the account
        Account accountAfterDeposit = accountService.deposit(accountNumber, amount);

        // Verify that the balance has increased by the amount
        assertEquals(initialBalance + amount, accountAfterDeposit.getBalance());
    }

    @Test
    public void deposit_AccountNotExist_ThrowsAccountNotFoundException() {
        assertThrows(AccountNotFoundException.class, () -> accountService.deposit("123", 100.0));
    }

    @Test
    public void withdraw_AccountExist_IncreasesBalance() {
        // Setup account
        String accountNumber = "123";
        double initialBalance = 100.0;
        double amount = 50.0;
        Account account = new Account("title", accountNumber, AccountType.CREDIT, initialBalance);

        // Mock the behavior of accountRepository
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(java.util.Optional.of(account));

        // Mock the behavior of accountTransactionComponent and accountRepository
        when(accountTransactionComponent.withdraw(account, amount)).thenAnswer(invocation -> {
            Account argAccount = invocation.getArgument(0);
            double argAmount = invocation.getArgument(1);
            argAccount.setBalance(argAccount.getBalance() - argAmount);
            return argAccount;
        });
        when(accountRepository.save(account)).thenReturn(account);

        // Withdraw the amount from the account
        Account accountAfterWithdraw = accountService.withdraw(accountNumber, amount);

        // Verify that the balance has decreased by the amount
        assertEquals(initialBalance - amount, accountAfterWithdraw.getBalance());
    }

    @Test
    public void withdraw_AccountNotExist_ThrowsAccountNotFoundException() {
        assertThrows(AccountNotFoundException.class, () -> accountService.withdraw("123", 100.0));
    }

    @Test
    public void getAccountTransactionHistory_AccountExist_ReturnsTransactionHistory() {
        // Setup account
        String accountNumber = "123";
        Account account = new Account("title", accountNumber, AccountType.CREDIT, 0.0);

        // Mock the behavior of accountRepository to return our account when findByAccountNumber is called
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        // Call the getAccountTransactionHistory method
        List<Transaction> transactionHistory = accountService.getAccountTransactionHistory(accountNumber);

        // Verify that the transaction history is empty
        assertEquals(0, transactionHistory.size());
    }

    @Test
    public void getAccountTransactionHistory_AccountNotExist_ThrowsAccountNotFoundException() {
        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountTransactionHistory("123"));
    }
}
