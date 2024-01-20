package com.rbaun.banking.service.account;

import com.rbaun.banking.controller.account.exception.AccountErrorMessage;
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

@Component
public class AccountTransactionComponent {

    @Autowired
    private AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionComponent.class);

    @Transactional
    public Account deposit(Account account, double amount) {
        throwIfAmountIsNegative(amount);
        account.deposit(amount);
        addTransactionToAccount(amount, TransactionType.DEPOSIT, account);

        return accountRepository.save(account);
    }

    @Transactional
    public Account withdraw(Account account, double amount) {
        throwIfAmountIsNegative(amount);
        throwIfAccountHasInsufficientFunds(amount, account);
        account.withdraw(amount);
        addTransactionToAccount(amount, TransactionType.WITHDRAWAL, account);

        return accountRepository.save(account);
    }

    private void addTransactionToAccount(double amount, TransactionType transactionType, Account account) {
        Transaction transaction = new Transaction(amount, transactionType);
        account.addTransaction(transaction);
        logger.info("Added transaction: {} to account: {}", transaction, account);
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

}
