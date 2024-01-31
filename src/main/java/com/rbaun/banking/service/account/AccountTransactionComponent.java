package com.rbaun.banking.service.account;

import com.rbaun.banking.assertion.account.AccountAssertions;
import com.rbaun.banking.exception.account.AmountInvalidException;
import com.rbaun.banking.exception.account.InsufficientFundsException;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.account.Transaction;
import com.rbaun.banking.model.enums.TransactionType;
import com.rbaun.banking.repository.account.AccountRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Responsible for the business logic of performing transactions on an account
 */
@Component
public class AccountTransactionComponent {

    private static final Logger logger = LoggerFactory.getLogger(AccountTransactionComponent.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountAssertions accountAssertions;

    /**
     * Deposit money into an account
     * @param account the account to deposit money into
     * @param amount the amount to deposit
     * @return the account with the updated balance
     * @throws AmountInvalidException if the amount is less than or equal to 0
     */
    @Transactional
    public Account deposit(Account account, double amount) {
        accountAssertions.throwIfAmountIsBelowMinimum(amount);
        account.deposit(amount);
        addTransactionToAccount(amount, TransactionType.DEPOSIT, account);

        return accountRepository.save(account);
    }

    /**
     * Withdraw money from an account
     * @param account the account to withdraw money from
     * @param amount the amount to withdraw
     * @return the account with the updated balance
     * @throws AmountInvalidException if the amount is less than or equal to 0
     * @throws InsufficientFundsException if the account has insufficient funds
     */
    @Transactional
    public Account withdraw(Account account, double amount) {
        accountAssertions.throwIfAmountIsBelowMinimum(amount);
        accountAssertions.throwIfAccountHasInsufficientFunds(amount, account);
        account.withdraw(amount);
        addTransactionToAccount(amount, TransactionType.WITHDRAWAL, account);

        return accountRepository.save(account);
    }

    private void addTransactionToAccount(double amount, TransactionType transactionType, Account account) {
        Transaction transaction = new Transaction(amount, transactionType);
        account.addTransaction(transaction);
        logger.info("Added transaction: {} to account: {}", transaction, account);
    }
}
