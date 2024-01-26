package com.rbaun.banking.model.account;

import com.rbaun.banking.controller.account.request.CreateAccountRequest;
import com.rbaun.banking.model.BaseEntity;
import com.rbaun.banking.model.customer.Customer;
import com.rbaun.banking.model.enums.AccountType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity that represents a customer's account in the banking application
 */
@Entity(name = "accounts")
public class Account extends BaseEntity {

    @Column(nullable = false)
    private String title;
    @Column(unique = true)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(nullable = false)
    private double balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Transaction> transactionList = new ArrayList<>();

    public Account() {
    }

    public Account(CreateAccountRequest createAccountRequest) {
        this.title = createAccountRequest.title();
        this.accountNumber = createAccountRequest.accountNumber();
        this.accountType = createAccountRequest.accountType();
        this.balance = createAccountRequest.balance();
    }

    public Account(String title, String accountNumber, AccountType accountType, double balance) {
        this.title = title;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    /**
     * Deposit money into account
     * @param amount Amount to deposit
     */
    public void deposit(double amount) {
        this.setBalance(this.getBalance() + amount);
    }

    /**
     * Withdraw money from account
     * @param amount Amount to withdraw
     */
    public void withdraw(double amount) {
        this.setBalance(this.getBalance() - amount);
    }

    /**
     * Add transaction to account
     * @param transaction Transaction to add
     */
    public void addTransaction(Transaction transaction) {
        transaction.setAccount(this);
        this.transactionList.add(transaction);
    }

    /**
     * Add list of transactions to account
     * @param transactionList List of transactions to add
     */
    public void addTransactionList(List<Transaction> transactionList) {
        transactionList.forEach(this::addTransaction);
    }

    /**
     * Remove transaction from account, if exists on account
     * @param transaction Transaction to remove
     */
    public void removeTransaction(Transaction transaction) {
        if (this.transactionList.remove(transaction)) {
            transaction.setAccount(null);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + super.getId() + '\'' +
                ", title='" + title + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}

