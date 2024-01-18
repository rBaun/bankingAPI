package com.rbaun.banking.service.account;

import com.rbaun.banking.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountServiceIF {
    Account createAccount(Account account);
    Optional<Account> getAccountById(Long id);
    Optional<Account> getAccountByAccountNumber(String accountNumber);
    void deleteAccount(Long id);
    void deleteAccountByAccountNumber(String accountNumber);
    List<Account> getAllAccounts();
}
