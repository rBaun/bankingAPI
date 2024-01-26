package com.rbaun.banking.service.account;

import com.rbaun.banking.exception.account.AccountNotFoundException;
import com.rbaun.banking.exception.account.AmountInvalidException;
import com.rbaun.banking.exception.account.DuplicateAccountException;
import com.rbaun.banking.exception.account.InsufficientFundsException;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.account.Transaction;
import com.rbaun.banking.model.customer.Customer;

import java.util.List;

/**
 * Service class for {@link Account} objects that contains all the business logic for the account entity
 */
public interface AccountService {

    /**
     * Create an account
     * @param account the account to create
     * @return the created account
     * @throws DuplicateAccountException if the account number is already created
     * @throws DuplicateAccountException if the account name is already created for the customer
     */
    Account createAccount(Account account, Customer customer);

    /**
     * Get an account by the id
     * @param id the id of the account to get
     * @return the account matching the id
     * @throws AccountNotFoundException if the account is not found
     */
    Account getAccountById(Long id);

    /**
     * Get an account by the account number
     * @param accountNumber the account number of the account to get
     * @return the account matching the account number
     * @throws AccountNotFoundException if the account is not found
     */
    Account getAccountByAccountNumber(String accountNumber);

    /**
     * Delete an account by the account number
     * @param accountNumber the account number of the account to delete
     */
    void deleteAccountByAccountNumber(String accountNumber);

    /**
     * Get a list of all the accounts registered
     * @return List of all the accounts, empty list if no accounts are registered
     */
    List<Account> getAllAccounts();

    /**
     * Deposit money into an account
     * @param accountNumber the account number to deposit money into
     * @param amount the amount to deposit
     * @return the account with the updated balance
     * @throws AccountNotFoundException if the account is not found
     * @throws AmountInvalidException if the amount is less than or equal to 0
     */
    Account deposit(String accountNumber, double amount);

    /**
     * Withdraw money from an account
     * @param accountNumber the account number to withdraw money from
     * @param amount the amount to withdraw
     * @return the account with the updated balance
     * @throws AccountNotFoundException if the account is not found
     * @throws AmountInvalidException if the amount is less than or equal to 0
     * @throws InsufficientFundsException if the account has insufficient funds
     */
    Account withdraw(String accountNumber, double amount);

    /**
     * Get the transaction history for an account
     * @param accountNumber the account number to get the transaction history for
     * @return the transaction history for the account
     * @throws AccountNotFoundException if the account is not found
     */
    List<Transaction> getAccountTransactionHistory(String accountNumber);
}
