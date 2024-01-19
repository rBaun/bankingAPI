package com.rbaun.banking.service.account;

import com.rbaun.banking.model.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account);
    Optional<Account> getAccountById(Long id);
    Optional<Account> getAccountByAccountNumber(String accountNumber);
    void deleteAccount(Long id);
    void deleteAccountByAccountNumber(String accountNumber);
    List<Account> getAllAccounts();
    Account deposit(String accountNumber, double amount);
    Account withdraw(String accountNumber, double amount);
}
