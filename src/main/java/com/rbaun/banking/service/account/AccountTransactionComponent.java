package com.rbaun.banking.service.account;

import com.rbaun.banking.controller.account.exception.AccountErrorMessage;
import com.rbaun.banking.controller.account.exception.AccountNotFoundException;
import com.rbaun.banking.controller.account.exception.AmountInvalidException;
import com.rbaun.banking.controller.account.exception.InsufficientFundsException;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.account.Transaction;
import com.rbaun.banking.model.enums.TransactionType;
import com.rbaun.banking.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountTransactionComponent {

    @Autowired
    private AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionComponent.class);

    @Transactional
    public Account deposit(String accountNumber, double amount) {
        throwIfAmountIsNegative(amount);
        Account account = findAccountByAccountNumber(accountNumber);
        account.deposit(amount);

        Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT);
        account.addTransaction(transaction);
        logger.info("Added transaction: {} to account: {}", transaction, account);

        return accountRepository.save(account);
    }

    @Transactional
    public Account withdraw(String accountNumber, double amount) {
        throwIfAmountIsNegative(amount);
        Account account = findAccountByAccountNumber(accountNumber);
        throwIfAccountHasInsufficientFunds(amount, account);
        account.withdraw(amount);

        Transaction transaction = new Transaction(amount, TransactionType.WITHDRAWAL);
        account.addTransaction(transaction);
        logger.info("Added transaction: {} to account: {}", transaction, account);

        return accountRepository.save(account);
    }

    private void throwIfAmountIsNegative(double amount) {
        boolean amountIsBelowMinimum = amount <= 0;
        if (amountIsBelowMinimum) {
            logger.error("Amount: {} is below the minimum allowed transaction amount", amount);
            throw new AmountInvalidException(AccountErrorMessage.AMOUNT_BELOW_MINIMUM.getMessage());
        }
    }

    private void throwIfAccountHasInsufficientFunds(double amount, Account account) {
        boolean accountHasSufficientFunds = account.getBalance() >= amount;
        if (!accountHasSufficientFunds) {
            logger.error("Account: {} has insufficient funds", account);
            throw new InsufficientFundsException(AccountErrorMessage.INSUFFICIENT_FUNDS.getMessage());
        }
    }

    private Account findAccountByAccountNumber(String accountNumber) {
        Optional<Account> accountFoundByAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if (accountFoundByAccountNumber.isEmpty()) {
            logger.error("Account with account number: {} not found", accountNumber);
            throw new AccountNotFoundException(AccountErrorMessage.ACCOUNT_NOT_FOUND.getMessage());
        }
        return accountFoundByAccountNumber.get();
    }

}
