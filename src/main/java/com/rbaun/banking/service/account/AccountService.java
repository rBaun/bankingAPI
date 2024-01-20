package com.rbaun.banking.service.account;

import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.account.Transaction;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    Account getAccountById(Long id);

    Account getAccountByAccountNumber(String accountNumber);

    void deleteAccountByAccountNumber(String accountNumber);

    List<Account> getAllAccounts();

    Account deposit(String accountNumber, double amount);

    Account withdraw(String accountNumber, double amount);

    List<Transaction> getAccountTransactionHistory(String accountNumber);
}
