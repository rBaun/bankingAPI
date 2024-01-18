package com.rbaun.banking.service.account;

import com.rbaun.banking.model.Account;
import com.rbaun.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceIF {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
}
