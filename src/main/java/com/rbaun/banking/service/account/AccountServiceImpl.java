package com.rbaun.banking.service.account;

import com.rbaun.banking.controller.account.exception.AccountNotFoundException;
import com.rbaun.banking.controller.account.exception.DuplicateAccountException;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link Account} objects.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountTransactionComponent accountTransactionComponent;
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountTransactionComponent accountTransactionComponent) {
        this.accountRepository = accountRepository;
        this.accountTransactionComponent = accountTransactionComponent;
    }

    @Override
    public Account createAccount(Account account) {
        throwIfAccountNumberAlreadyCreated(account);

        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> accountFoundById = accountRepository.findById(id);
        if (accountFoundById.isEmpty()) {
            logger.error("Account with id {} not found", id);
            throw new AccountNotFoundException("Account with id " + id + " not found");
        }

        return accountFoundById.get();
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        Optional<Account> accountFoundByAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if (accountFoundByAccountNumber.isEmpty()) {
            logger.error("Account with account number {} not found", accountNumber);
            throw new AccountNotFoundException("Account with account number " + accountNumber + " not found");
        }

        return accountFoundByAccountNumber.get();
    }

    @Override
    public void deleteAccountByAccountNumber(String accountNumber) {
        accountRepository.deleteByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account deposit(String accountNumber, double amount) {
        Account account = getAccountByAccountNumber(accountNumber);

        return accountTransactionComponent.deposit(account, amount);
    }

    @Override
    @Transactional
    public Account withdraw(String accountNumber, double amount) {
        Account account = getAccountByAccountNumber(accountNumber);

        return accountTransactionComponent.withdraw(account, amount);
    }

    private void throwIfAccountNumberAlreadyCreated(Account account) {
        boolean accountExists = accountRepository.findByAccountNumber(account.getAccountNumber()).isPresent();
        if (accountExists) {
            logger.error("Account with account number {} already exists", account.getAccountNumber());
            throw new DuplicateAccountException("Account with account number " + account.getAccountNumber() + " already exists");
        }
    }
}
