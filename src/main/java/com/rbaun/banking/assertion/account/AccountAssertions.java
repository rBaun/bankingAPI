package com.rbaun.banking.assertion.account;

import com.rbaun.banking.exception.account.AccountErrorMessage;
import com.rbaun.banking.exception.account.AccountNotFoundException;
import com.rbaun.banking.exception.account.AmountInvalidException;
import com.rbaun.banking.exception.account.InsufficientFundsException;
import com.rbaun.banking.model.account.Account;
import com.rbaun.banking.model.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountAssertions {
    private static final Logger logger = LoggerFactory.getLogger(AccountAssertions.class);

    /**
     * Validate that the signed in customer owns the account
     * @param accountNumber the account number to validate
     * @param customer the signed in customer
     * @throws AccountNotFoundException if the signed in customer does not own the account
     */
    public void throwIfSignedInCustomerDoesNotOwnAccount(String accountNumber, Customer customer) {
        boolean signedInCustomerDoesNotOwnAccount = !customer.hasAccount(accountNumber);
        if (signedInCustomerDoesNotOwnAccount) {
            logger.error("Signed in customer: {} does not own account with account number: {}", customer, accountNumber);
            throw new AccountNotFoundException(AccountErrorMessage.ACCOUNT_NOT_FOUND_ON_CUSTOMER.getMessage());
        }
    }

    /**
     * Validate that the amount is above the minimum allowed transaction amount
     * @param amount the amount to validate
     * @throws AmountInvalidException if the amount is less than or equal to 0
     */
    public void throwIfAmountIsBelowMinimum(double amount) {
        boolean amountIsBelowMinimum = amount <= 0;
        if (amountIsBelowMinimum) {
            logger.error("Amount: {} is below the minimum allowed transaction amount", amount);
            throw new AmountInvalidException(AccountErrorMessage.AMOUNT_BELOW_MINIMUM.getMessage());
        }
    }

    /**
     * Validate that the account has sufficient funds
     * @param amount the amount to withdraw from the account
     * @param account the account to validate
     * @throws InsufficientFundsException if the account has insufficient funds
     */
    public void throwIfAccountHasInsufficientFunds(double amount, Account account) {
        boolean accountHasSufficientFunds = account.getBalance() >= amount;
        if (!accountHasSufficientFunds) {
            logger.error("Account: {} has insufficient funds", account);
            throw new InsufficientFundsException(AccountErrorMessage.INSUFFICIENT_FUNDS.getMessage());
        }
    }
}
