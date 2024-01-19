package com.rbaun.banking.service.account;

import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.repository.AccountRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountTransactionComponent accountTransactionComponent) {
        this.accountRepository = accountRepository;
        this.accountTransactionComponent = accountTransactionComponent;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
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
        return accountTransactionComponent.deposit(accountNumber, amount);
    }

    @Override
    @Transactional
    public Account withdraw(String accountNumber, double amount) {
        return accountTransactionComponent.withdraw(accountNumber, amount);
    }
}
